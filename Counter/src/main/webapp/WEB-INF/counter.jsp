<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Current visit  count</title>
</head>
<body>
	<div class="container">
		<p>You have visited <a href="/">http://localhost:8080/</a> <c:out value="${countContext }" /> times</p>
		<a href="/">Test Another Visit?</a>
		<br>
		<a href="/divide2">Divide Me!</a>
		<br>
		<a href="/reset">Reset</a>
	</div>
</body>
</html>