<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/customLogout" method="post">
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	<input type="submit">
</form>
</body>
</html>