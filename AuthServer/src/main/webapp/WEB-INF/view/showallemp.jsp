<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Information</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center" id="tasksDiv">
		<h3>Employees Information</h3>
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

					</tr>
				</thead>
				<tbody>
					<c:forEach var="emps" items="${emp}">
						<tr>
							<td>${emps.id}</td>
							<td>${emps.username}</td>
							<td>${emps.firstname}</td>
							<td>${emps.lastname}</td>
							<td>${emps.age}</td>
							<td>${emps.enabled}</td>
							<td>${emps.department}</td>
							<td>${emps.email}</td>
							<td>${emps.accepted}</td>
							<td>${emps.phone}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


</body>
</html>