<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<meta charset="ISO-8859-1">
	<title>New Product</title>
</head>
<body>
	<div class="container">
		<h1>New Product</h1>
		<form:form action="/product/new" method="post" modelAttribute="productObj">
			<p>
				<form:label path="name">Name:</form:label>
		        <form:input path="name"/>
		        <form:errors path="name"/>
			</p>
			<p>
				<form:label path="description">Description:</form:label>
		        <form:input path="description"/>
		        <form:errors path="description"/>
			</p>
			<p>
				
				<form:label path="description">Price:</form:label>			
				<form:input type="number" step=".1" path="price"/>
				<form:errors path="price"/>
			</p>
			<button type="submit">Create</button>
		</form:form>
		<br/>
		<br/>
		<a href="/category/new">Create a Category</a>
	</div>
</body>
</html>