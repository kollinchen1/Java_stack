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
<title>${event.name}</title>

</head>
<body>
	<h1>${event.name}</h1>
	<a href="/events">Homepage</a>
	<p>
		Host:<c:out value="${event.host.first_name } ${event.host.last_name }" />
	</p>
	<p>
		Date: <fmt:formatDate pattern = "MMMM dd, yyyy" value = "${event.host_date}" /></p>
	<p>
		Location: <c:out value="${event.city }, ${event.state }" />
	</p>
	
	<p>
		People who are attending this event: ${event.joinedUsers.size()}
	</p>
	<div>
		<table class="table table-bordered">
				<thead>
					<tr>
						<th>Name</th>
						<th>Location</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="joinedUser" items="${event.joinedUsers}">
						<tr>
							<td>
								<c:out value="${joinedUser.first_name } ${joinedUser.last_name }" />
							</td>
							<td>
								<c:out value="${joinedUser.city } ${joinedUser.state }" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
		</table>
		<div>
			<h1>Message Wall</h1>
			<div style = "height:300px; margin-bottom:50px " class="px-3 overflow-auto border border-dark">
				<c:forEach var="message" items="${messages}">
					<p><c:out value="${message.sender.first_name }: ${message.body }"/></p>
					<p>--------------------------</p>
       			 </c:forEach>
			</div>
			
			<form:form method="post" action="/events/message" modelAttribute="messageObj">
				<form:input value="${user_id}" path="sender" type="hidden"/>
				<form:input value="${event.id}" path="event" type="hidden"/>
				 <p>
		            <form:label path="body">Write a message</form:label>
		            <form:input path="body" value=""/>
		            <form:errors path="body"/>
        		</p>
        		<%-- <form:errors path="event"/>
        		<form:errors path="sender"/> --%>
				<button type="submit">Submit</button>
    		</form:form>
    		
			
		</div>
	</div>
</body>
</html>