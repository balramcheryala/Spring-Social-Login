<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.button {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 10px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<center>
<h1 style="color:#4CAF50;">IPL Login Form</h1>
<form:form action="loginform.html"  commandName="loginForm">
	<table>
		<tr>
			<td>User Name:<FONT color="#AD1874"><form:errors
				path="userName" /></FONT></td>
		</tr>
		<tr>
			<td><form:input path="userName" style="text-transform: lowercase"/></td>
		</tr>
		<tr>
			<td>Password:<FONT color="#AD1874"><form:errors
				path="password" style="text-transform: lowercase" /></FONT></td>
		</tr>
		<tr>
			<td><form:password path="password" /></td>
		</tr>
		<tr>
			<td><button type="submit"  class="button">Login User</button></td>
		</tr>
	</table></body>
</form:form>
</center>
</html>