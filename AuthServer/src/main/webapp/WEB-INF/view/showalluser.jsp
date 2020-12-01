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
		<h3>Users Information</h3>
		<hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>UserName</th>
						<th>First Name</th>
						<th>LastName</th>
						<th>Age</th>
						<th>Enable</th>
						<th>Department</th>
						<th>Email</th>
						<th>accepted</th>
						<th>Phone</th>
						<th>Delete</th>
						<th>Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${users}">
						<tr>
							<td>${user.id}</td>
							<td>${user.username}</td>
							<td>${user.firstname}</td>
							<td>${user.lastname}</td>
							<td>${user.age}</td>
							<td><a href="/del-usr?id=${user.id}"><span
									class="glyphicon glyphicon-floppy-remove"></span></a></td>
							<td><a href="/get-page-edit?id=${user.id}"><span
									class="glyphicon glyphicon-list-alt"></span></a></td>
							<td>${user.enabled}</td>
							<td>${user.email}</td>
							<td>${user.accepted}</td>
							<td>${user.phone}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


</body>
</html>