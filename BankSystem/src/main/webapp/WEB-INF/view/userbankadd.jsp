<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center">
		<h3>Registration UserBank</h3>
		<hr>
		<form class="form-horizontal" method="POST" action="save-user-bank">


			<input type="hidden" name="idaccount" value="${userBank.id}" />
			<input type="hidden" name="register_date" value="${userBank.registerdate}" />
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<!--  for CSRF secirty -->
			
			<div class="form-group">
				<label class="control-label col-md-3">User Account Id</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="userid"
						value="${userBank.useraccountid}" />
				</div>
			</div>

            			<div class="form-group">
				<label class="control-label col-md-3">Current Balance</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="currentbalance"
						value="${userBank.currentbalance}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">Work Name</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="workname"
						value="${userBank.workname}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">Is Enabled</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="isenabled"
						value="${userBank.isenabled}" />
				</div>
			</div>
			
			
			
			
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="Register" />
			</div>


		</form>
	</div>


</body>
</html>