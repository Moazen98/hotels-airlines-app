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
				<label class="control-label col-md-3">airplan_id</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="airplan_id"
						value="${plane.airplan_id}" />
				</div>
			</div>

            			<div class="form-group">
				<label class="control-label col-md-3">date</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="date"
						value="${plane.date}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">depart_time</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="depart_time"
						value="${plane.depart_time}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">arrive_time</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="arrive_time"
						value="${plane.arrive_time}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">Country_of_departure</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="country_of_departure"
						value="${plane.country_of_departure}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">Country_of_arrival</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="country_of_arrival"
						value="${plane.country_of_arrival}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">img</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="img"
						value="${plane.img}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">number_of_Economy_booking</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="number_of_Economy_booking"
						value="${plane.number_of_Economy_booking}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">number_of_Perimume_Economy_bookingy</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="number_of_Perimume_Economy_bookingy"
						value="${plane.number_of_Perimume_Economy_booking}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">number_of_Business_booking</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="number_of_Business_booking"
						value="${plane.number_of_Business_booking}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">number_of_First_Class_booking</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="number_of_First_Class_booking"
						value="${plane.number_of_First_Class_booking}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">price_of_Economy_tecket</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="price_of_Economy_tecket"
						value="${plane.price_of_Economy_tecket}" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-md-3">price_of_Perimume_Economy_tecket</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="price_of_Perimume_Economy_tecket"
						value="${plane.price_of_Perimume_Economy_tecket}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">price_of_Business_tecket</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="price_of_Business_tecket"
						value="${plane.price_of_Business_tecket}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-3">price_of_First_Class_tecket</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="price_of_First_Class_tecket"
						value="${plane.price_of_First_Class_tecket}" />
				</div>
			</div>
			
			
			
			
			<div class="form-group">
				<label class="control-label col-md-3">price_of_Economy_tecket_for_child</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="price_of_Economy_tecket_for_child"
						value="${plane.price_of_Economy_tecket_for_child}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">price_of_Perimume_Economy_tecket_for_child</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="price_of_Perimume_Economy_tecket_for_child"
						value="${plane.price_of_Perimume_Economy_tecket_for_child}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">price_of_Business_tecket_for_child</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="price_of_Business_tecket_for_child"
						value="${plane.price_of_Business_tecket_for_child}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">price_of_First_Class_tecket_for_child</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="price_of_First_Class_tecket_for_child"
						value="${plane.price_of_First_Class_tecket_for_child}" />
				</div>
			</div>
			
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="Register" />
			</div>


		</form>
	</div>


</body>
