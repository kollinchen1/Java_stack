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
<style>
table {
/* fixed space for each column  */
	width:60%!important;
    table-layout: fixed;
    word-wrap: break-word;
}
</style>
</head>
<body>
	<h1>Welcome, &#128525; <c:out value="${user.name}"/> &#128525; <a href="/logout">Logout</a></h1>
	<h2>Courses:</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Courses</th>
					<th>Instructor</th>
					<th>Signups</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="course" items="${courses}">
				<tr>
					<td>
						<a href="/courses/${course.id}"><c:out value="${course.name }" /></a>
					</td>
					<td>
						<c:out value="${course.instructor }" />
					</td>
					<td>
						<c:out value="${course.joinedUsers.size() }/${course.capacity }" />
					</td>
					<td>
						<c:choose>
							<c:when test="${Integer.parseInt(course.joinedUsers.size()) == Integer.parseInt(course.capacity)}">
							   <p>FULL</p>
					
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${course.joinedUsers.contains(user)}">
							    		<p>Already Added</p>
					
									</c:when>
									<c:otherwise>
									    <a href="/add/${course.id}">Add</a>
									</c:otherwise>
								</c:choose>	    	
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
				
			</tbody>
		</table>
	<a href="/courses/new">Create</a>
</body>
</html>