<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Debtors Prison</title>
</head>
<body>
	<h1 class="text-center m-0">You're in a debtors prison</h1>
	<div class="container mt-2">
		<h1>Your Gold:  <span style="width: 120px;" class="text-left border border-dark px-5"> <c:out value="${count}" /></span></h1>
		<p><a href="/reset">Reset</a></p>
		<div class="mt-5 d-flex justify-content-between">
			<form class = "d-flex flex-column border border-dark py-4 px-5 text-center"  action="/process/dig">
				<h1>Dig</h1>
				<p>(earns 1 gold)</p>
				<button type="submit">Find Gold!</button>
			</form>
			<form class = "d-flex flex-column border border-dark py-4 px-5 text-center" action="/process/gamble">
				<h1>Prison Gamble</h1>
				<p>(earns/takes 20-100 gold)</p>
				<button type="submit">Find Gold!</button>
			</form>	
			<form class = "d-flex flex-column border border-dark py-4 px-5 text-center" action="/process/laundry">
				<h1>Laundry</h1>
				<p>(earns 1-3 gold)</p>
				<button type="submit">Find Gold!</button>
			</form>
			
		
		</div>
		
		<div>
			<h1>Activities:</h1>
			<div style = "height:200px " class="px-3 overflow-auto border border-dark" id="middle">
				<c:forEach var="message" items="${messages}">
					<c:choose>
						<c:when test="${Integer.parseInt(message.gain) >=0 }">
            				<p style="margin:5px; color:green"><c:out value="${message.getMessage()}"></c:out></p>
            			</c:when>
            			<c:otherwise>
							<p style="margin:5px; color:red"><c:out value="${message.getMessage()}"></c:out></p>
						</c:otherwise>
					</c:choose>
       			 </c:forEach>
			</div>
			
		</div>
	
	
	</div>
	<script>
	    var div = document.getElementById("middle");
	    div.scrollTop = div.scrollHeight;
 	</script>
</body>
</html>