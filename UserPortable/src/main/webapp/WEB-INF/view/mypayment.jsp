<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>payment</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center">
		<h3>Payment</h3>
		<hr>
		<form class="form-horizontal" method="POST" action="get-check-info">
		 <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="form-group">
				<label class="control-label col-md-3">Email</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="useremail" 
						value="${user.useremail}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">Password</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="password"
						value="${user.password}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">Account Id</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="accountId"
						value="${user.accountId}" />
				</div>
			</div>
			
			
			<div class="form-group ">
				<input type="submit" class="btn btn-primary" value="Register" />
			</div>
		</form>
	</div>


</body>
</html>