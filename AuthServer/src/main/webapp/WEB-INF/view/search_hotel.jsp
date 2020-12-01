<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>search hotel</title>
	<link rel="stylesheet" type="text/css" href="static/css/search_hotel.css">
</head>
<body>


<div class="total">
	<div class="container">


		<div class="left info">

			<div class="title">
				<h2>Search Page</h2>
                <p>please fill the feild to help us find the best choices for you</p>
			</div>

			<form class="info_form" action="/all-hotels" method="POST">
				<div class="country">
					<input type="text" name="country" placeholder="country">
				</div>


                <div class="dates">

				<div class="book">
					<label>Entering date</label>
					<br>
					<input type="date" name="book">
				</div>
				   
				<div class="leave">
					<label>Leaving date</label>
					<br>
					<input type="date" name="leave">
				</div>
			    
				</div>



				<div class="adult">
					<input type="text" name="adult" placeholder="number of adult">
				</div>
				<div class="childern">
					<input type="text" name="childern" placeholder="number of childern">
				</div>
				<div class="send">
					<input type="submit" value="Search Hotels">
				   <!--	<a href="/all-hotels">Search Hotels</a>-->
				</div>
			</form>
		</div>

		<div class="left image">
			<img src="static/img/house.svg">
		</div>
		
	</div>


</body>
</html>