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
		<h3>User Information</h3>
		<hr>
		<form class="form-horizontal" method="POST" action="save-user">
			
			<div class="form-group">
				<label class="control-label col-md-3">id</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="id" 
						value="${user.id}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">first name</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="firstname" 
						value="${user.firstname}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">last name</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="lastname"
						value="${user.lastname}"  />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">father name</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="fathername"
						value="${user.fathername}"  />
				</div>
			</div>

			
			<div class="form-group">
				<label class="control-label col-md-3">mother name</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="mothername"
						value="${user.mothername}"  />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">international number</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="international_number"
						value="${user.international_number}" />
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
				<label class="control-label col-md-3">phone number</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="phone"
						value="${user.phone}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">room type</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" placeholder="single room or dual room or threeperson room" name="room_type"
						value="${user.room_type}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">number of days</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" placeholder="single room or dual room or threeperson room" name="number_of_days"
						value="${user.number_of_days}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">hotel_id</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="hotel_id"
						value="${user.hotel_id}" />
				</div>
			</div>
			
		
						
			<div class="form-group">
				<label class="control-label col-md-3">start_day</label>
				<div class="col-md-7">
					<input type="date" required class="form-control" name="start_day"
						value="${user.start_day}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">end_day</label>
				<div class="col-md-7">
					<input type="date" required class="form-control" name="last_day"
						value="${user.last_day}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">number of child</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="child_number"
						value="${user.child_number}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">adult_number</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="adult_number"
						value="${user.adult_number}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">totalcost</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="totalcost"
						value="${user.totalcost}" />
				</div>
			</div>
			
			
			<div class="form-group ">
				<input type="submit" class="btn btn-primary" value="Register" />
			</div>
		</form>
	</div>


</body>
</html>