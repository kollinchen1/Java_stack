<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

	<meta charset="UTF-8">
	<title>Dojo Page</title>
</head>
<body>
	<div class="container">
		<a href="/ninjas">View All Dojos</a>
		<h1>
			<c:out value="${dojo.name}" />
		</h1>
		<c:forEach var="ninja" items="${dojo.ninjas }">
			<ul>
				<li>
					<a href="/ninja/${ninja.id}"><c:out value="${ninja.first_name }" /></a> - <c:out value="${ninja.last_name }"/> - <c:out value="${ninja.age }"/> 
				</li>					
			</ul>
		</c:forEach>
	
	</div>
</body>
</html>