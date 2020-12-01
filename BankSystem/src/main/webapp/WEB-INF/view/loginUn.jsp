<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="static/css/loginUn.css">
</head>
<body>

  <div class="total">
	<div class="container">

		<div class="left image">
			<img  src="static/img/undraw9.png">
		</div>


		<div class="left form">
		  <div class="sub">
		  	<form action="login-user_cheak" >
			<div class="log">
				<h2>Login</h2>	
			</div>
			<div class="username">
				<input type="text" name="username" placeholder="username">
			</div>
			<div class="password">
				<input type="password" name="password" placeholder="password">
			</div>
			<div class="next">
			<!--	<a href="login.html">Login</a>  -->
			<input type="submit" name="" value="Login">
			</div>
			<div class="sing">
				<p>Don't have an Account <a href="/register-user">sign up</a></p> 
				
			</div>
			</form>
		  </div>
		</div>
		

	</div>
  </div>

</body>
</html>