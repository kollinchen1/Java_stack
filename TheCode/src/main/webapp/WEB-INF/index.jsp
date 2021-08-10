<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Secret Code</title>
</head>
<body>
	<div class="container text-center">
		<p class="text-danger"><c:out value="${error}"/></p>
		<h3>What is the Code?</h3>
		<form class="d-flex flex-column align-items-center" method="POST" action="/process">
			<input type="text" name="code" />
			
			<button style="margin-top:20px" type="submit">Try Code</button>
		</form>
	</div>
</body>
</html>