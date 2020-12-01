<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.zeon.UserPortable.Model.*"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.Iterator"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Hotel</title>

<!-- include bootstrap css -->
<link rel="stylesheet" type="text/css"
	href="static/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="static/css/AllHotels.css">


<!-- include bootstrap js -->
<script type="text/javascript" src="static/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
</head>
<body>

	<%
		Hotel hhh = new Hotel();
		List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels");
	%>


	<!-- start navbar -->


	<div class="nv">
		<nav class="navbar navbar-inverse ">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/index">ZeonCompany</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">

				<ul class="nav navbar-nav navbar-right">

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Sort Resault <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/order-hotel-by-rate-pro">Top Ratting</a></li>
							<li><a href="/order-hotel-by-rate-des">Lowest Ratting</a></li>
							<li><a href="/order-hotel-by-name">By Name</a></li>
							<li><a href="/order-hotel-by-price">By Price</a></li>

						</ul></li>

				</ul>

			</div>
		</div>
		</nav>
	</div>

	<!-- end navbar -->


	<div class="content">
		<div class="all">



			<div class="res">
				<article>
				<h2 class="hd">These are all the resaults to your infomation
					that you enter</h2>
				<span class="ll"> </span> </article>
			</div>


			<div class="travels">
				<%
					for (int i = 0; i < hotels.size(); i++) {
				%>
				<div class="trip">

					<%
						Set<String> set = new HashSet<String>();
							set = hotels.get(i).getMedia();
							Iterator<String> it = set.iterator();
							String s = it.next();

							//Set<String> set = new HashSet<String>();
							//set=hotels.get(i).getMedia();
					%>

					<div class="image">
						<img src="<%out.print(s);%>">
					</div>

					<div class="info">
						<div class="name">
							<h2>
								<%
									out.print(hotels.get(i).getName());
								%>
							</h2>
							<p>
								<%
									for (int j = 0; j < hotels.get(i).getRatting(); j++) {
								%>
								<i class="fa fa-star"></i>

								<%
									}
								%>
							</p>
						</div>

						<div class="address">
							<p>
								<%
									out.print(hotels.get(i).getAddress().getAddress());
								%>
							</p>
						</div>

						<div class="contact">
							<p>
								<%
									out.print(hotels.get(i).getContact().getEmail());
								%>
							</p>
						</div>

						<div class="discreption">
							<p>
								<%
									out.print(hotels.get(i).getDescription());
								%>
							</p>
						</div>

						<div class="cost">
							<p>
								tatal cost: <span>200$</span>
							</p>
						</div>

						<a href="/hotel-info?id=<%out.print(hotels.get(i).getid());%>">
							See More </a>
					</div>

				</div>

				<%
					}
				%>


			</div>

			<!-- end back -->

		</div>
	</div>



	<!-- start footer -->

	<footer>
	<div class="footer-logo">
		<img src="static/img/logo-alt.png" alt="logo">
	</div>

	<div class="cr">
		<p>
			COPYRIGHT &copy;2020. ALL RIGHTS RESERVED. DESIGNED BY <span>
				Zeon company</span>
		</p>
	</div>
	</footer>

	<!-- end footer -->



</body>
</html>