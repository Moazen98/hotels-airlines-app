<%@ page import="com.zeon.BankSystem.Model.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Page</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center">
		<h3>User Bank Edit Page</h3>
		<hr>
		<form class="form-horizontal" method="POST" action="updateplane">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<!--  for CSRF secirty -->
			<input type="hidden" name="idaccount" value="${userbankedit.idaccount}" />

			<div class="form-group">
				<label class="control-label col-md-3">User Account Id</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="userid"
						value="${userbankedit.userid}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">Current Balance</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="currentbalance"
						value="${userbankedit.currentbalance}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">Work name</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="workname"
						value="${userbankedit.workname}" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3">Is Enabled</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="isenabled"
						value="${userbankedit.isenabled}" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3">Register Date</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="registerdate"
						value="${userbankedit.registerdate}" />
				</div>
			</div>



			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="Update" />
			</div>
		</form>
	</div>
</body>
</html>