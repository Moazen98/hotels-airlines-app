<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<meta charset="utf-8">
<link rel="stylesheet" href="static/css/all.min.css">
<link rel="stylesheet" href="static/css/index.css">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Manrope&display=swap"
	rel="stylesheet">
</head>
<body>
	<!-- start header -->
	<header>
		<div class="overlay">
			<div class="container">
				<div class="logo">
					<a href="/user-edit-page"><i class="fa fa-user-circle-o"></i></a> <img
						src="static/img/logo-alt.png" alt="logo">
				</div>
				<ul class="navbar">
					<li><a href="#" class="active">home</a></li>
					<li><a href="#about">about</a></li>
					<li><a href="#prices">prices</a></li>
					<li><a href="#team">team</a></li>
					<li><a href="#contact">contact</a></li>
					<li><a href="/login-user">login</a></li>
					<li><a href="/register-user">register</a></li>
					<li class="drop">more
						<ul>
							<li><a href="/get-all-user-reserve" class="active">Show
									All Reserved Hotel</a></li>
							<li><a href="/get-all-user-plane-reserve" class="active">Show
									All Reserved Plane</a></li>
						</ul>

					</li>
				</ul>
				<div class="clear"></div>

			</div>

			<div class="home">
				<h1 class="title">
					<span>welcome</span> <span>to</span> <span>zeon</span> <span>company</span>
				</h1>
				<p class="home-content">
					we are creative agency that provide to you a save and comfortable
					flight and also you can reservation in hotel in any place in the
					world<br>
					<br> You Can Started Your Dream Trip.
				</p>
				<button class="btn btn1">
					<a href="/search-flight">Booking flight </a>
				</button>
				<button class="btn btn2">
					<a href="/get-search-page">Booking in Hotel</a>
				</button>
			</div>
		</div>
	</header>

	<!-- end header -->

	<!-- start about -->

	<section class="about" id="about">
		<article>
			<h2 class="hd">Welcome To Zeon Web Site</h2>
			<span class="line"></span>
		</article>
		<article class="about-disc container">
			<div class="left">
				<i class="fa fa-cogs"></i>
				<h3>Safe Trip</h3>
				<p>you will find our flight safe , Reserve Taxi and there are
					many services you will get in your trip</p>
				<br>

			</div>
			<div class="left">
				<i class="fa fa-magic"></i>
				<h3>Prices are acceptable</h3>
				<p>All Hotels Ordered by Price</p>
				<br>

			</div>
			<div class="left">
				<i class="fa fa-mobile"></i>
				<h3>Rest and Relaxation</h3>
				<p>you can reserve in any hotel and you can pay by credit card
					and paypal</p>
				<br>

			</div>
			<div class="clear"></div>
		</article>
	</section>

	<!-- end about -->




	<!-- start why choose us -->

	<section class="choose">
		<div class="container">
			<article class="choose-l ">
				<h2 class="hd">why choose us</h2>
				<span class="line"></span>
				<p>easer and cheaper booking flight and easer reserve in hotels.
					we always with you in any place in the world. we give to you a
					stronger choices and services to trips.</p>
				<ul>
					<li><i class="fa fa-check-circle-o"></i><span>Reserve
							Flight.</span></li>
					<li><i class="fa fa-check-circle-o"></i><span>Reserve
							Hotel.</span></li>
					<li><i class="fa fa-check-circle-o"></i><span>Reserve
							Taxi</span></li>
					<li><i class="fa fa-check-circle-o"></i><span>We are
							always available for any assistance or problems.</span></li>
					<li><i class="fa fa-check-circle-o"></i><span>Availability
							of trips to all countries of the world.</span></li>
				</ul>
			</article>
			<article class="choose-r">
				<img class="image" src="static/img/about3.jpg" alt="photo">
			</article>
		</div>
	</section>

	<!-- end why choose us -->



	<!-- start trophy -->

	<section class="trophy">
		<div class="overlay">
			<div class="troph">
				<i class="fa fa-users"></i> <span>0</span> <span>happy
					clients</span>
			</div>

			<div class="troph">
				<i class="fa fa-trophy"></i> <span>0</span> <span>awards won</span>
			</div>

			<div class="troph">
				<i class="fa fa-coffee"></i> <span>0</span> <span>cups of
					coffe</span>
			</div>

			<div class="troph">
				<i class="fa fa-file"></i> <span>2</span> <span>projects
					completed</span>
			</div>
		</div>
	</section>

	<!-- end trophy -->

	<!-- start prices -->

	<section class="prices" id="prices">
		<article>
			<h2 class="hd">Pricing Table</h2>
			<span class="line"></span>
		</article>
		<article class="price container">

			<div class="price-item ">
				<div class="overlay-item-utd">
					<span class="plan-hd">Reserve in Hotel</span> <span
						class="plan-price"> <span>$9</span> <br> <span>/
							More</span>
					</span> <span class="plan-dt">Hotels around world</span> <span
						class="plan-dt">Details and photo</span> <span class="plan-dt">24/24
						Support</span>
					<button>Reserve now</button>
				</div>
			</div>

			<div class="price-item ">
				<div class="overlay-item-utd">
					<span class="plan-hd">Reserve Plane</span> <span class="plan-price">
						<span>$19</span> <br> <span>/ More</span>
					</span> <span class="plan-dt">Travel Around World !</span> <span
						class="plan-dt">Details about airport </span> <span
						class="plan-dt">24/24 Support</span>
					<button>Reserve now</button>
				</div>
			</div>

			<div class="price-item ">
				<div class="overlay-item-utd">
					<span class="plan-hd">Reserve Taxi</span> <span class="plan-price">
						<span>$2</span> <br> <span>/ More</span>
					</span> <span class="plan-dt">A lot of taxi</span> <span class="plan-dt">Choose
						your taxi</span> <span class="plan-dt">24/24 Support</span>
					<button>Reserve now</button>
				</div>
			</div>

		</article>
	</section>

	<!-- end prices -->

	<!-- start opinion -->

	<section class="opinion">
		<div class="overlay">
			<div class="container">

				<article class="opin">
					<div class="opin-img">
						<img src="static/img/Pic2.jpg" alt="photo">
					</div>
					<span class="opin-nm">Mohamad Al Moazen</span> <span
						class="opin-desc">Web Developer</span>
					<p>Team Leader And Spring Boot Web Developer MVC and
						Microservices , ITE Network From Damascuse University</p>
				</article>

				<article class="opin">
					<div class="opin-img">
						<img src="static/img/team3.jpg" alt="photo">
					</div>
					<span class="opin-nm">Reem Jomaa</span> <span class="opin-desc">Web
						Designer</span>
					<p>Spring Boot Web Developer MVC , Microservices , ITE Network
						From Damascuse University</p>
				</article>
			</div>
		</div>

	</section>

	<!-- end opinion -->



	<!-- start team -->

	<section class="our-team" id="team">
		<article>
			<h2 class="hd">our team</h2>
			<span class="line"></span>
		</article>
		<article class="team container">

			<div class="card">
				<div class="front">
					<img src="static/img/Pic2.jpg">
				</div>
				<div class="back">

					<h2>Mohamad Al-Moazen</h2>
					<p>web developer</p>
					<p>ITE Network</p>
					<p>mhd.moazen98@gmail.com</p>
					<div class="social">
						<i class="fa fa-facebook-square"></i> <i
							class="fa fa-twitter-square"></i> <i
							class="fa fa-google-plus-square"></i>
					</div>

				</div>
			</div>

			<div class="card">
				<div class="front">
					<img src="static/img/team3.jpg">
				</div>
				<div class="back">

					<h2>Reem Jomaa</h2>
					<p>web designer</p>
					<p>ITE Network</p>
					<div class="social">
						<i class="fa fa-facebook-square"></i> <i
							class="fa fa-twitter-square"></i> <i
							class="fa fa-google-plus-square"></i>
					</div>

				</div>
			</div>



			<div class="card">
				<div class="front">
					<img src="static/img/pic3.jpg">
				</div>
				<div class="back">

					<h2>Elie Hashisho</h2>
					<p>web web designer</p>
					<p>ITE Network</p>
					<div class="social">
						<i class="fa fa-facebook-square"></i> <i
							class="fa fa-twitter-square"></i> <i
							class="fa fa-google-plus-square"></i>
					</div>

				</div>
			</div>
			<div class="card">
				<div class="front">
					<img src="static/img/team3.jpg">
				</div>
				<div class="back">

					<h2>Ahmad Mla-Rashed</h2>
					<p>ITE Network</p>
					<div class="social">
						<i class="fa fa-facebook-square"></i> <i
							class="fa fa-twitter-square"></i> <i
							class="fa fa-google-plus-square"></i>
					</div>

				</div>
			</div>





			<!-- <div class="team-item ">
                <div class="overlay-item-utd">
                    
                    <img src="img/team1.jpg" alt="">
                    <span class="team-nm">Mohamad Al-Moazen</span>
                    <span class="team-jb">web developer</span>
                    
                </div>

            </div>

            <div class="team-item ">
                <div class="overlay-item-utd">
                    
                    
                    <img src="img/team1.jpg" alt="">
                    <span class="team-nm">Ahmad Mla-Rashed</span>
                    <span class="team-jb">web developer</span>
                    
                </div>

            </div>

            <div class="team-item ">
                <div class="overlay-item-utd">
                    <img src="img/team1.jpg" alt="">
                    <span class="team-nm">Reem jomaa</span>
                    <span class="team-jb">web designer</span>
                </div>

            </div>


            <div class="team-item ">
                <div class="overlay-item-utd">
                
                    <img src="img/team1.jpg" alt="">
                    <span class="team-nm">Elie Hashisho</span>
                    <span class="team-jb">web designer</span>
                    
                </div>

            </div>
 -->


		</article>
	</section>

	<!-- end team -->



	<!-- star contact -->

	<section class="contact" id="contact">

		<article>
			<h2 class="hd">Get In Touch</h2>
			<span class="line"></span>
		</article>

		<article class="contact-form container">
			<div class="contact-item">
				<i class="fa fa-phone"></i> <span>Phone</span> <span>+963
					992875193</span>
			</div>
			<div class="contact-item">
				<i class="fa fa-envelope"></i> <span>Email</span> <span>mhd.moazen98@gmail.com</span>
			</div>
			<div class="contact-item">
				<i class="fa fa-map-marker"></i> <span>Address</span> <span>Syria
					, Damascus</span>
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
		<div class="social">
			<i class="fa fa-facebook-square"></i> <i class="fa fa-twitter-square"></i>
			<i class="fa fa-google-plus-square"></i> <i class="fa fa-linkedin"></i>
			<i class="fa fa-youtube-square"></i>
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