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
        
        <form action='/process' class = "form" method='POST'>
            <div class = "flex-container">
                <label>Your Name:</label>
                <input class="flexTwo" type="text" name="name">
            </div>
            <div class = "flex-container">
                <label>Dojo Location:</label>
                <select class="flexTwo"name='dojo_location'>
                    <option value='San Jose'>San Jose</option>
                    <option value='San Francisco'>San Francisco</option>
                    <option value='Seattle'>Seattle</option>
                    <option value='San Diego'>San Diego</option>
                    <option value='Los Angeles'>Los Angeles</option>
                </select>
            </div>
            <div class = "flex-container">
                <label>Favorite Language:</label>
                <select class="flexTwo" name='favorite_language'>
                    <option value='Python'>Python</option>
                    <option value='C++'>C++</option>
                    <option value='Java'>Java</option>
                    <option value='Javascript'>Javascript</option>
                    <option value='Go'>Go</option>
                </select>
            </div>
            <div class = "flex-container flexColumn">
                <label style="margin-bottom:10px">Comment(Optional):</label>
                <textarea rows=5" cols="20" name="comment" wrap="hard"></textarea>
            </div>
            <div style="text-align:right">
                <input class = "greenButton" type="submit" value="Submit">
            </div>
        </form>
    </div>
</body>
</html>