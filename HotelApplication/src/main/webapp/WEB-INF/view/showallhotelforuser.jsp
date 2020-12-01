<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zeon.HotelReservation.Modal.Hotel" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users Information</title>
<link rel="stylesheet" href="static/css/styles.css">
</head>
<body>


	<div class="title">
		<h3>All Hotels</h3>
		<hr>
	</div>
	
			
		<%
		
		Hotel hhh=new Hotel();
		
		HttpSession s=request.getSession();
		hhh =(Hotel)s.getAttribute("test");
		List<Hotel> hotels = (List<Hotel>)s.getAttribute("hotels");
		%>	
		
	
  
  	
		<%
		for(int i=0;i<hotels.size();i++)
		{
		%>
		
		<div class="wrapper">
		
		
		
        <div class="item item_1">
      <h3><% out.print(hotels.get(i).getName()) ; %></h3>
      <img src="<% out.print(hotels.get(i).getImg()) ; %>"  alt="cycle_image">
      <div class="info">
        <p><% out.print(hotels.get(i).getDegree()) ; %></p>
        <p>the price of rooms</p>
      </div>
      <div class="price">
         <h2>personal  :  <sup>$</sup><% out.print(hotels.get(i).getPrice_of_personal_room()) ;%><sub>/day</sub></h2>   
        <h2>dual  :  <sup>$</sup><% out.print(hotels.get(i).getPrice_of_dual_room()) ;%><sub>/day</sub></h2>
         <h2>three  :  <sup>$</sup><% out.print(hotels.get(i).getPrice_of_threeperson_room()) ;%><sub>/day</sub></h2>
      </div>
      <div class="btn">
          <a href="/enter-user-info?id=<%out.print(hotels.get(i).getId()); %> ">View More</a>
      </div>
  </div>
  
  <%
  if(i+1 >= hotels.size())
  {
	  break;
  }
  %>
    <div class="item item_2">
      <h3><% out.print(hotels.get(i+1).getName()) ; %></h3>
      <img src="<% out.print(hotels.get(i+1).getImg()) ; %>" alt="bike_image">
      <div class="info">
        <p><% out.print(hotels.get(i+1).getDegree()) ; %></p>
        <p>the price of rooms</p>
      </div>
      <div class="price">
         <h2>personal  :  <sup>$</sup><% out.print(hotels.get(i+1).getPrice_of_personal_room()) ;%><sub>/day</sub></h2>   
        <h2>dual  :  <sup>$</sup><% out.print(hotels.get(i+1).getPrice_of_dual_room()) ;%><sub>/day</sub></h2>
         <h2>three  :  <sup>$</sup><% out.print(hotels.get(i+1).getPrice_of_threeperson_room()) ;%><sub>/day</sub></h2>
      </div>
      <div class="btn">
          <a href="/enter-user-info?id=<%out.print(hotels.get(i+1).getId()); %> " >View More</a>  
      </div>
  </div>
  
   <%
  if(i+2 >= hotels.size())
  {
	  break;
  }
  %>
  
    <div class="item item_3">
      <h3><% out.print(hotels.get(i+2).getName()) ; %></h3>
      <img src="<% out.print(hotels.get(i+2).getImg()); %>" alt="bike_image">
      <div class="info">
        <p><% out.print(hotels.get(i+2).getDegree()) ; %></p>
        <p>the price of rooms</p>
      </div>
      <div class="price">
        <h2>personal  :  <sup>$</sup><% out.print(hotels.get(i+2).getPrice_of_personal_room()) ;%><sub>/day</sub></h2>   
        <h2>dual  :  <sup>$</sup><% out.print(hotels.get(i+2).getPrice_of_dual_room()) ;%><sub>/day</sub></h2>
         <h2>three  :  <sup>$</sup><% out.print(hotels.get(i+2).getPrice_of_threeperson_room()) ;%><sub>/day</sub></h2>
      </div>
      <div class="btn">
          <a href="/enter-user-info?id=<%out.print(hotels.get(i+2).getId()); %> ">View More</a>
      </div>
  </div>
</div>

         

<%
i=i+2;
}
%>
		
		
			
		
	

	
</body>
</html>