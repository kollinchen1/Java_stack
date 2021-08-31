<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<div class="container p-5">
    <h1 class="text-left">Welcome!</h1>
    <div class="container p-5 border border-dark d-flex justify-content-between">
    <div class="register-form">
        <h3>Register Here!</h3>
        <p><form:errors path="user.*"/></p>    	
        <p><c:out value="${error}" /></p>

        <form:form method="POST" action="/registration" modelAttribute="userObj">
        <p>
            <form:label path="first_name">First Name</form:label>
            <form:input path="first_name" id="longerInputBox"/>
            <form:errors path="first_name"/>
        </p>
        <p>
            <form:label path="last_name">Last Name</form:label>
            <form:input path="last_name" id="longerInputBox"/>
            <form:errors path="last_name"/>
        </p>
        <p>
            <form:label path="email">Email:</form:label>
            <form:input type="email" path="email" id="longerInputBox"/>
            <form:errors path="email"/>
        </p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password" id="longerInputBox"/>
            <form:errors path="password"/>
        </p>
        <p>
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation" id="longerInputBox"/>
            <form:errors path="passwordConfirmation"/>
        </p>
        <p>
        	<form:label path="city">Location:</form:label>
        	<form:errors path="city"/>
        	<span>
            <form:input path="city" />
            <form:select path="state" >
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
			</span>
        </p>
        <button type="submit" style="margin-top:30px" class='btn btn-success'>Register</button>
    	</form:form>

    </div>
    <div class="login-form">
        <h3>Login Here!</h3>
        <p><c:out value="${errorLogin}" /></p>
        <form action="/login" method="post">
        <p>
            Email:
            <input type="email" name="email" id="longerInputBox">
        </p>
        <p>
            Password:
            <input type="password" name="password" id="longerInputBox">
        </p>
        <button type="submit" class='btn btn-success'>Login</button>
        </form>
    </div>
    </div>
</div>
</body>
</html>