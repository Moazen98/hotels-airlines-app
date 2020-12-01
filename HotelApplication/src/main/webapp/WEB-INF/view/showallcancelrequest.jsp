<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Information</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center" id="tasksDiv">
		<h3>Mail Information</h3>
		<hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>hotelId</th>
						<th>roomId</th>
						<th>cityName</th>
						<th>userName</th>
						<th>startdate</th>
						<th>endDate</th>
						<th>roomType</th>
						<th>avalible</th>
						<th>totalCost</th>
						<th>canceled?</th>
						
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${users}">
						<tr>
							<td>${user.id}</td>
							<td>${user.hotelId}</td>
							<td>${user.roomId}</td>
							<td>${user.cityName}</td>
							<td>${user.userName}</td>
							<td>${user.startdate}</td>
							<td>${user.endDate}</td>
							<td>${user.roomType}</td>
							<td>${user.avalible}</td>
							<td>${user.totalCost}</td>
							<td>${user.canceled}</td>
							<td><a href="/set-zero?id=${user.id}"><span
									class="glyphicon glyphicon-floppy-remove"></span></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


</body>
</html>