<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Courses</title>
</head>
<body>
	<div class="container p-5">
    <h1 class="text-left">Welcome!</h1>
    <div class="container p-5 border border-dark d-flex justify-content-between">
    <div class="register-form">
        <h3>Register Here!</h3>
        <p><form:errors path="user.*"/></p>    	
        <p><c:out value="${error}" /></p>

        <form:form method="POST" action="/registration" modelAttribute="userObj">
        <p>
            <form:label path="name">Name:</form:label>
            <form:input path="name" id="longerInputBox"/>
            <form:errors path="name"/>
        </p>
        <p>
            <form:label path="email">Email:</form:label>
            <form:input type="email" path="email" id="longerInputBox"/>
            <form:errors path="email"/>
        </p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password" id="longerInputBox"/>
            <form:errors path="password"/>
        </p>
        <p>
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation" id="longerInputBox"/>
            <form:errors path="passwordConfirmation"/>
        </p>
        
       <button type="submit" style="margin-top:30px" class='btn btn-success'>Register</button>
    	</form:form>

    </div>
     <div class="login-form">
        <h3>Login Here!</h3>
        <p><c:out value="${errorLogin}" /></p>
        <form action="/login" method="post">
        <p>
            Email:
            <input type="email" name="email" id="longerInputBox">
        </p>
        <p>
            Password:
            <input type="password" name="password" id="longerInputBox">
        </p>
        <button type="submit" class='btn btn-success'>Login</button>
        </form>
    </div>
    </div>
    </div>
</body>
</html>