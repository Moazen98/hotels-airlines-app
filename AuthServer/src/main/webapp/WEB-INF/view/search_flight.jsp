<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="static/css/search_flight.css">
  <script type="text/javascript">
    function checkview(id) {
      a = document.getElementById(id);
      if(a.href[a.href.length - 1] == 1){
        document.getElementsByClassName('blue')[0].style.display = 'none';
        document.getElementById('hide-return').style.visibility = "visible";
      }
      else if(a.href[a.href.length - 1] == 2){
        document.getElementById('hide-return').style.visibility = "hidden";
        document.getElementsByClassName('blue')[0].style.display = 'none';
      }
      else if(a.href[a.href.length - 1] == 3){
        document.getElementById('hide-return').style.visibility = "hidden";
        document.getElementsByClassName('blue')[0].style.display = '';
      }
    }
    //window.onload= function(){ document.getElementsByClassName('blue')[0].style.display = 'none'; }
    </script>
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
      		<li><a id="Multi" href="#tab3" onclick="checkview('Multi');">Multi City</a></li>
        
      	</ul>
      </div>
      </div>


    <div class="first">

      <div class="info">
      	<div class="sub">
        

        <div class="from">
      	   <label>From</label> <br>
      	   <input type="text" name="from" placeholder="Type Depatuare City"><br>
        </div>


        <div class="date">
      	    <label>Depart</label><br>
      	    <input type="date" name="depart"><br>
      	</div>


      	<div class="date" id="hide-return">
      	   <label>Return</label><br>
      	   <input type="date" name="retturn"><br>
      	</div>

        
        
        <div class="add_remove"> 
           <button>Add City +</button>
           <button>Remove</button>  
        </div>

      </div>
      </div>

      <!-- start section nuber 2 -->

     <div class="info">
      	<div class="sub">
        

        <div class="from">
      	   <label>To</label> <br>
      	   <input type="text" name="to" placeholder="Type Destination City"><br>
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


    <!-- start blue section -->
    <div class="blue" style="display: none;">
      
        <div class="info">
        <div class="sub">
        

        <div class="from">
           <label>From</label> <br>
           <input type="text" name="from_multy" placeholder="Type Depatuare City"><br>
        </div>


        <div class="date">
            <label>Depart</label><br>
            <input type="date" name="depart_multy"><br>
        </div>


        

      </div>
      </div>

      <!-- start section nuber 2 -->

     <div class="info">
        <div class="sub">
        

        <div class="from">
           <label>To</label> <br>
           <input type="text" name="to_multy" placeholder="Type Destination City"><br>
        </div>


        </div>
    </div>


    </div>

    <!-- end blue section -->


    <div class="clear"></div>


    <!-- start last section -->
    <div class="last">
       
        <div class="info">
        <div class="sub">
        




        <div class="number">
           <label>Child:(2-11 years )<br>
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



</body>
</html>