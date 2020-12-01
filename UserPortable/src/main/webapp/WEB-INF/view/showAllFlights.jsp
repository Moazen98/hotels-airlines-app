<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.zeon.UserPortable.Model.Plane"%>
<%@page import="com.zeon.UserPortable.Model.Coordinates"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show All Flight</title>

<link rel="stylesheet" type="text/css"
	href="static/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="static/css/showAllFlights.css">

<link href="static/css/style.css" rel="stylesheet" type="text/css" media="all">
<link href="static/css/owl.carousel.css" rel="stylesheet" type="text/css" media="all">

<script type="text/javascript" src="static/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>

</head>
<body>
<div class="overlay">


<%
					Coordinates coord = new Coordinates();
					coord = (Coordinates) request.getAttribute("coord");
					Plane[] planes = new Plane[20];
					planes = (Plane[]) request.getAttribute("total");
					String deg = (String) request.getAttribute("degree");
					
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
							data-toggle="dropdown" href="#">Sort Result <span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Lowest Price</a></li>
								<li><a href="#">Shortest Duration</a></li>
								<li><a href="#">Earliest Departure</a></li>
								<li><a href="#">Latest Departure</a></li>
								<li><a href="#">Earliest Arrive</a></li>
								<li><a href="#">Latest Arrive</a></li>
							</ul></li>

					</ul>

				</div>
			</div>
		</nav>
	</div>


		
	<!-- start weater -->
	<div class="weather">

            <div class="agileits_w3layouts_main_grid_left">
                <div class="wthree_main_grid agileinfo_main_grid">
                    <div class="w3ls_main_grid">
                        <div class="agileits_main_grid_left">
                            <div class="agileits_main_grid_leftl">
                                <h3>24<span>°</span></h3>
                            </div>
                            <div class="agileits_main_grid_leftr">
                                <h4>Sunny</h4>
                                <p>Today </p>,<p> <%out.print(planes[0].getDestination()); %></p>
                            </div>
                            <div class="clear"> </div>
                        </div>

                        <div class="clear"> </div>
                    </div>
                    <div class="w3l_main_grid_list">
                        <ul id="owl-demo" class="owl-carousel owl-theme" style="opacity: 1; display: block;">
                            <div class="owl-wrapper-outer">
                                <div class="owl-wrapper" style="width: 100%; left: 0px; transition: all 1000ms ease 0s; transform: translate3d(0px, 0px, 0px);">
                                    <div class="owl-item" style="width: 118px;"><li><h4>Max</h4>
                                            <div class="w3layouts_icon">
                                                <img src="static/images/1.png" alt=" " class="w3_img_responsive">
                                            </div>
                                            <h5> <span id="max">15</span>° </h5>
                                            
                                        </li></div>
                                        
                                        <div class="owl-item" style="width: 118px;"><li><h4>Min</h4>
                                            <div class="w3layouts_icon">
                                                <img src="static/images/8.png" alt=" " class="w3_img_responsive">
                                            </div>
                                                <h5> <span id="min">15</span>° </h5>
                                        </li></div>
                                        
                                        <div class="owl-item" style="width: 118px;"><li><h4>wind</h4>
                                            <div class="w3layouts_icon">
                                                <img src="static/images/12.png" alt=" " class="w3_img_responsive" style="width: 40px;">
                                            </div>
                                                <h5> <span id="wind">15</span>° </h5>
                                        </li></div>
                                        
                                        
                                    
                                   </div>
                            </div>






                        </ul>
                    </div>
                </div>
            </div>


        </div>

	
	<!-- end weater -->
	
	<!-- end navbar -->
	<div class="content">

		<div class="all">

			<div class="res">
				<article>
					<h2 class="hd">These are all the resaults to your infomation
						that you enter</h2>
					<span class="ll"></span>
				</article>
			</div>

			<div class="travels">


				<%
					
					for (int i = 0; i < planes.length; i++) {
						if (planes[i] == null) {
							break;
						}
				%>

				<div class="trip">
					<div class="left logo">
						<img src=<%out.print(planes[i].getImg());%>>
					</div>
					<div class="left section">
						<h2>
							from
							<%
							out.print(planes[i].getLocation());
						%>
							to
							<%
							out.print(planes[i].getDestination());
						%>
						</h2>
						<p>
							<%
								out.print(planes[i].getDate());
							%>
						</p>
						<p>
							started at
							<%
							out.print(planes[i].getDate());
						%>
							PM and it take about
							<%
							out.print(planes[i].getTakeOff());
						%>
						</p>
					</div>

					<div class="left price">
						<p>
							Total Price: <span>500$</span>
						</p>
					</div>
					<div class="left more">
						<button class="show">Show More</button>
					</div>
				</div>

				<div class="bg-modal">

					<form action="/payment-plane">
						<div class="temp">
							<div class="payment-modal">
								<h2>payment section</h2>
								<div class="g-info">
									<span class="closet" onclick="close()">X</span>
									<%
										if (deg.equals("Economy")) {
									%>
									<input type="hidden" name="price"
										value="<%out.print(planes[i].getEconomy_price());%>" />
									<%
										}
									%>

									<%
										if (deg.equals("Perimume")) {
									%>
									<input type="hidden" name="price"
										value="<%out.print(planes[i].getPremium_economy_price());%>" />
									<%
										}
									%>

									<%
										if (deg.equals("Business")) {
									%>
									<input type="hidden" name="price"
										value="<%out.print(planes[i].getBusiness_price());%>" />
									<%
										}
									%>

									<%
										if (deg.equals("Class")) {
									%>
									<input type="hidden" name="price"
										value="<%out.print(planes[i].getFirst_class_price());%>" />
									<%
										}
									%>

									<input type="hidden" name="book" class="book" value="0">
									<input type="hidden" name="plane_id"
										value="<%out.print(planes[i].getId());%>" /> <input
										type="hidden" name="location"
										value="<%out.print(planes[i].getLocation());%>" /> <input
										type="hidden" name="destination"
										value="<%out.print(planes[i].getDestination());%>" />
										<input
										type="hidden" name="date" value="<%out.print(planes[i].getDate());%>">
										
										<input
										type="hidden" name="arriveTime" value="<%out.print(planes[i].getArriveTime());%>">
										
									<div class="fields">
										<div class="section">
											<h3>User Name</h3>
											<input type="text" name="username" required>
										</div>

										<div class="section">
											<h3>User Email</h3>
											<input type="text" name="useremail" required>
										</div>

										<div class="section">
											<h3>Account Id</h3>
											<input type="text" name="accountId" required>
										</div>

										<div class="section">
											<h3>Account Password</h3>
											<input type="password" name="password" required>
										</div>
										<a onclick="setBook()">Book A Taxi</a>
										<input value="Pay" type="submit">

									</div>


								</div>
							</div>
						</div>
					</form>
				</div>

				
			</div>

			<%
				}
			%>


		</div>

		<!-- end back -->

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
</div>
	<!-- end footer -->
	<script>
	
	var inp =document.querySelector("form input[name='book']");
    console.log(inp);
    function setBook() {
         inp.setAttribute("value", "1");
         console.log(inp);
    }
    

	var ourGalary=document.body.querySelectorAll('.travels .trip .more button');
	
	console.log(ourGalary);
	ourGalary.forEach(button=>{
	button.addEventListener('click',(e)=>{
	    
	    if(e.target.className==='show')
	    {  
	    	document.body.querySelector('.bg-modal').style.display ="flex";
	        document.body.querySelector('.temp').style.display ="block"; 
        }
	         
	});
	});
	
	// when click on x in any pupop
	document.addEventListener('click',(e)=>{
	  if(e.target.className==='closet')
	    { 
		  document.body.querySelector('.bg-modal').style.display ="none";
	      document.body.querySelector('.temp').style.display ="none";
          document.body.querySelector('.another').style.display ="none"; 
	    }
        
	});
	

</script>
<script>
            axios.get('http://api.openweathermap.org/data/2.5/weather?lat=<%out.print(coord.getLatitude());%>&lon=<%out.print(coord.getLongtitude());%>&units=metric&appid=d1f2d24005e7ac2614e5bad1a8d963d4')
                    .then((response) => {
                        // console.log(response.status);
                        //console.log(response.data.main.temp);

                        document.getElementById('max').innerHTML = response.data.main.feels_like;
                        document.getElementById('min').innerHTML = response.data.main.temp_min;
                       document.getElementById('wind').innerHTML = response.data.wind.speed;

                    });

</script>
</body>
</html>