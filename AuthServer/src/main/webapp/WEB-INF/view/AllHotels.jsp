<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zeon.UserPortable.Model.Hotel" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show All Hotels</title>
	
	<!-- include bootstrap css --> 

     <link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css" href="static/css/AllHotels.css">


	<!-- include bootstrap js -->
	<script type="text/javascript" src="static/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	
</head>
<body>



<!-- start navbar -->

 		
 <div class="nv">
    <nav class="navbar navbar-inverse ">
   <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.html">ZeonCompany</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      
      <ul class="nav navbar-nav navbar-right">
      
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Sort Resault
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Lowest Price</a></li>
          <li><a href="#">The most expensive</a></li>
          
        </ul>
      </li>
      
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
            <h2 class="hd">These are all the resaults to your infomation that you enter</h2>
            <span class="ll"> </span>
    </article>
 </div>

 <div class="travels">


 <%
		
        Hotel[] hotels = new Hotel[20];
		hotels = (Hotel[]) request.getAttribute("hotels");
		
		int total[]=new int[20];
		total =(int[]) request.getAttribute("prices");

		for(int i=0;i<hotels.length;i++)
		{
		
			if(hotels[i]==null)
			{
				break;
			}
			
%>
 
   <div class="trip">

   	<div class="left logo">
   		<img src="<% out.print(hotels[i].getImg()); %>">
   	</div>
   	<div class="left section">
  	    <h2><%out.print(hotels[i].getName());%></h2>
  	    <p> <%out.print(hotels[i].getAddress());%></p>
  	    <p> <%out.print(hotels[i].getDegree());%> </p>
  	    <p> <%out.print(hotels[i].getEmail());%></p>
    </div>

    <div class="left price">
   		<p>Total Price: <span><%out.print(total[i]);%>$</span></p>
   	</div>
   	<div class="left more">
  	    <a href="hotel-info?id=<%out.print(hotels[i].getId());%>">Show More</a>
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
        <div class="footer-logo"><img src="static/img/logo-alt.png" alt="logo"></div>
        
        <div class="cr">
            <p>COPYRIGHT &copy;2020. ALL RIGHTS RESERVED. DESIGNED BY <span> Zeon company</span></p>
        </div>
    </footer>

    <!-- end footer -->



</body>
</html>