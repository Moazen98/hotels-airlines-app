<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Planes Information</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center" id="tasksDiv">
		<h3>Planes Information</h3>
		<hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Size</th>
						<th>Capacity</th>
						<th>Type</th>
						<th>Delete</th>
						<th>Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="plane" items="${planes}">
						<tr>
							<td>${plane.id}</td>
							<td>${plane.name}</td>
							<td>${plane.size}</td>
							<td>${plane.capacity}</td>
							<td>${plane.size}</td>
							<td><a href="/delete-plane?id=${plane.id}"><span
									class="glyphicon glyphicon-floppy-remove"></span></a></td>
							<td><a href="/edit-page?id=${plane.id}"><span
									class="glyphicon glyphicon-list-alt"></span></a></td>
							
						
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	

</body>
</html>