<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<meta charset="ISO-8859-1">
	<title>${language.name}</title>
</head>
<body>
	<div class="container">
		<form action="/languages/${language.id}" method="post">
		    <input type="hidden" name="_method" value="delete">
		    <input style="background-color: inherit;border: 0;text-decoration: underline; color:#0d6efd"type="submit" value="Delete">
		    <a href="/languages">Dashboard</a>
		</form>
		<h1>Edit</h1>
		<form:form action="/languages/${language.id}" method="post" modelAttribute="language">
		    <input type="hidden" name="_method" value="put">
		    <p>
		        <form:label path="name">Title</form:label>
		        <form:input path="name"/>
		        <form:errors path="name"/>
		    </p>
		    <p>
		        <form:label path="creator">Description</form:label>
		        <form:errors path="creator"/>
		        <form:textarea path="creator"/>
		    </p>
		    <p>
		        <form:label path="currentVersion">Language</form:label>
		        <form:errors path="currentVersion"/>
		        <form:input path="currentVersion"/>
		    </p>   
		    <input type="submit" value="Submit"/>
		</form:form>
	</div>
</body>
</html>