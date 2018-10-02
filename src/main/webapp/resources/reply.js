const REGIST_URL = "/replies/";
let gBno = 0,
	gPage = null,
	gIsEdit = false,
	gRno = 0,
	gReplytext = null;

function listPage(page, bno) {
	event.preventDefault();
	gBno = bno || gBno;
	page = page || gPage || 1;
	gPage = page;
//	console.debug("======================== listPage");
	
	let url = "/replies/" + gBno + "/" + page;
//	console.log("url>>>", url)
	sendAjax(url, (isSuccess, res) => {
//		console.debug("listPage:res>>", res);
		if (isSuccess) {
			res.currentPage = page;
			res.pageData = makePageData(res.pageMaker);
//			console.log("res>>", res.pageData)
			renderHbs("replies", res);
		}
	});
}

const readReply = rno => new Promise( (resolves, rejects) => {
	sendAjax("/replies/" + rno, (isSuccess, res) => {
		if (isSuccess) {
			resolves(res);
			
		} else {
			rejects(Error(res));
		}
	})
});

function makePageData(pm) {
//	console.debug(pm);
	let pageData = {
		prevPage: 0,
		pages: [],
		nextPage: 0
	};
	
	if (pm.prev) {
		pageData.prevPage = pm.startPage - 1;
	}
	
	for (let i = pm.startPage; i <= pm.endPage; i++) {
		pageData.pages.push(i);
	}
	  
	if (pm.next) {
		pageData.nextPage = pm.endPage + 1;
	}
	
	pageData.delim = "|";
	
	return pageData;
}

const checkEdit = () => {
	let $replyer = $('#replyer'),
		$replytext = $('#replytext'),
		$btn = $('#btnModReply'),
		replyer = $replyer.val(),
		replytext = $('#replytext').val();
	console.debug("QQQQQ>>", replytext, event, event.keyCode)
	if (event.keyCode === 13)
		console.debug("**********************************")
	
	if (replyer && replytext && replytext !== gReplytext) {
		$btn.show();
	} else {
		$btn.hide();
	}
	
};

function editReply(rno, replyer, replytext) {
	event.preventDefault();
	gIsEdit = !!rno;
	gRno = rno;
	gReplytext = replytext;
	
	renderHbs('myModal', {
		gIsEdit: gIsEdit,
		replyer: replyer,
		replytext: replytext
	});
	
	$('#myModal').modal();
	$('#btnModReply').hide();
}

function closeMod() {
	gRno = 0;
	gReplytext = null;
	$('#myModal').modal('hide');
}

function save() {
	let jsonData = getValidData( $('#replyer'), $('#replytext') );
	if (!jsonData) return;
	
	let url = gIsEdit ? "/replies/" + gRno : "/replies/",
		method = gIsEdit ? 'PATCH' : 'POST';
	
	if (!gIsEdit)
		jsonData.bno = gBno;
	
	sendAjax(url, (isSuccess, res) => {
		if (isSuccess) {
			let resultMsg = gIsEdit ? gRno + "번 댓글이 수정되었습니다." : "댓글이 등록되었습니다.";  
			alert(resultMsg);
			listPage(gIsEdit ? gPage : 1);
			closeMod();
		} else {
			console.debug("Error on editReply>>", res);
		}
	}, method, jsonData);
}

function removeReply() {
	if (!confirm("Are u sure??")) return;
	
	sendAjax("/replies/" + gRno, (isSuccess, res) => {
		if (isSuccess) {
			alert(gRno + " Removed.");
			listPage();
			closeMod();
		} else {
			console.debug("Error on removeReply>>", res);
		}
	}, 'DELETE');
}

function getValidData($replyer, $replytext) {
	let $errorFocus = null,
		replyer = $replyer.val(),
		replytext = $replytext.val(),
		errorMsg = "";

	if (!gIsEdit && !replyer) {
		errorMsg = "작성자를 입력하세요!";
		$errorFocus = $replyer;
		
	} else if (!replytext) {
		errorMsg = "내용을 입력하세요!";
		$errorFocus = $replytext;
	}
	
	if (errorMsg) {
		alert(errorMsg);
		$errorFocus.focus();
		return;
	}
	
	return {replyer: replyer, replytext: replytext};
}

function sendAjax(url, fn, method, jsonData) {
	let options = {
					method: method || 'GET',
					url: url, 
					contentType: 'application/json'
				  };
	if (jsonData)
		options.data = JSON.stringify(jsonData);
	
	$.ajax(options).always((responseText, statusText, ajaxResult) => {
		// console.log("aaa", responseText, statusText, ajaxResult);
		let isSuccess = statusText === 'success';
		fn(isSuccess, responseText);
		if (!isSuccess) {
			alert("오류가 발생하였습니다!! (errorMessgae:" + responseText + ")");
		}
	});
}