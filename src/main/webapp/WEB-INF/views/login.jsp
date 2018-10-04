<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>

    <h1 class="text-center">Sign In</h1>
    
    <p class="text-center">${ LoginResult }</p>

	<form role="form" action="/loginPost"  method="post">
		<div class="box-body">
			<div class="form-group">
				<input type="text" id="uid"
					name="uid" value="user1" class="form-control" placeholder="ID..." value="${ uid }" autofocus />
			</div>

			<div class="form-group">
			    <input type="password" name="upw" value="1234" placeholder="Password..." class="form-control" />
			</div>

			<div class="form-group">
				<label for="useCookie">
				    <input type="checkbox" id="useCookie" name="useCookie"/> Remember Me
				</label>
			</div>
			
		</div> <!-- end of box-body -->
		
		<div class="box-footer">
		  <button type="submit" class="btn btn-primary">Sign In</button>
		</div>

	</form>

<%@ include file="footer.jsp"%>
</html>