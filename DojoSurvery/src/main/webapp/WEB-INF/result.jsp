<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/my_style.css">
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
 -->
<meta charset="UTF-8">
<title>Dojo Survey Index</title>
</head>
<body>
	<div class="container">
        <div class="insideContainer">
            <h1>Submitted Info</h1>
            <div class = "flex-container">
                <p>Name:</p>
                <p><c:out value="${name }" /></p>
            </div>
            <div class = "flex-container">
                <p>Dojo Location:</p>
                <p><c:out value="${dojo_location }" /></p>
            </div>
            <div class = "flex-container">
                <p>Favorite Language:</p>
                <p><c:out value="${favorite_language }" /></p>
            </div>
            <div class = "flex-container">
                <p>Comment:</p>
                <p><c:out value="${comment }" /></p>
            </div>
            <div style="text-align:right; margin-top:100px;">
                <a href="/" class = greenButton>Go Back</a>
            </div>
        </div>
        
    </div>
</body>
</html>