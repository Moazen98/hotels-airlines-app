
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Show All User Reserve</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css"
	href="static/fonts/font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="static/css/util.css">
<link rel="stylesheet" type="text/css"
	href="static/css/showAllUserReserved.css">

</head>

<body>


	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100">
					<table>
						<thead>
							<tr class="table100-head">
								<th class="column1">Id</th>
								<th class="column1">planelId</th>
								<th class="column2">userName</th>
								<th class="column2">planeType</th>
								<th class="column1">avalible</th>
								<th class="column1">totalCost</th>
								<th class="column1">canceled?</th>
								<th class="column2">Location</th>
								<th class="column2">Destination</th>
								<th class="column1">numOfPerson</th>
								<th class="column1">accountId</th>
								<th class="column1">Book Taxi</th>
								<th class="column1">Delete</th>


							</tr>
						</thead>
						<tbody>
							<c:forEach var="res" items="${reserved}">
								<tr>
									<td class="column1">${res.id}</td>
									<td class="column1">${res.planelId}</td>
									<td class="column1">${res.userName}</td>
									<td class="column1">${res.planeType}</td>
									<td class="column1">${res.avalible}</td>
									<td class="column1">${res.totalCost}</td>
									<td class="column1">${res.canceled}</td>
									<td class="column1">${res.location}</td>
									<td class="column1">${res.destination}</td>
									<td class="column1">${res.num}</td>
									<td class="column1">${res.accountId}</td>
									<td class="column1">${res.taxiReserve}</td>

									<td><a
										href="/delete-reserve-plane?id=${res.id}&userName=${res.userName}&location=${res.location}&destination=${res.destination}&taxiReserve=${res.taxiReserve}"><span></span><i
											class="fa fa-times"></i></a></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<spring:url value="/report-user-plane/?type=xls" var="xlsURL" />
			<spring:url value="/report-user-plane/?type=pdf" var="pdfURL" />
			<a href="${xlsURL}">Download Excel</a> <a href="${pdfURL}">Download
				PDF</a>
		</div>

	</div>



</body>
</html>
