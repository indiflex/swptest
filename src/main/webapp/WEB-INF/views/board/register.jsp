<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>

	<form role="form" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="title">제목</label> <input type="text" id="title"
					name="title" class="form-control" placeholder="title..." />
			</div>

			<div class="form-group">
				<label for="content">내용</label>
				<textarea name="content" id="content" cols="30" rows="3"
					class="form-control" placeholder="내용.."></textarea>
			</div>

			<div class="form-group">
				<label for="writer">작성자</label>
				<input type="text" id="writer"
					name="writer" class="form-control" />
			</div>
			
			<div class="form-group">
			   <label for="">File Drop Here!</label>
			   <div class="fileDrop text-right">
			     <div id="percent">0 %</div>
                 <div id="status">ready</div>
			   </div>
		    </div>
		</div> <!-- end of box-body -->
		
		<div class="box-footer">
		  <ul class="mailbox-attachments clearfix uploadedList">
			<%@ include file="uploadedFiles.jsp"%>
		  </ul>
		  
		  <a href="/board/listPage${criteria.makeQuery()}" class="btn btn-default">목록</a>
		  <button type="submit" class="btn btn-primary">Submit</buteston>
		</div>

	</form>

<form action="/uploadAjaxes" id="form_attach" method="POST" enctype="multipart/form-data">
       <input type="hidden" name="type" value="ajax" />
       <input type="file" name="files" id="ajax-file" style="display: none;" />
       <!-- <input type="submit" value="ajax로 제출" /> -->
</form>


<script src="/resources/upload.js?ver=1"></script>
<script>
gIsEditing = true;
</script>
<%@ include file="../footer.jsp"%>
</html>