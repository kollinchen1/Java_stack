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
	<title>New Ninja</title>
</head>
<body>
	<div class="container">
		<h1>New Ninja</h1>
		<a href="/ninjas/">View All ninjas</a>
		<form:form action="/ninjas/new" method="post" modelAttribute="newNinja">
			<p>
				<form:label path="dojoninjas">Dojo:</form:label>
				<form:select path="dojoninjas">
					<c:forEach var="dojo" items="${allDojos}">
						<form:option value="${dojo.id }">
							<c:out value="${dojo.name}" />
						</form:option>
					</c:forEach>
				</form:select>
			</p>
			<p>
				<form:label path="first_name">First Name:</form:label>
		        <form:input type="text" path="first_name"/>
		        <form:errors path="first_name"/>
			</p>
			<p>
				<form:label path="last_name">Last Name:</form:label>
		        <form:input type="text" path="last_name"/>
		        <form:errors path="last_name"/>
			</p>
			<p>
				<form:label path="age">Age:</form:label>
		        <form:input type="number" path="age"/>
		        <form:errors path="age"/>
			</p>
			
			<button type="submit">Create</button>
		</form:form>
	</div>
</body>
</html>