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
	<div>
		<a href="/courses">Back to homepage</a>
		<h1><c:out value="${course.name}"/></h1>
		<p>Instructor: ${course.instructor}</p>
		<p>Sign Ups: ${course.joinedUsers.size()}</p>
		
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Sign Up Date</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${course.joinedUsers}">
				<tr>
					<td>
						<c:out value="${user.name }" />
					</td>
					<td>
						<fmt:formatDate pattern = "MM/dd/yyyy" value = "${user.createdAt}" />
					</td>
					<c:if test="${user == currentUser}">
						<td>
							<a href="/remove/${course.id}">Remove</a>
						</td>
					</c:if> 
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/courses/${course.id}/edit">Edit</a>
		<br>
		<a href="/courses/${course.id}/delete">Delete</a>
	</div>
</body>
</html>