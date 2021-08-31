<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${language.name}</title>
</head>
<body>
	<h1><a href="/languages">Dashboard</a></h1>
	
	<p>Name: <c:out value="${language.name}"/></p>
	<p>Creator: <c:out value="${language.creator}"/></p>
	<p>Version: <c:out value="${language.currentVersion}"/></p>
	<br />
	<p>Created at: <c:out value="${language.getCreatedAt()}"/></p>
	<p>Updated at: <c:out value="${language.getUpdatedAt()}"/></p>
	<br />
	<p><a href="/languages/${language.id}/edit">Edit Book</a></p>
	<br />
	<form action="/languages/${language.id}" method="post">
	    <input type="hidden" name="_method" value="delete">
	    <input style="background-color: inherit;border: 0;text-decoration: underline; color:#0d6efd; font-size:16px; cursor:pointer"type="submit" value="Delete">
	</form>
	

	
</body>
</html>