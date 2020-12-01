<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Flight</title>
<link rel="stylesheet" type="text/css" href="static/css/search_flight.css">
<link href="static/css/style.css" rel="stylesheet" type="text/css" media="all">
<link href="static/css/owl.carousel.css" rel="stylesheet" type="text/css" media="all">
<!-- include jquery ui -->
<link href='https://ajax.aspnetcdn.com/ajax/jquery.ui/1.10.4/themes/smoothness/jquery-ui.css' rel='stylesheet'>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.19.2/axios.js"></script>
<script src="static/jq/jquery.min.js"> 
    </script>

<script src="static/jq/jquery-ui.min.js"> </script>
    
</head>
<body>


   <div class="title">
	<p >Flight Ticket Booking</p>
   </div>

   <div class="all">

   <div class="container">

    <form action="/get-all-planes">
      
      <div class="trip-type">
        <div class="content">
      	<ul>
      		<li><a id="Round" href="#tab1" onclick="checkview('Round');">Round Trip</a></li>
      		<li><a id="One-way" href="#tab2" onclick="checkview('One-way');">One Way</a></li>
      	</ul>
      </div>
      </div>


    <div class="first">

      <div class="info">
      	<div class="sub">
        

        <div class="from">
      	   <label>From</label> <br>
      	   <input type="text" name="from" placeholder="Type Depatuare City" required><br>
        </div>


        <div class="date">
      	    <label>Depart</label><br>
      	    <input type="text" class="calender" name="depart" placeholder="Depart Date" required><br>
      	</div>


      	<div class="date" id="hide-return">
      	   <label>Return</label><br>
      	   <input type="text" class="calender2" name="retturn" placeholder="Return Date"><br>
      	</div>


      </div>
      </div>

      <!-- start section nuber 2 -->

     <div class="info">
      	<div class="sub">
        

        <div class="from">
      	   <label>To</label> <br>
      	   <input type="text" name="to" placeholder="Type Destination City" required><br>
        </div>


        <div class="degree">
      	  <label>Class</label><br>
      	  <select name="degree">
      		<option value="Economy" selected > Economy </option>
      		<option value="Perimume" > Perimume Economy  </option>
      		<option value="Business"> Business </option>
      		<option value="Class"> First Class</option>
      	  </select>
      	</div>
     <!-- end section nuber 2 -->
      </div>
    </div>

  </div>

  <!-- end first section -->  

    <div class="clear"></div>


    <!-- start last section -->
    <div class="last">
       
        <div class="info">
        <div class="sub">
        




        <div class="number">
           <label>Child:(2-11 years )</label><br>
           <input type="text" name="child" placeholder="Type here" value="0">
        </div>
        
        <div class="number">
           <label>Adult:(12+ years )</label><br>
           <input type="text" name="adult" placeholder="Type here" value="1">
           <br>
        </div>

    
        <input type="submit" name="submit" value="Search Flight">

      </div>
      </div>




     </div>
    <!-- end last section -->

    </form>

  </div>

</div>
<script type="text/javascript">
   
      var but1= document.getElementById("Round");
      var but2= document.getElementById("One-way");
      console.log(but1);
      console.log(but2);
	
	but1.addEventListener('click',(e)=>{ 
        
	    	document.getElementById("hide-return").style.display ="block";
     
	});
	

	but2.addEventListener('click',(e)=>{

		  document.getElementById("hide-return").style.display ="none";

	});
    </script>
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

</body>
</html>