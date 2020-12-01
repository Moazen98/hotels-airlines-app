<!DOCTYPE html>
<html>
<head>
<title>Search Hotel</title>
<link rel="stylesheet" type="text/css" href="static/css/search_hotel_new.css">
</head>
<body>

	<div class="container">
		<div class="content">


			<div class="info">

				<div class="title">
					<h2>Search Page</h2>
					<p>please fill these feilds to help us find the best choices
						for you</p>
				</div>

				<form class="info_form" method="POST" action="/search-hotel-city">


					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
						
						
					<div class="country">
						<input type="text" name="city" placeholder="country">
					</div>


				
					<div class="send">
						<input type="submit" value="Search Hotels">
						<a href="/index">Home Page</a>
					</div>
				</form>
			</div>



		</div>
	</div>

</body>
</html>