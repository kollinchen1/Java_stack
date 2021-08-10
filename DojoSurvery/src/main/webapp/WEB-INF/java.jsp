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
<title>Dojo Survey Java Index</title>
</head>
<body>
	<div class="container">
        <div class="insideContainer">
            <h1>Submitted Java</h1>
            <div class = "flex-container">
                <p>Java Name:</p>
                <p>Java <c:out value="${name }" /></p>
            </div>
            <div class = "flex-container">
                <p>Java Dojo Location:</p>
                <p>Java <c:out value="${dojo_location }" /></p>
            </div>
            <div class = "flex-container">
                <p>Java Favorite Language:</p>
                <p>OMGGG YOU LOVE JAVA!!!!!!!!!!!!!!!!!!!!!!!!</p>
            </div>
            <div class = "flex-container">
                <p>Java Comment:</p>
                <p>Java <c:out value="${comment }" /></p>
            </div>
            <div style="text-align:right; margin-top:100px;">
                <a href="/" class = greenButton>Go Back Java</a>
            </div>
        </div>
        
    </div>
</body>
</html>