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
	<li><a href="/mvc" >spring mvc 1.0</a></li>
	<li><a href="/board/list" >게시판 리스트</a></li>
	<li><a href="/uploadForm" >업로드</a></li>
</ol>
</body>
</html>
