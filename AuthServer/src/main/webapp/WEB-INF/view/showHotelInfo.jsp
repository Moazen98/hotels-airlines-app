<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hotel Info</title>
	<!-- include bootstrap css -->
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">

	<!-- include bootstrap js -->
	<script type="text/javascript" src="static/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>

	<!-- include my style -->
	<link rel="stylesheet" type="text/css" href="static/css/showHotelInfo.css">
    <link rel="stylesheet" href="static/css/all.min.css">
</head>
<body>



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
      <img src="${hotel.img}" style="height: 550px; width: 100%">
      <div class="carousel-caption">
        <h2>Welcome To ${hotel.name}</h2>
        <p>The Best Choise To Have Fun!</p>
      </div>
    </div>

    <div class="item">
      <img src="${hotel.imagea}" style="height: 550px; width: 100%">
      <div class="carousel-caption">
        <h2>Welcome To ${hotel.name}</h2>
        <p>The Best Choise To Have Fun!</p>
      </div>
    </div>

    <div class="item">
      <img src="${hotel.imageb}" style="height: 550px; width: 100%">
      <div class="carousel-caption">
        <h2>Welcome To ${hotel.name}</h2>
        <p>The Best Choise To Have Fun!</p>
      </div>
    </div>

    <div class="item">
      <img src="${hotel.imagec}" style="height: 550px; width: 100%">
      <div class="carousel-caption">
        <h2>Welcome To ${hotel.name}</h2>
        <p>The Best Choise To Have Fun!</p>
      </div>
    </div>

    <div class="item">
      <img src="${hotel.imaged}" style="height: 550px; width: 100%">
      <div class="carousel-caption">
        <h2>Welcome To ${hotel.name}</h2>
        <p>The Best Choise To Have Fun!</p>
      </div>
    </div>

  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

<!-- end slider -->




<!-- show information -->

    <div class="information">
    	<div class="container">
    		
    		<p>${hotel.about_hotel}</p>
            <br>
            <p>${hotel.about_hotel}</p>
    	</div>	
    </div>

    <!-- end div info -->


    <!-- start prices -->

    <div class="prices"  >
    	<article>
            <h2 class="hd">Our Prices</h2>
            <span class="line"></span>
        </article>
    	<div class="container">
    	
    	<div class="type">
    		<h2>Personal Room</h2>    
            <div class="price">    
            <p>${hotel.price_of_personal_room}$</p>
            </div>
            <p>for day</p>
            <p>Get Your Dream Stay :)</p>
            <div class="book">
            <a href="call_hotel_user"> Book Now</a>
             </div>

    	</div>

    	<div class="type">
    		<h2>Dual Room</h2>  
    		<div class="price">    
            <p>${hotel.price_of_dual_room}$</p>
            </div>
            <p>for day</p>
            <p>Get Your Dream Stay :)</p>
            <div class="book">
            <a href="#"> Book Now</a>
             </div>

    	</div>
    	<div class="type">
    		<h2>Triple Room</h2>    
            <div class="price">    
            <p>${hotel.price_of_threeperson_room}$</p>
            </div>
            <p>for day</p>
            <p>Get Your Dream Stay :)</p>
            <div class="book">
            <a href="#"> Book Now</a>
             </div>
          	</div>
    </div>
    </div>

    <!-- end prices -->



    <!-- star contact -->

    <section class="contact" id="contact">

        <article>
            <h2 class="hd">Get In Touch</h2>
            <span class="line"></span>
        </article>

        <article class="contact-form container">
            <div class="contact-item">
                <i class="fas fa-phone-alt"></i>
                <span>Phone</span>
                <span>${hotel.phone}</span>
            </div>
            <div class="contact-item">
                <i class="fas fa-envelope"></i>
                <span>Email</span>
                <span>${hotel.email}</span>
            </div>
            <div class="contact-item">
                <i class="fas fa-map-marker-alt"></i>
                <span>Address</span>
                <span>${hotel.address}</span>
            </div>
            
            <form action="">
                <input type="text" placeholder="Name">
                <input type="text" placeholder="Email"><br>
                <input type="text" placeholder="subject"><br>
                <textarea name="" id="" placeholder="message"></textarea><br>
                <button>send message</button>
            </form>
        </article>
    </section>

            <!-- end contact -->

            <!-- start footer -->

    <footer>
        <div class="footer-logo"><img src="static/img/logo-alt.png" alt="logo"></div>
        
        <div class="cr">
            <p>COPYRIGHT &copy;2020. ALL RIGHTS RESERVED. DESIGNED BY <span> Zeon company</span></p>
        </div>
    </footer>

            <!-- end footer -->


</body>
</html>