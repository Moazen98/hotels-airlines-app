<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.zeon.UserPortable.Model.*"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.Iterator"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hotel Info</title>
<!-- include bootstrap css -->
<link rel="stylesheet" type="text/css"
	href="static/css/bootstrap.min.css">

<!-- include bootstrap js -->
<script type="text/javascript" src="static/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>

<!-- include my style -->
<link href="static/css/style.css" rel="stylesheet" type="text/css"
	media="all">
<link href="static/css/owl.carousel.css" rel="stylesheet"
	type="text/css" media="all">
<link rel="stylesheet" type="text/css"
	href="static/css/showHotelInfo.css">
<link rel="stylesheet" href="static/css/all.min.css">
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- include jquery ui -->
<link
	href='https://ajax.aspnetcdn.com/ajax/jquery.ui/1.10.4/themes/smoothness/jquery-ui.css'
	rel='stylesheet'>

<script src="static/jq/jquery.min.js"> 
    </script>

<script src="static/jq/jquery-ui.min.js"> 
    </script>

</head>
<body>


	<%
		Hotel hotel = (Hotel) request.getAttribute("hotel");
		Set<SingleRoom> singleroom = new HashSet<SingleRoom>();
		Set<DualRoom> dualroom = new HashSet<DualRoom>();
		Set<SuitRoom> suitroom = new HashSet<SuitRoom>();
		singleroom = hotel.getSingleRoomList();
		dualroom = hotel.getDualRoomList();
		suitroom = hotel.getSuitRoomList();
	%>

	<!-- start slider -->

	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<li data-target="#myCarousel" data-slide-to="3"></li>
			<li data-target="#myCarousel" data-slide-to="4"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<img src="static/img/5.jpg" style="height: 550px; width: 100%">
			</div>

			<div class="item">
				<img src="static/img/1.jpg" style="height: 550px; width: 100%">
			</div>

			<div class="item">
				<img src="static/img/2.jpg" style="height: 550px; width: 100%">
			</div>

			<div class="item">
				<img src="static/img/6.jpg" style="height: 550px; width: 100%">
			</div>

			<div class="item">
				<img src="static/img/7.jpg" style="height: 550px; width: 100%">
			</div>

		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>

	<!-- end slider -->




	<!-- show information -->

	<div class="information">
		<div class="container">

			<h2>
				WELCOME TO
				<%
				out.print(hotel.getName());
			%>
			</h2>
			<p>The Best Choise To Have Fun!</p>
			<br>
			<p>
				<%
					out.print(hotel.getDescription());
				%>
				our hotel has a
				<%
					out.print(singleroom.size() + dualroom.size() + suitroom.size());
				%>
				room to reservation.
			</p>
		</div>
	</div>

	<!-- end div info -->

	<!-- start weater -->
	<div class="weather">

		<div class="agileits_w3layouts_main_grid_left">
			<div class="wthree_main_grid agileinfo_main_grid">
				<div class="w3ls_main_grid">
					<div class="agileits_main_grid_left">
						<div class="agileits_main_grid_leftl">
							<h3>
								24<span>°</span>
							</h3>
						</div>
						<div class="agileits_main_grid_leftr">
							<h4>Sunny</h4>
							<p>Today</p>
							,
							<p>
								<%
									out.print(hotel.getAddress());
								%>
							</p>
						</div>
						<div class="clear"></div>
					</div>

					<div class="clear"></div>
				</div>
				<div class="w3l_main_grid_list">
					<ul id="owl-demo" class="owl-carousel owl-theme"
						style="opacity: 1; display: block;">
						<div class="owl-wrapper-outer">
							<div class="owl-wrapper"
								style="width: 100%; left: 0px; transition: all 1000ms ease 0s; transform: translate3d(0px, 0px, 0px);">
								<div class="owl-item" style="width: 118px;">
									<li><h4>Max</h4>
										<div class="w3layouts_icon">
											<img src="static/images/1.png" alt=" "
												class="w3_img_responsive">
										</div>
										<h5>
											<span id="max">15</span>°
										</h5></li>
								</div>
								<div class="owl-item" style="width: 118px;">
									<li><h4>Min</h4>
										<div class="w3layouts_icon">
											<img src="static/images/8.png" alt=" "
												class="w3_img_responsive">
										</div>
										<h5>
											<span id="min">15</span>°
										</h5></li>
								</div>
								<div class="owl-item" style="width: 118px;">
									<li><h4>Wind</h4>
										<div class="w3layouts_icon">
											<img src="static/images/12.png" alt=" "
												class="w3_img_responsive" style="width: 40px;">
										</div>
										<h5>
											<span id="wind">15</span>°
										</h5></li>
								</div>
							</div>
						</div>

					</ul>
				</div>
			</div>
		</div>


	</div>


	<!-- end weater -->


	<!-- start prices -->


	<div class="prices">
		<article>
			<h2 class="hd">Our Prices</h2>
			<span class="line"></span>
		</article>
		<div class="container">


			<%
				boolean bool = false;
				SingleRoom sr = new SingleRoom();
				for (Iterator<SingleRoom> it = singleroom.iterator(); it.hasNext();) {
					sr = it.next();
					if (sr.getReserved() == false) {
						bool = true;
						break;
					}
				}
				if (bool) {
			%>
			<div class="type">
				<div class="img">
					<img src="<%out.print(sr.getUrl());%>">

				</div>

				<div class="info">
					<h2>Single Room</h2>
					<p>
						The nubmer of available room is
						<%
						out.print(sr.getId());
					%>
					</p>
					<div class="price">
						<p>
							<%
								out.print(sr.getPrice());
							%>$
						</p>
					</div>
					<div class="book">
						<button class="single">See More</button>
					</div>
				</div>
			</div>
			<%
				}
			%>



			<%
				bool = false;
				DualRoom dr = new DualRoom();
				for (Iterator<DualRoom> it = dualroom.iterator(); it.hasNext();) {
					dr = it.next();
					if (dr.getReserved() == false) {
						bool = true;
						break;
					}
				}
				if (bool) {
			%>
			<div class="type">
				<div class="img">
					<img src="<%out.print(dr.getUrl());%>">
				</div>

				<div class="info">
					<h2>Dual Room</h2>
					<p>
						The nubmer of available room is
						<%
						out.print(dr.getId());
					%>
					</p>
					<div class="price">
						<p>
							<%
								out.print(dr.getPrice());
							%>$
						</p>
					</div>
					<div class="book">
						<button class="dual">See More</button>
					</div>
				</div>
			</div>
			<%
				}
			%>


			<%
				bool = false;
				SuitRoom sur = new SuitRoom();
				System.out.println("hellow before jsp");
				for (Iterator<SuitRoom> it = suitroom.iterator(); it.hasNext();) {
					sur = it.next();
					System.out.println("hellow form jsp");
					if (sur.getReserved() == false) {
						bool = true;
						System.out.println("hellow form suit code");
			%>
			<div class="type">
				<div class="img">
					<img src="<%out.print(sur.getUrl());%>">
				</div>
				<div class="info">
					<h2>Triple Room</h2>
					<p>
						The nubmer of available room is
						<%
						out.print(sur.getId());
					%>
					</p>
					<div class="price">
						<p>
							<%
								out.print(sur.getPrice());
							%>$
						</p>
					</div>
					<div class="book">
						<button class="third">See More</button>
					</div>
				</div>
			</div>
			<%
				break;
					}
				}
			%>


		</div>
	</div>

	<!-- end prices -->


	<!-- start popup -->
	<div class="bg-modal">

		<div class="model">
			<h2>payment section</h2>
			<form action="/after-payment">
				<div class="g-info">
					<span class="closet" onclick="close()">X</span> <input
						type="hidden" name="price" value="<%out.print(sr.getPrice());%>" />
					<input type="hidden" name="hotel_id"
						value="<%out.print(hotel.getid());%>" /> <input type="hidden"
						name="roomId" value="<%out.print(sr.getId());%>" /> <input
						type="hidden" name="roomType" value="<%out.print("single");%>" />
					<input type="hidden" name="city"
						value="<%out.print(hotel.getAddress().getAddress());%>" />

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

					</div>

					<div class="fields">

						<div class="section">
							<h3>Starting date</h3>
							<input type="text" class="calender" name="book" required>
						</div>

						<div class="section">
							<h3>Leaving date</h3>
							<input type="text" class="calender2" name="leave" required>
						</div>


						<div class="section">
							<input type="text" name="adult" placeholder="number of adult"
								required>
						</div>

						<div class="section">
							<input type="text" name="childern"
								placeholder="number of childern" required>
						</div>

						<input value="Pay" type="submit">

					</div>


				</div>

			</form>
		</div>

		<div class="dual-payment-modal">
			<h2>payment section</h2>
			<form action="/after-payment">
				<div class="g-info">
					<span class="closet" onclick="close()">X</span> <input
						type="hidden" name="hotel_id"
						value="<%out.print(hotel.getid());%>" /> <input type="hidden"
						name="price" value="<%out.print(dr.getPrice());%>" /> <input
						type="hidden" name="roomId" value="<%out.print(dr.getId());%>" />
					<input type="hidden" name="roomType" value="<%out.print("dual");%>" />
					<input type="hidden" name="city"
						value="<%out.print(hotel.getAddress().getAddress());%>" />

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

					</div>

					<div class="fields">

						<div class="section">
							<h3>Starting date</h3>
							<input type="text" class="calender" name="book" required>
						</div>

						<div class="section">
							<h3>Leaving date</h3>
							<input type="text" class="calender2" name="leave" required>
						</div>


						<div class="section">
							<input type="text" name="adult" placeholder="number of adult"
								required>
						</div>

						<div class="section">
							<input type="text" name="childern"
								placeholder="number of childern" required>
						</div>

						<input value="Pay" type="submit">

					</div>

				</div>

			</form>
		</div>

		<div class="suit-payment-modal">
			<h2>payment section</h2>
			<form action="/after-payment">
				<div class="g-info">
					<span class="closet" onclick="close()">X</span> <input
						type="hidden" name="hotel_id"
						value="<%out.print(hotel.getid());%>" /> <input type="hidden"
						name="price" value="<%out.print(sur.getPrice());%>" /> <input
						type="hidden" name="roomId" value="<%out.print(sur.getId());%>" />
					<input type="hidden" name="roomType" value="<%out.print("suit");%>" />

					<input type="hidden" name="city"
						value="<%out.print(hotel.getAddress().getAddress());%>" />
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

					</div>

					<div class="fields">

						<div class="section">
							<h3>Starting date</h3>
							<input type="text" class="calender" name="book" required>
						</div>

						<div class="section">
							<h3>Leaving date</h3>
							<input type="text" class="calender2" name="leave" required>
						</div>


						<div class="section">
							<input type="text" name="adult" placeholder="number of adult"
								required>
						</div>

						<div class="section">
							<input type="text" name="childern"
								placeholder="number of childern" required>
						</div>

						<input value="Pay" type="submit">

					</div>
				</div>

			</form>
		</div>



		<div class="model-single">
			<span class="closet">X</span>
			<h2>
				<%
					out.print(hotel.getName());
				%>
			</h2>
			<h3>Single Room</h3>
			<img src="<%out.print(sr.getUrl());%>">
			<p>
				<%
					out.print(sr.getDescription());
				%>
			</p>
			<p>
				Total cost for one day :
				<%
				out.print(sr.getPrice());
			%>
			</p>
			<button class="butt">Book Now</button>
		</div>

		<div class="model-dual">
			<span class="closet"">X</span>
			<h2>
				<%
					out.print(hotel.getName());
				%>
			</h2>
			<h3>Dual Room</h3>
			<img src="<%out.print(dr.getUrl());%>">
			<p>
				<%
					out.print(dr.getDescription());
				%>
			</p>
			<p>
				Total cost for one day :
				<%
				out.print(dr.getPrice());
			%>
			</p>
			<button class="buttd">Book Now</button>
		</div>

		<div class="model-third">
			<span class="closet">X</span>
			<h2>
				<%
					out.print(hotel.getName());
				%>
			</h2>
			<h3>Third Room</h3>
			<img src="<%out.print(sur.getUrl());%>">
			<p>
				<%
					out.print(sur.getDescription());
				%>
			</p>
			<p>
				Total cost for one day :
				<%
				out.print(sur.getPrice());
			%>
			</p>
			<button class="buttsu">Book Now</button>
		</div>
	</div>
	<!-- end popup -->



	<!-- start amenties -->

	<div class="amenties">
		<article>
			<h2 class="hd">Our Amenities</h2>
			<span class="line"></span>
		</article>
		<div class="container">

			<%
				Set<String> set = new HashSet<String>();
				set = hotel.getAmenities();
				for (Iterator<String> ita = set.iterator(); ita.hasNext();) {
			%>

			<p>
				<%
					String s = ita.next();
						out.print(s);
				%>
			</p>

			<%
				}
			%>

		</div>
	</div>

	<!-- end amentis -->

	<!-- start map -->
	<div id="googleMap" style="width: 100%; height: 90vh;"></div>
	<!-- end map -->


	<!-- star contact -->

	<section class="contact" id="contact">

		<article>
			<h2 class="hd">Get In Touch</h2>
			<span class="line"></span>
		</article>

		<article class="contact-form container">
			<div class="contact-item">
				<i class="fa fa-phone"></i> <span>Phone</span> <span> <%
 	out.print(hotel.getContact().getPhone());
 %>
				</span>
			</div>
			<div class="contact-item">
				<i class="fa fa-envelope"></i> <span>Email</span> <span> <%
 	out.print(hotel.getContact().getEmail());
 %>
				</span>
			</div>
			<div class="contact-item">
				<i class="fa fa-map-marker"></i> <span>Address</span> <span>
					<%
						out.print(hotel.getAddress().getAddress());
					%>
				</span>
			</div>

			<form action="/send-email">
				<input type="text" placeholder="Name" name="Name" required>
				<input type="text" placeholder="Email" name="Email" required><br>
				<input type="text" placeholder="subject" name="subject" required><br>
				<textarea name="message" id="" placeholder="message" required></textarea>
				<br>
				<button>send message</button>
			</form>
		</article>
	</section>

	<!-- end contact -->

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

	<script>
function myMap() {
var mapProp= {
  center:new google.maps.LatLng(<%out.print(hotel.getLatitude());%>,<%out.print(hotel.getLongtitude());%>),
  zoom:10,
};
var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
}
</script>

	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB_sVG2_ySIPBKFezuNcNpG8d07Y1KBStA&callback=myMap"></script>


	<script>
        $(Document).ready(function(){
            $(".calender").datepicker({
                minDate :+1
            });
        });
        
        
    // This will update the "end" variable as it changes.
        $(document).on('change', '.calender', function() {
        var end = $(this).val();
        console.log(end, "Hello, world!");
        
        $(".calender2").datepicker({
                minDate : end
        });
        console.log("end of func");
    });

    </script>

	<script>
	
	var ourGalary=document.body.querySelectorAll('.prices .container .type .info button');
	
	
	ourGalary.forEach(button=>{
	button.addEventListener('click',(e)=>{
	    
	    if(e.target.className==='single')
	    {  
	        document.body.querySelector('.bg-modal').style.display ="flex";
	        document.body.querySelector('.model-single').style.display ="block"; 
	    }
	    else if(e.target.className==='dual')
	    {  
	        document.body.querySelector('.bg-modal').style.display ="flex";
	        document.body.querySelector('.model-dual').style.display ="block"; 
	    }
	     else if(e.target.className==='third')
	    {  
	        document.body.querySelector('.bg-modal').style.display ="flex";
	        document.body.querySelector('.model-third').style.display ="block"; 
	    }
	    
	         
	});
	});
	
	
	
	
	//////when click on book now button 
	document.addEventListener('click',(e)=>{
	if(e.target.className==='butt')
	    { 
	        
	      document.body.querySelector('.model-single').style.display ="none";
	        document.body.querySelector('.model-dual').style.display ="none";
	      document.body.querySelector('.model-third').style.display ="none";
	      document.body.querySelector('.dual-payment-modal').style.display ="none";
	      document.body.querySelector('.suit-payment-modal').style.display ="none";
	      
	      document.body.querySelector('.model').style.display ="block"; 
	    }
	});
	
	document.addEventListener('click',(e)=>{
		if(e.target.className==='buttd')
		    { 
		        
		      document.body.querySelector('.model-single').style.display ="none";
		        document.body.querySelector('.model-dual').style.display ="none";
		      document.body.querySelector('.model-third').style.display ="none";
		      

		      document.body.querySelector('.dual-payment-modal').style.display ="block";
		      document.body.querySelector('.suit-payment-modal').style.display ="none";
		      document.body.querySelector('.model').style.display ="none"; 
		    }
		});
	
	document.addEventListener('click',(e)=>{
		if(e.target.className==='buttsu')
		    { 
		        
		      document.body.querySelector('.model-single').style.display ="none";
		        document.body.querySelector('.model-dual').style.display ="none";
		      document.body.querySelector('.model-third').style.display ="none";

		      document.body.querySelector('.dual-payment-modal').style.display ="none";
		      document.body.querySelector('.suit-payment-modal').style.display ="block";
		      document.body.querySelector('.none').style.display ="block"; 
		    }
		});
	
	// when click on x in any pupop
	document.addEventListener('click',(e)=>{
	  if(e.target.className==='closet')
	    { 
	      document.body.querySelector('.bg-modal').style.display ="none";
	      document.body.querySelector('.model-single').style.display ="none";
	      document.body.querySelector('.model-dual').style.display ="none";
	      document.body.querySelector('.model-third').style.display ="none";
	      document.body.querySelector('.suit-payment-modal').style.display ="none";
	      document.body.querySelector('.dual-payment-modal').style.display ="none";
	      document.body.querySelector('.model').style.display ="none";
	    }
	});

</script>
	<script>
            axios.get('http://api.openweathermap.org/data/2.5/weather?lat=<%out.print(hotel.getLatitude());%>&lon=<%out.print(hotel.getLongtitude());%>&units=metric&appid=d1f2d24005e7ac2614e5bad1a8d963d4')
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