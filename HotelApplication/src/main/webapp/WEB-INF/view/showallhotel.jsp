<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel Information</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>


	<div class="container text-center" id="tasksDiv">
		<h3>Users Information</h3>
		<hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						
						<th>Name</th>
						<th>country</th>
						<th>about hotel</th>	
						<th>email</th>
						<th>phone</th>	
						<th> image link</th>
						<th> address</th>	
							
						<th>number of single room</th>
						<th>number of dual room</th>
						<th>number of three person room</th>
						<th>number of booking single room </th>	
						<th>number of booking dual room</th>
						<th>number of booking three person room</th>	
						<th>price of single room</th>
						<th>price of dual room</th>		
						<th>price of three person room</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach var="hotel" items="${hotels}">
						<tr>
							
							<td>${hotel.name}</td>
							<td>${hotel.country}</td>
							<td>${hotel.about_hotel}</td>
							<td>${hotel.email}</td>
							<td>${hotel.phone}</td>
							<td> ${hotel.img} </td>
							<td>${hotel.address}</td>
							
							
							
							<td>${hotel.number_of_room_for_own_person}</td>
							<td>${hotel.number_of_room_for_two_person}</td>
							<td>${hotel.number_of_room_for_three_person}</td>
							
							<td>${hotel.number_of_room_for_own_person_booking}</td>
							<td>${hotel.number_of_room_for_two_person_booking}</td>
							<td>${hotel.number_of_room_for_three_person_booking}</td>
							
							<td>${hotel.price_of_personal_room}</td>
							<td>${hotel.price_of_dual_room}</td>
							<td>${hotel.price_of_threeperson_room}</td>
							
							<td><a href="/delete-hotel?id=${hotel.id}"><span
									class="glyphicon glyphicon-floppy-remove"></span></a></td>
							<td><a href="/edit-hotel?id=${hotel.id}"><span
									class="glyphicon glyphicon-list-alt"></span></a></td>
							
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	
</body>
</html>