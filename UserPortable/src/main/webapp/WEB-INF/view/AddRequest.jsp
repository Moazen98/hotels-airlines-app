<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center" id="tasksDiv">
		<h3>Add Requests</h3>
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
						
						<th>Delete</th>
						<th>accepted</th>
						
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
							<td>${user.enabled}</td>
							
							<td>${user.department}</td>
							
							
							<td><a href="/delete-request?id=${user.id}">delete</span></a></td>				
							<td><a href="/accepte?id=${user.id}"> accepted </a></td>
                           
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<spring:url value="/report/?type=xls" var="xlsURL"/>
	<spring:url value="/report/?type=pdf" var="pdfURL"/>
	<a href="${xlsURL}">Download Excel</a>
	<a href="${pdfURL}">Download PDF</a>

</body>
</html>