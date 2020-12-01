<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="static/css/registerUn.css">

</head>
<body>



	<div class="total">
		<div class="container">
			
            <div class="left image">
			    <img  src="static/img/undraw8.png">
		    </div>

		    <div class="left form">

			    <div class="sub">

			    	<form method="POST" action="add-usr">
			    	
			    	<input type="hidden" name="id" value="${user.id}" />
			    	<input type="hidden" name="id" value="${user.enabled}" />
			    	<input type="hidden" name="id" value="${user.role}" />
			    	<input type="hidden" name="id" value="${user.age}" />
			    	<input type="hidden" name="id" value="${user.accepted}" />

			    	<div class="reg">
				        <h2>Register</h2>	
			        </div>

			        <div class="username">
				        <input type="text" name="username" placeholder="User Name" value="${user.username}">
			        </div>

			        <div class="firstname">
				        <input type="text" name="firstname" placeholder="First Name" value="${user.firstname}">
			        </div>

			        <div class="lastname">
				        <input type="text" name="lastname" placeholder="Last Name" value="${user.lastname}">
			        </div>

			        <div class="phone">
				        <input type="text" name="phone" placeholder="Phone Number" value="${user.phone}">
			        </div>

			        <div class="email">
				        <input type="text" name="email" placeholder="Email" value="${user.email}">
			        </div>

			        <div class="password">
				        <input type="password" name="password" placeholder="Password" value="${user.password}">
			        </div>

			        <div class="next">
				      <!--  <a href="login.html">Register</a> -->
				      <input type="submit" name="submit" value="Register">
			        </div>
			

			    	</form>

			    </div> 
		    </div>

		    <div class="left image">
			    <img  src="static/img/undraw2.png">
		    </div>
           

		</div>
	</div>



</body>
</html>