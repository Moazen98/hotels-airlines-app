<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index Page</title>

     <link rel="stylesheet" href="static/css/all.min.css">
     <link rel="stylesheet" href="static/css/index.css">
     <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
     <link href="https://fonts.googleapis.com/css?family=Manrope&display=swap" rel="stylesheet">
     
</head>
<body>

  <!-- start header -->
    <header>
        <div class="overlay">
            <div class="container">
                <div class="logo"><img src="static/img/logo-alt.png" alt="logo"></div>
                <ul class="navbar">
                    <li><a href="#" class="active">home</a></li>
                    <li><a href="#about">about</a></li>
                    <li><a href="#prices">prices</a></li>
                    <li><a href="#team">team</a></li>
                    <li><a href="#contact">contact</a></li>
                    <li class="ll"><a href="/login-user">login</a></li>
                    <li class="ll"><a href="/register-user">register</a></li> 
                </ul>
                <div class="clear"></div>
                
            </div>

            <div class="home">
                <h1 class="title">we are creative agency</h1>
                <p class="home-content">welocme to zeon company that provide to you a save and comfortable flight and also you can reservation in hotel in any place in the world :)<br> You Can Started Your Dream Trip.</p>
                <button class="btn btn1"><a href="/search-flight">Booking flight </a> </button>
                <button class="btn btn2"><a href="/search-hotel">Booking in Hotel</a> </button>
            </div>
        </div>
    </header>

    <!-- end header -->

    <!-- start about -->

    <section class="about" id="about">
        <article>
            <h2 class="hd">Welcome To Website</h2>
            <span class="line"></span>
        </article>
        <article class="about-disc container">
            <div>
                    <i class="fas fa-cogs"></i>
                    <h3>Safe Trip</h3>
                    <p>you will find our fligh safe and there are many services you will get in your trip.</p>
                    <br>

            </div>
            <div>
                    <i class="fas fa-magic"></i>
                    <h3>Prices are acceptable</h3>
                    <p>We strive to make flights easier and more accessible to everyone.</p>
                    <br>
                    
            </div>
            <div>
                    <i class="fas fa-mobile-alt"></i>
                    <h3>Many Feature</h3>
                    <p>you can reservice in any hotel and you can pay by creadet card and paypal.</p>
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
                    <p>easer and cheaper booking fligh and easer reseve in hotels.
                     we always with you in any palce in the world. we give to you a stronger choices and services to trips.</p>
                    <ul>
                        <li><i class="far fa-check-circle"></i>Reserve fligh.</li>
                        <li><i class="far fa-check-circle"></i>Reserve hotel.</li>
                        <li><i class="far fa-check-circle"></i>We are always available for any assistance or problems.</li>
                        <li><i class="far fa-check-circle"></i>Availability of trips to all countries of the world.</li>
                    </ul>
                </article>
                    <article class="choose-r"><img class="image" src="static/img/about3.jpg" alt="photo"></article>
            </div>

    </section>
            <!-- end why choose us -->

            <!-- start trophy -->

    <section class="trophy">
        <div class="overlay">
            <div class="troph">
                <i class="fas fa-users"></i>
                <span>451</span>
                <span>happy clients</span>
            </div> 

            <div class="troph">
                <i class="fas fa-trophy"></i>
                <span>12</span>
                <span>awards won</span>
            </div> 

            <div class="troph">
                <i class="fas fa-coffee"></i>
                <span>145k</span>
                <span>cups of coffe</span>
            </div>

            <div class="troph">
                <i class="fas fa-file"></i>
                <span>45</span>
                <span>projects completed</span>
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
                    <span class="plan-hd">BASIC PLAN</span>
                    <span class="plan-price"> <span>$9</span>  <br>
                        <span>/ MONTH</span> </span>
                    <span class="plan-dt">1GB Disk Space</span>
                    <span class="plan-dt">100 Email Account</span>
                    <span class="plan-dt">24/24 Support</span>
                    <button>Purchase now</button>
                </div>
            </div>

            <div class="price-item ">
                <div class="overlay-item-utd">
                    <span class="plan-hd">BASIC PLAN</span>
                    <span class="plan-price"> <span>$19</span>  <br>
                        <span>/ MONTH</span> </span>
                    <span class="plan-dt">1GB Disk Space</span>
                    <span class="plan-dt">100 Email Account</span>
                    <span class="plan-dt">24/24 Support</span>
                    <button>Purchase now</button>
                </div>
            </div>

            <div class="price-item ">
                <div class="overlay-item-utd">
                    <span class="plan-hd">BASIC PLAN</span>
                    <span class="plan-price"> <span>$39</span>  <br>
                        <span>/ MONTH</span> </span>
                    <span class="plan-dt">1GB Disk Space</span>
                    <span class="plan-dt">100 Email Account</span>
                    <span class="plan-dt">24/24 Support</span>
                    <button>Purchase now</button>
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
                <div class="opin-img"><img src="static/img/author.jpg" alt="photo"></div>
                <span class="opin-nm">john doe</span>
                <span class="opin-desc">web designer</span>
                <p>Molestie at elementum eu facilisis sed odio. Scelerisque in dictum non consectetur a erat. Aliquam id diam maecenas ultricies mi eget mauris.</p>
            </article>

            <article class="opin">  
                <div class="opin-img"><img src="static/img/perso1.jpg" alt="photo"></div>
                <span class="opin-nm">john doe</span> 
                <span class="opin-desc">web designer</span>
                <p>Molestie at elementum eu facilisis sed odio. Scelerisque in dictum non consectetur a erat. Aliquam id diam maecenas ultricies mi eget mauris.</p>
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
            <div class="team-item ">
                <div class="overlay-item-utd">
                    
                    <img src="static/img/team1.jpg" alt="">
                    <span class="team-nm">Mohamad Al-Moazen</span>
                    <span class="team-jb">web developer</span>
                    
                </div>

            </div>

            <div class="team-item ">
                <div class="overlay-item-utd">
                    
                    
                    <img src="static/img/team2.jpg" alt="">
                    <span class="team-nm">Ahmad Mla-Rashed</span>
                    <span class="team-jb">web developer</span>
                    
                </div>

            </div>

            <div class="team-item ">
                <div class="overlay-item-utd">
                    <img src="static/img/team3.jpg" alt="">
                    <span class="team-nm">Reem jomaa</span>
                    <span class="team-jb">web designer</span>
                </div>

            </div>


            <div class="team-item ">
                <div class="overlay-item-utd">
                
                    <img src="static/img/team3.jpg" alt="">
                    <span class="team-nm">Elie Hashisho</span>
                    <span class="team-jb">web designer</span>
                    
                </div>

            </div>

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
                <i class="fas fa-phone-alt"></i>
                <span>Phone</span>
                <span>512-412-3940</span>
            </div>
            <div class="contact-item">
                <i class="fas fa-envelope"></i>
                <span>Email</span>
                <span>email@support.com</span>
            </div>
            <div class="contact-item">
                <i class="fas fa-map-marker-alt"></i>
                <span>Address</span>
                <span>1739 Bubby Drive</span>
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
        <div class="social">
            <i class="fab fa-facebook-square"></i>
            <i class="fab fa-twitter-square"></i>
            <i class="fab fa-google-plus-square"></i>
            <i class="fab fa-linkedin"></i>
            <i class="fab fa-youtube-square"></i>
        </div>
        <div class="cr">
            <p>COPYRIGHT &copy;2020. ALL RIGHTS RESERVED. DESIGNED BY <span> Zeon company</span></p>
        </div>
    </footer>

            <!-- end footer -->
 
</body>
</html>