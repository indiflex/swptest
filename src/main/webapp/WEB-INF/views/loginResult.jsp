<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Google Login Result Page</title>
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <h2>Google Login Result</h2>
    <h3 class="text-success text-center"> ${ result } </h3>
    
    Code: ${ code }
    <textarea rows="5" cols="150">${ scope }</textarea>
    <textarea rows="20" cols="150">${ profile }</textarea>
    
    <div class="text-center">
        <a href="/login">Retry Login</a> | <a href="/board/listPage">Go To the Board</a>
    </div>
</body>
</html>