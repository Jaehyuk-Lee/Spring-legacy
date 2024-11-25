<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  안녕
</h1>

<P>  The time on the server is ${serverTime}. </P>
<ol>
	<li><a href="sample/all">all</a></li>
	<li><a href="sample/member">member</a></li>
	<li><a href="sample/admin">admin</a></li>
</ol>
</body>
</html>
