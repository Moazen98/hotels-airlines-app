<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Information</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center" id="tasksDiv">
		<h3>Bank System Information</h3>
		<hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Id Account</th>
						<th>User Account Id</th>
						<th>Current Balance</th>
						<th>Work Name</th>
						<th>Is Enabled</th>
						<th>Register Date</th>
						<th>Version</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="bank" items="${userBank}">
						<tr>
							<td>${bank.idaccount}</td>
							<td>${bank.userid}</td>
							<td>${bank.currentbalance}</td>
							<td>${bank.workname}</td>
							<td>${bank.isenabled}</td>
							<td>${bank.registerdate}</td> 
							<td>${bank.version}</td>
										
							
							<td><a href="/delete-user-bank?id=${bank.idaccount}"><span
									class="glyphicon glyphicon-floppy-remove"></span></a></td>
							<td><a href="/edit-page-bank?id=${bank.idaccount}"><span
									class="glyphicon glyphicon-list-alt"></span></a></td>
							
						
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	

</body>
</html>