<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class = "container">
		<h1>Hello World</h1>
		<form method = "POST" action = "/process">
			<input type = "email" name="email" />
			<input type = "password" name="password" />
			<button type="submit">Login</button>
		</form>
	</div>
</body>
</html>