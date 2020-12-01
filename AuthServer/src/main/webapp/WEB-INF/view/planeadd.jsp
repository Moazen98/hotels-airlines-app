<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Register</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center">
		<h3>New Registration Plane</h3>
		<hr>
		<form class="form-horizontal" method="POST" action="save-plane">


			<input type="hidden" name="id" value="${plane.id}" />


			<div class="form-group">
				<label class="control-label col-md-3">Type</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="type"
						value="${plane.type}" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3">capacity</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="capacity"
						value="${plane.capacity}" />
				</div>
			</div>


			<div class="form-group">
				<label class="control-label col-md-3">Size</label>
				<div class="col-md-7">
					<input type="password" required class="form-control" name="size"
						value="${plane.size}" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3">Name</label>
				<div class="col-md-7">
					<input type="password" required class="form-control" name="name"
						value="${plane.name}" />
				</div>
			</div>

			<div class="form-group ">
				<input type="submit" class="btn btn-primary" value="Register" />
			</div>
		</form>
	</div>


</body>
</html>