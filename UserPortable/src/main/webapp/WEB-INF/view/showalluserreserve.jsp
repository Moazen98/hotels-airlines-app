
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
								<th class="column1">id</th>

								<th class="column1">roomId</th>
								<th class="column2">cityName</th>
								<th class="column3">userName</th>
								<th class="column3">startdate</th>
								<th class="column3">endDate</th>
								<th class="column2">roomType</th>
								<th class="column1">avalible</th>
								<th class="column1">totalCost</th>
								<th class="column1">canceled?</th>
								<th class="column2">Delete</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="res" items="${reserved}">
								<tr>
									<td class="column1">${res.id}</td>

									<td class="column1">${res.roomId}</td>
									<td class="column2">${res.cityName}</td>
									<td class="column3">${res.userName}</td>
									<td class="column3">${res.startdate}</td>
									<td class="column3">${res.endDate}</td>
									<td class="column2">${res.roomType}</td>
									<td class="column1">${res.avalible}</td>
									<td class="column1">${res.totalCost}</td>
									<td class="column1">${res.canceled}</td>
									<td class="column2"><a href="/delete-reserve?id=${res.id}"><span></span><i
											class="fa fa-times"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<spring:url value="/report-user-hotel/?type=xls" var="xlsURL" />
			<spring:url value="/report-user-hotel/?type=pdf" var="pdfURL" />
			<a href="${xlsURL}">Download Excel</a> <a href="${pdfURL}">Download
				PDF</a>
		</div>

	</div>





</body>
</html>