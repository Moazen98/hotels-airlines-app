<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="static/img/icons/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="static/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="static/fonts/iconic/css/material-design-iconic-font.min.css">


<link rel="stylesheet" type="text/css" href="static/css/util.css">
<link rel="stylesheet" type="text/css" href="static/css/main.css">
</head>
<body>

	<div class="limiter">
		<div class="container-login100"
			style="background-image: url('static/img/bg3.jpg');">
			<div class="wrap-login100">
				<form class="login100-form validate-form" action="" >
					<span class="login100-form-logo"> <i
						class="zmdi zmdi-landscape"></i>
					</span> <span class="login100-form-title p-b-34 p-t-27"> Log in </span>

					<div class="wrap-input100 validate-input"
						data-validate="Enter username">
						<input class="input100" type="text" name="username"
							placeholder="Username"> <span class="focus-input100"
							data-placeholder="&#xf207;"></span>
					</div>

					<div class="wrap-input100 validate-input"
						data-validate="Enter password">
						<input class="input100" type="password" name="pass"
							placeholder="Password"> <span class="focus-input100"
							data-placeholder="&#xf191;"></span>
					</div>

					<div class="container-login100-form-btn">
						<button class="login100-form-btn">Login</button>
					</div>

					<div class="text-center p-t-90">
						<a class="txt1" href="/register-user"> Don't Have an account? </a> <a class="txt1"
							href="/index"> Home Page? </a>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>