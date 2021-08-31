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
<title>Events</title>
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
	<div>
		<h1>Welcome, &#128525; <c:out value="${user.first_name} ${user.last_name}" /> &#128525; <a href="/logout">Logout</a></h1>
		<p><c:out value="${errorBruteForce}" /></p>
		<p>Here are some of the events in your state:</p>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Date</th>
					<th>Location</th>
					<th>Host</th>
					<th>Action/Status</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="event" items="${events}">
					<tr>
						<c:if test="${user.state == event.state}">
							<td><a href="/events/${event.id}/view"><c:out value="${event.name }" /></a></td>
							<td><fmt:formatDate pattern = "MMMM dd, yyyy" value = "${event.host_date}" /></td>
							<td><c:out value="${event.city }" /></td>
							<td><c:out value="${event.host.first_name }" /></td>
							<td>
								<c:choose>
								<c:when test="${user_id == event.host.id}">
							    	<a href="/events/${event.id}/edit">Edit</a>
									<a href="/events/${event.id}/delete">Delete</a>
							  	</c:when>
							  	<c:when test="${event.joinedUsers.contains(user)}">
							    	<a>JoinED</a>
							    	<a href="/events/${event.id}/cancel">Cancel</a>
							  	</c:when>
							  	
							  	<c:otherwise>
							    	<a href="/events/${event.id}">Join</a>
							 	</c:otherwise>
								
								<%-- <c:if test="${user_id == event.host.id}">
									<a href="/events/${event.id}/edit">Edit</a>
									<a href="/events/${event.id}/delete">Delete</a>
								</c:if> --%>
								</c:choose>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p>Here are some of the events in other states:</p>
				<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Date</th>
					<th>Location</th>
					<th>Host</th>
					<th>Action/Status</th>
				</tr>
			</thead>
			<tbody>	
				<c:forEach var="event" items="${events}">
					<tr>
						<c:if test="${user.state != event.state}">
							<td><a href="/events/${event.id}/view"><c:out value="${event.name }" /></a></td>
							<td><fmt:formatDate pattern = "MMMM dd, yyyy" value = "${event.host_date}" /></td>
							<td><c:out value="${event.city }" /></td>
							<td><c:out value="${event.host.first_name }" /></td>
							<td>
								<c:choose>
								<c:when test="${user_id == event.host.id}">
							    	<a href="/events/${event.id}/edit">Edit</a>
									<a href="/events/${event.id}/delete">Delete</a>
							  	</c:when>
							  	<c:when test="${event.joinedUsers.contains(user)}">
							    	<a>JoinED</a>
							    	<a href="/events/${event.id}/cancel">Cancel</a>
							  	</c:when>
							  	<c:otherwise>
							    	<a href="/events/${event.id}">Join</a>
							 	</c:otherwise>
								
								<%-- <c:if test="${user_id == event.host.id}">
									<a href="/events/${event.id}/edit">Edit</a>
									<a href="/events/${event.id}/delete">Delete</a>
								</c:if> --%>
								</c:choose>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		 <form:form method="POST" action="/create/event" modelAttribute="eventObj">
		 <form:input value="${user_id}" path="host" type="hidden"/>
        <p>
            <form:label path="name">Name:</form:label>
            <form:input path="name" />
            <form:errors path="name"/>
        </p>
        <p>
            <form:label path="host_date">Date: </form:label>
            <form:input type = "date" path="host_date" />
            <form:errors path="host_date"/>
        </p>
        <p>
        	<form:label path="city">Location:</form:label>
        	<span>
            <form:input path="city" />
            <form:select path="state" >
            	<option selected disabled>Choose a State</option>
				<option value="AL">AL</option>
				<option value="AK">AK</option>
				<option value="AR">AR</option>	
				<option value="AZ">AZ</option>
				<option value="CA">CA</option>
				<option value="CO">CO</option>
				<option value="CT">CT</option>
				<option value="DC">DC</option>
				<option value="DE">DE</option>
				<option value="FL">FL</option>
				<option value="GA">GA</option>
				<option value="HI">HI</option>
				<option value="IA">IA</option>	
				<option value="ID">ID</option>
				<option value="IL">IL</option>
				<option value="IN">IN</option>
				<option value="KS">KS</option>
				<option value="KY">KY</option>
				<option value="LA">LA</option>
				<option value="MA">MA</option>
				<option value="MD">MD</option>
				<option value="ME">ME</option>
				<option value="MI">MI</option>
				<option value="MN">MN</option>
				<option value="MO">MO</option>	
				<option value="MS">MS</option>
				<option value="MT">MT</option>
				<option value="NC">NC</option>	
				<option value="NE">NE</option>
				<option value="NH">NH</option>
				<option value="NJ">NJ</option>
				<option value="NM">NM</option>			
				<option value="NV">NV</option>
				<option value="NY">NY</option>
				<option value="ND">ND</option>
				<option value="OH">OH</option>
				<option value="OK">OK</option>
				<option value="OR">OR</option>
				<option value="PA">PA</option>
				<option value="RI">RI</option>
				<option value="SC">SC</option>
				<option value="SD">SD</option>
				<option value="TN">TN</option>
				<option value="TX">TX</option>
				<option value="UT">UT</option>
				<option value="VT">VT</option>
				<option value="VA">VA</option>
				<option value="WA">WA</option>
				<option value="WI">WI</option>	
				<option value="WV">WV</option>
				<option value="WY">WY</option>
			</form:select >
			<form:errors path="city"/>
			</span>
        </p>
        <button type="submit">Create</button>
    	</form:form>
	</div>
</body>
</html>