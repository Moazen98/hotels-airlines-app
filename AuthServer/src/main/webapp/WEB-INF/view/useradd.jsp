<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Register</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center">
		<h3>New Registration User</h3>
		<hr>
		<form class="form-horizontal" method="POST" action="add-usr">


			<input type="hidden" name="id" value="${user.id}" />

			<div class="form-group">
				<label class="control-label col-md-3">Username</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="username"
						value="${user.username}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">First Name</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="firstname"
						value="${user.firstname}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">Last Name</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="lastname"
						value="${user.lastname}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">Age</label>
				<div class="col-md-3">
					<input type="text" required class="form-control" name="age"
						value="${user.age}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">Password</label>
				<div class="col-md-7">
					<input type="password" required class="form-control"
						name="password" value="${user.password}" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3">email</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="email"
						value="${user.email}" />
				</div>
			</div>


			<div class="form-group">
				<label class="control-label col-md-3">Enabled</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="enabled"
						value="${user.enabled}" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3">Phone</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="phone"
						value="${user.phone}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">Role</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="role"
						value="${user.role}" />
				</div>
			</div>

			<div class="form-group ">
				<input type="submit" class="btn btn-primary" value="Register" />
			</div>
		</form>
	</div>

</body>
</html>