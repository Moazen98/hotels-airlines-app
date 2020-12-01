<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Please Insert Your Username and Password</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="static/css/css.css" rel="stylesheet">
</head>
<body>

		<div class="container text-center">
			<h3>User Login</h3>
			<hr>
			<form class="form-horizontal" method="POST" action="/login-user">
				<c:if test="${not empty error}">
					<div class="alert alert-danger">
						<c:out value="${error}"></c:out>
					</div>
				</c:if>
				<div class="form-group">
					<label class="control-label col-md-3">Username</label>
					<div class="col-md-7">
						<input type="text" class="form-control" name="username"
						value="${user.username}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">Password</label>
					<div class="col-md-7">
						<input type="password" class="form-control" name="password"
						value="${user.password}"/>
					</div>
				</div>
				<div class="form-group ">
					<input type="submit" class="btn btn-primary" value="login" />
				</div>
			</form>
		</div>
	
	
</body>

</html>