<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zeon.UserPortable.Model.Plane" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show All Flight</title>

	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css" href="static/css/showAllFlights.css">

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
          <li><a href="#">Shortest Duration</a></li>
          <li><a href="#">Earliest Departure</a></li>
          <li><a href="#">Latest Departure</a></li>
          <li><a href="#">Earliest Arrive</a></li>
          <li><a href="#">Latest Arrive</a></li>
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
            <span class="ll"></span>
    </article>
 </div>

 <div class="travels">
 
 
<%
		
        Plane[] planes = new Plane[20];
        planes = (Plane[]) request.getAttribute("planes");

        Plane[] planest = new Plane[20];
        planest = (Plane[]) request.getAttribute("planest");
        
        int total[]=new int[20];
		total =(int[]) request.getAttribute("total");
		int k=0;

		for(int i=0;i<planes.length;i++)
		{
			if(planes[i]==null)
			{
				break;
			}
			
			for(int j=0;j<planest.length;j++)
			{
		
				if(planest[j]==null)
				{
					break;
				}
			
%>


   <div class="trip">
   	<div class="left logo">
   		<img src="<% out.print(planes[i].getImg()); %>">
   	</div>
   	<div class="left section">
  	    <h2>from <% out.print(planes[i].getCountry_of_departure()); %> to <% out.print(planes[i].getCountry_of_arrival()); %></h2>
  	    <p> <% out.print(planes[i].getDate()); %></p>
  	    <p> by zeon company</p>
  	    <p> started at <% out.print(planes[i].getDepart_time()); %> PM and arrive at <% out.print(planes[i].getArrive_time()); %> AM</p>
    </div>
    
    <div class="line clear"><div class="middle"><hr></div></div>
    
    <div class="left logo">
   		<img src="static/img/planLogo.png">
   	</div>
   	
   	<div class="left section">
  	    <h2>from <% out.print(planest[j].getCountry_of_departure()); %> to <% out.print(planest[j].getCountry_of_arrival()); %></h2>
  	    <p> <% out.print(planest[j].getDate()); %></p>
  	    <p> by zeon company</p>
  	    <p> started at <% out.print(planest[j].getDepart_time()); %> PM and arrive at <% out.print(planest[j].getArrive_time()); %> AM</p>
    </div>

    <div class="left price">
   		<p>Total Price: <span><% out.print(total[k]); %>$</span></p>
   	</div>
   	<div class="left more">
  	    <a href="/after-search-flight">Show More</a>
    </div>

   </div>
   
      
<%
k++;
}
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