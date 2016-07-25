<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/playerlist.css" />" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>About Team</title>
</head>

<body>
		<table border="1" cellpadding="2" width="70%">
			<tr>
				<th>NAME</th>
				<th>COACH</th>
				<th>CAPTAIN</th>
				<th>VENUE</th>
				<th>OWNER</th>
				<c:forEach var="team" items="${teamlist}">

					<tr>
						<td>${team.name}</td>
						<td>${team.couch}</td>
						<td>${team.caption}</td>
						<td>${team.veneue}</td>
						<td>${team.owner}</td>

					</tr>
				</c:forEach>
		</table>
		<br />
</form>
</body>
</html>