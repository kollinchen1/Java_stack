<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<meta charset="UTF-8">
	<title>Languages</title>
</head>
<body>
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Creator</th>
					<th>Version</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="language" items="${languages}">
					<tr>
						<td>
							<a href="/languages/${language.id}"><c:out value="${language.name}" /></a>
						</td>
						<td><c:out value="${language.creator}" /></td>
						<td><c:out value="${language.currentVersion}" /></td>
						<td>
					
						<form action="/languages/${language.id}" method="post">
					    <input type="hidden" name="_method" value="delete">
					    <input style="background-color: inherit;border: 0;text-decoration: underline; color:#0d6efd"type="submit" value="Delete">
					    <a href="/languages/${language.id}/edit">Edit</a>
						</form>
						
							
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- CREATE -->
	
	<div class="container">
		<form:form action="/languages" method="post" modelAttribute="newLanguage">
		    <p>
		        <form:label path="name">Name</form:label>
		        <form:errors path="name"/>
		        <form:input path="name"/>
		    </p>
		    <p>
		        <form:label path="creator">Creator</form:label>
		        <form:errors path="creator"/>
		        <form:textarea path="creator"/>
		    </p>
		    <p>
		        <form:label path="currentVersion">Version</form:label>
		        <form:errors path="currentVersion"/>
		        <form:input path="currentVersion"/>
		    </p>
	  
		    <input type="submit" value="Submit"/>
		</form:form>	
	
	</div>
	
</body>
</html>