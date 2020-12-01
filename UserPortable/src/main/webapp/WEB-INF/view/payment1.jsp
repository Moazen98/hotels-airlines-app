<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Interface</title>
    <!-- include bootstrap css -->
	<link rel="stylesheet" type="text/css" href="static/css/payment1.css">
</head>
<body>
	 
    <div class="payment">
        <form method="POST" action="/receivePayment">
            <div class="g-info">
                <div class="img"></div>
                <div class="fields">
                    <h2>payment section</h2>
                    <div class="section">
                        <h3>User Name</h3>
                        <input type="text" name="username">
                    </div>
                    
                    <div class="section">
                        <h3>User Email</h3>
                        <input type="text" name="useremail">
                    </div>
                    
                    <div class="section">
                        <h3>Account Id</h3>
                        <input type="text" name="accountId">
                    </div>
                    
                    <div class="section">
                        <h3>Account Password</h3>
                        <input type="password" name="password">
                    </div>
                    
                    <input value="Pay" type="submit">
                    
<!--
                    <div>
                        <p>Enter your payment method</p>
                        <button> Payment By Our Bank</button>
                        <button> Payment By Paypal</button>
                    </div>
-->
                    
                </div>
            </div>
            
<!--
            <div class="pay-info">
                <div>
                    <input placeholder="Enter Your Account Name" type="text">
                    <input placeholder="Enter Your Account Password" type="password">
                    <input value="submit" type="submit">
                </div>
                <div class="img"></div>
            </div>
-->
        </form>
    </div>
   
</body>
</html>