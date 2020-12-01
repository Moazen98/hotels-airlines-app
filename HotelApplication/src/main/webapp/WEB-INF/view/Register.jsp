<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Hotel</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center">
		<h3>New Registration</h3>
		<hr>
		<form class="form-horizontal" method="POST" action="save-hotel">
		
		
			<input type="hidden" name="id" value="${hotel.id}"  />
			<input type="hidden" name="version" value="${hotel.version}"  />
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<!--  for CSRF secirty -->
			
			<div class="form-group">
				<label class="control-label col-md-3">name</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="name" 
						value="${hotel.name}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">country</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="country"
						value="${hotel.country}"  />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">phone</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="phone"
						value="${hotel.phone}" />
				</div>
			</div>

			
			<div class="form-group">
				<label class="control-label col-md-3">email</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="email"
						value="${hotel.email}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">address</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="address"
						value="${hotel.address}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">about hotel</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="about_hotel"
						value="${hotel.about_hotel}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3"> hotel degree</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="degree" placeholder="for example 5_star hotel"
						value="${hotel.degree}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">image link</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="img"
						value="${hotel.img}" />
				</div>
			</div>
			
			
			
			
			
			
			
			
			
			<div class="form-group">
				<label class="control-label col-md-3">number of room for own person</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="number_of_room_for_own_person"
						value="${hotel.number_of_room_for_own_person}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">number_of_room_for_two_person</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="number_of_room_for_two_person"
						value="${hotel.number_of_room_for_two_person}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">number_of_room_for_three_person</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="number_of_room_for_three_person"
						value="${hotel.number_of_room_for_three_person}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">number_of_room_for_own_person_booking</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="number_of_room_for_own_person_booking"
						value="${hotel.number_of_room_for_own_person_booking}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">number_of_room_for_two_person_booking</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="number_of_room_for_two_person_booking"
						value="${hotel.number_of_room_for_two_person_booking}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">number_of_room_for_three_person_booking</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="number_of_room_for_three_person_booking"
						value="${hotel.number_of_room_for_three_person_booking}" />
				</div>
			</div>
			
			
			
			<div class="form-group">
				<label class="control-label col-md-3">price_of_personal_room</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="price_of_personal_room"
						value="${hotel.price_of_personal_room}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">price_of_dual_room</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="price_of_dual_room"
						value="${hotel.price_of_dual_room}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">price_of_threeperson_room</label>
				<div class="col-md-7">
					<input type="text" required class="form-control" name="price_of_threeperson_room"
						value="${hotel.price_of_threeperson_room}" />
				</div>
			</div>
			
			
			
			
			
			<div class="form-group ">
				<input type="submit" class="btn btn-primary" value="Register" />
			</div>
		</form>
	</div>


</body>
</html>