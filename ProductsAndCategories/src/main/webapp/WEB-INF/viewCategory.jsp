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
	<title>Category Page</title>
</head>
<body>
	<div class="container text-center ">
		<h1><c:out value="${category.name }" /></h1>
		<div class ="d-flex justify-content-around align-items-start mt-5">
			<div>
				<h4>Products: </h4>
				<ul>
				<c:forEach var="prod" items="${category.products}">
					<li>
						<c:out value="${prod.name }" />
						<form action="/product/${prod.id}/${category.id }" method="post">
						    <input type="hidden" name="_method" value="delete">
						    <input style="background-color: inherit;border: 0;text-decoration: underline; color:#0d6efd"type="submit" value="Delete">
						</form>
					</li>
				</c:forEach>
			</ul>
			</div>
			<form action="/category/${category.id}" method="POST">
			<h4>
				Add Category:
				<select name="product_id" id="">
					<c:forEach var="pro" items="${products }">
						<option value="${pro.id }">
							<c:out value="${pro.name }" />
						</option>
					</c:forEach>
				</select>
			
			</h4>
			<button type="submit">Add</button>
		</form>
		</div>
		
		
		
		
	</div>
</body>
</html>