<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/playerlist.css" />" />
    <title>${title}</title>
</head>
<body><center>

<h1 style="color:#00cccc;">"${title}"</h1>
<br/><br/>
Dear <h4 style="color:#C70039;">"${user}"</h4>You are successfully logged into IPL application.
    <br/><br/><br/>
    <a href="<c:url value="/services/ipl" />"><h4 style="color:#33E3FF;">IPL HomePage</a>&nbsp&nbsp&nbsp&nbsp</h4>	
	<a href="<c:url value="/j_spring_security_logout" />"><h4 style="color:#33E3FF;">Logout</a></h4>
    
</center></body>
</html>