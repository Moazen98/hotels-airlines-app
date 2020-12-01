 <!-- 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" url="http://www.springframework.org/tages"%>
<%@ taglib prefix="c" url="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users Report</title>
</head>
<body>

	<table border="1">
		<thead>
			<tr>
				<td>ID</td>
				<td>USERNAME</td>
				<td>PASSWORD</td>
				<td>ENABLE</td>
				<td>AUTHORITY</td>
			</tr>
		</thead>

		<tbody>
			<c:forEach item="${userList}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.userName}</td>
					<td>${user.password}</td>
					<td>${user.enable}</td>
					<td>${user.authority}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<spring:url value="/report/?type=xls" var="xlsURL"/>
	<spring:url value="/report/?type=pdf" var="pdfURL"/>
	<a href="${xlsURL}">Download Excel</a>
	<a href="${pdfURL}">Download PDF</a>
</body>
</html>
-->