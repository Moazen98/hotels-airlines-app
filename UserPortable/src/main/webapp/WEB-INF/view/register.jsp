<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register1</title>
<link rel="stylesheet" type="text/css" href="static/css/register1.css">
</head>
<body>

	<div class="total">
		<div class="container">

			<div class="left image">
				<img src="static/img/city2.jpg">
			</div>


			<div class="left form">

				<div class="title">
					<h2>Registration</h2>
				</div>

				<div class="content">

					<form method="GET" action="/add-usr">
						<input type="text" name="username" placeholder="username" required>
						<input type="text" name="lastname" placeholder="lastname" required>
						<input type="text" name="email" placeholder="email" required>
						<input type="text" name="phone" placeholder="phone" required>

						<input type="password" id="password" name="password"
							placeholder="password" required onkeyup='Check();'> <input
							type="password" id="confirm" name="confirm" placeholder="confirm"
							required onkeyup='Check();'>
						<p id='message'></p>
						<input type="submit" name="submit" value="registration"
							id="submit" disabled>
					</form>
					<div class="links">
						<a href="/login-user">You have an account?</a> 
						<a href="/index">Home Page?</a>
					</div>
				</div>

			</div>


		</div>
	</div>

	<script>
		function Check() {

			var pass = document.querySelector("form input[name='password']");
			var conf = document.querySelector("form input[name='confirm']");
			console.log(pass);
			console.log(conf);
			var valp = document.getElementById("password").value;
			var valc = document.getElementById("confirm").value;

			console.log(valp);
			console.log(valc);

			if (valp == valc) {
				document.getElementById('message').style.color = 'green';
				document.getElementById('message').style.backgroundColor = '#8f8';
				document.getElementById('message').style.display = 'block';
				document.getElementById('message').innerHTML = 'matching';
				document.getElementById('submit').disabled = false;
			} else {
				document.getElementById('message').style.color = 'red';
				document.getElementById('message').style.backgroundColor = '#f88';
				document.getElementById('message').style.display = 'block';
				document.getElementById('message').innerHTML = 'dismatching';
				document.getElementById('submit').disabled = true;
			}

		}
	</script>
</body>
</html>