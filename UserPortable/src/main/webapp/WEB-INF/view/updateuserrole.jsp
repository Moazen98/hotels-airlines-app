<%@ page import="com.gl.patientsService.modal.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Users Role</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center">
		<h3>Update User Roles</h3>
		<hr>
		<form class="form-horizontal" method="POST" action="updaterole">
			<input type="hidden" name="id" value="${userrole.id}" /> 
				<input type="hidden" name="version" value="${userrole.version}"/>
			<div class="form-group">
				<label class="control-label col-md-3">Username</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="username"
						value="${userrole.username}" />
				</div>
			</div>



			<div class="form-group">
				<label class="control-label col-md-3">Role</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="roles"
						value="${userrole.roles}" />
				</div>
			</div>



			<div class="form-group">
				<label class="control-label col-md-3">Permission</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="permissions"
						value="${userrole.permissions}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">Phone</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="phone"
						value="${userrole.phone}" />
				</div>
			</div>
			
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="updaterole" />
			</div>
		</form>
	</div>
</body>
</html>