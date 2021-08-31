<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

<meta charset="UTF-8">
<title>Courses</title>
</head>
<body>
	<h1>Edit </h1>
	<a href="/courses">Back to homepage</a>
	<form:form method="POST" action="/courses/${courseObj.id}/edit" modelAttribute="courseObj">
        <input type="hidden" name="_method" value="put">
        <p>
            <form:label path="name">Name:</form:label>
            <form:input path="name" />
            <form:errors path="name"/>
        </p>
        <p>
            <form:label path="instructor">Instructor: </form:label>
            <form:input path="instructor" />
            <form:errors path="instructor"/>
        </p>
        <p>
            <form:label path="capacity">Capacity: </form:label>
            <form:input type = "number" path="capacity" />
            <form:errors path="capacity"/>
        </p>
    	<button type="submit">Update</button>
    </form:form>
</body>
</html>