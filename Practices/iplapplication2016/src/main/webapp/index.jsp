<%@page import="com.bridgelabz.social.FBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	FBConnection fbConnection = new FBConnection();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.button {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer; }
    
.h1{color:blue;}
.p{color:green;} 
}
</style>
</head>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">

	<!-- Optional theme -->
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">

	<!-- Latest compiled and minified JavaScript -->
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>

	<!-- font awesome css -->
	<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
</head>
<body style="text-align:center; padding:1em">

<center><h1 style="color:#800000;">WELCOME TO IPL APPLICATION</h1></center> 
<form action="loginform.html">
<center><button type="submit"  class="btn btn-success" style="width:12em">IPL LOGIN </button></center>
<br></form>
<form action="registrationform.html">
<center><button type="submit"  class="btn btn-success" style="width:12em">IPL REGISTRATION</button></center>
</form>
<center><h2 style="color:black;">Sign in With Social Networks</h2></center> 
	<!-- <div class="btn-group">
		<a class='btn btn-danger disabled'><i class="fa fa-google-plus" style="width:16px; height:20px"></i></a>
		<a class='btn btn-danger' href='' style="width:12em;"> Sign in with Google</a>
	</div>
	<br /><br /> -->
	<div class="btn-group">
		<a class='btn btn-primary disabled'><i class="fa fa-facebook" style="width:16px; height:20px"></i></a>
		<a class='btn btn-primary ' href='<%=fbConnection.getFBAuthUrl()%>' style="width:12em"> Sign in with Facebook</a>
	</div>	
	<br /><br />
	<div class="btn-group">
		<a class='btn btn-info disabled'><i class="fa fa-twitter" style="width:16px; height:20px"></i></a>
		<a class='btn btn-info ' href='' style="width:12em"> Sign in with Twitter</a>
	</div>	
	<br /><br />

</body>
</html>
