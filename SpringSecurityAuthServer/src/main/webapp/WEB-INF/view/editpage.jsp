<%@ page import="com.zeon.HotelReservation.Model.*"%>

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
		<h3>Update Plane</h3>
		<hr>
		<form class="form-horizontal" method="POST" action="update">
			<input type="hidden" name="id" value="${planeedit.id}" />	
			<input type="hidden" name="version" value="${planeedit.version}" />	
			
			<div class="form-group">
				<label class="control-label col-md-3">Name</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="name"
						value="${planeedit.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">Size</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="size"
						value="${planeedit.size}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">Capacity</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="capacity"
						value="${planeedit.capacity}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">Type</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="type"
						value="${planeedit.type}" />
				</div>
			</div>
			
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="Update" />
			</div>
		</form>
	</div>
</body>
</html>