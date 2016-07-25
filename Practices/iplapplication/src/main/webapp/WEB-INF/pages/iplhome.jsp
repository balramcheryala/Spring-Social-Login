<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ipl Home</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/background.css" />" />
</head>
<center>

		<h1 style="color:green;"><I><strong>Welcome To IPL Home Page</strong></I></h1>
		<br>
	</center>
	<table border="1" align="center" width="75%">
	<tr>
		<th colspan="4"><h3><I><strong><b>IPL 2016 Teams</b></strong></I></h3></th>
		</tr>
	<tr>	
		<td><h5 style="color:#900C3F;"><I><strong>DELHI DAREDEVILS</strong></I></h5></td>
		<td>
			<form action="teams/DelhiDareDevils">
				<button type="submit" class="View  Team Info">Team Information</button>
			</form>
		</td>
		
		
		<td>
			<form action="players/DelhiDareDevils">
				<button type="submit" class="View Player Info">Player Information</button>
			</form>
		</td>
	<td>		
			<img src="<c:url value="/resources/images/ddp.jpg" />" alt=""  style="width:160px; height:auto;">
			
		</td>
	</tr>
	
	<tr>
		<td><h5><I><strong>GUJARATH LIONS</strong></I></h5></td>
		<td>
			<form action="teams/GujaratLions">
				<button type="submit" class="View  Team Info">Team Information</button>
			</form>
		</td>
		<td>
			<form action="players/GujaratLions">
				<button type="submit" class="View Player Info">Player Information</button>
			</form>
		</td>
<td>
			<img src="<c:url value="/resources/images/gujrat.png" />" alt=""  style="width:150px; height:auto;">
		</td>
	</tr>
	
	<tr>
<td><h5 style="color:#900C3F;"><I><strong>KING XI PANJAB</strong></I></h5></td>
		<td>
			<form action="teams/KingsXIPunjab">
				<button type="submit" class="View  Team Info">Team Information</button>
			</form>
		</td>
		<td>
			<form action="players/KingsXIPunjab">
				<button type="submit" class="View Player Info">Player Information</button>
			</form>
		</td>
<td>
			<img src="<c:url value="/resources/images/panjab.jpg" />" alt=""  style="width:130px; height:auto;">
		</td>
	</tr>
	
	<tr>
		<td><h5><I><strong>KOLKATA KNIGHT RIDERS</strong></I></h5></td>
		<td>
			<form action="teams/KolkataKnightRiders">
				<button type="submit" class="View  Team Info">Team Information</button>
			</form>
		</td>
		<td>
			<form action="players/KolkataKnightRiders">
				<button type="submit" class="View Player Info">Player Information</button>
			</form>
		</td>
<td>
			<img src="<c:url value="/resources/images/kolkatta.png" />" alt=""  style="width:100px; height:auto;">
		</td>
	</tr>
	
	<tr>
<td><h5 style="color:#900C3F;"><I><strong>MUMBAI INDIANS</strong></I></h5></td>
		<td>
			<form action="teams/MumbaiIndians">
				<button type="submit" class="View  Team Info">Team Information</button>
			</form>
		</td>
		<td>
			<form action="players/MumbaiIndians">
				<button type="submit" class="View Player Info">Player Information</button>
			</form>
		</td>
<td>
			<img src="<c:url value="/resources/images/mumbai.png" />" alt=""  style="width:100px; height:auto;">
		</td>
	</tr>
	
	<tr>
		<td><h5><I><strong>RISING PUNE SUPER GIANTS</strong></I></h5></td>
		<td>
			<form action="teams/RisingPuneSupergiants">
				<button type="submit" class="View  Team Info">Team Information</button>
			</form>
		</td>
		<td>
			<form action="players/RisingPuneSupergiants">
				<button type="submit" class="View Player Info">Player Information</button>
			</form>
		</td>
	<td>
			<img src="<c:url value="/resources/images/pune.jpg" />" alt=""  style="width:150px; height:auto;">
		</td>
	</tr>
	<tr>
<td><h5 style="color:#900C3F;"><I><strong>ROYAL CHALANGERS BANGLORE </strong></I></h5></td>
		<td>
			<form action="teams/RoyalChallenge">
				<button type="submit" class="View  Team Info">Team Information</button>
			</form>
		</td>
		<td>
			<form action="players/RoyalChallenge">
				<button type="submit" class="View Player Info">Player Information</button>
			</form>
		</td>
			<td>
			<img src="<c:url value="/resources/images/banglore.jpeg" />" alt=""  style="width:130px; height:auto;">
		</td>
	</tr>
	
	<tr>
		<td><h5><I><strong>SUNRISERS HYDERABAD </strong></I></h5></td>
		<td>
			<form action="teams/SunRiseHydrabad">
				<button type="submit" class="View  Team Info">Team Information</button>
			</form>
		</td>
		<td>
			<form action="players/SunRiseHydrabad">
				<button type="submit" class="View Player Info">Player Information</button>
			</form>
		</td>
	<td>
			<img src="<c:url value="/resources/images/hyd.jpg" />" alt=""  style="width:100px; height:auto;">
		</td> 
	</tr>
	
	</table>
</body>
</html>
