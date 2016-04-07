<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>FMAS Login</title>

	<!-- CSS -->
	<link rel="stylesheet" href="styles/bootstrap.css">
	<link rel="stylesheet" href="styles/login.css">

	<!-- Fonts CSS -->
	<link href='https://fonts.googleapis.com/css?family=Alegreya+SC:400,900&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
</head>

<body>
	<div class="row">
		<div class="col-md-4 login pull-right">
			<div class="jumbotron text-center">
				<h1>FMAS</h1>
				<h2>Flight Management Activity System</h2>
			</div>
			<%-- Shows invalid credentials error message if username or password are incorrect --%>
			<c:if test="${valid == 'no'}">
				<p class='text-danger text-center'><strong>Invalid Username and/or Password</strong></p>
			</c:if>
			<!-- Login Panel -->
			<div class="login_panel">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Login</h4>
					</div>
					<div class="panel-body">
						<form class="form-group" action="Login" method="post">
							<div class="row">
								<div class="col-md-12">
									<label for="username">Username:</label>
									<input class="form-control" type="text" name="username" /> <br>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<label for="password">Password:</label>
									<input class="form-control" type="password" name="password" /> <br>
								</div>
							</div>
							<input type="checkbox">&nbsp;&nbsp;Remember Me
							<div class="pull-right">
								<p>Not registered? Register <a href="" data-toggle="modal" data-target="#reg-modal">here</a>!</p><br>
								<button class="btn btn-md pull-right" type='submit'>Login</button>
							</div>
						</form><!-- login form ends -->
					</div><!-- panel-body ends -->
				</div><!-- panel ends -->
			</div><!-- login panel ends -->
		</div><!-- col-md-4 ends -->
	</div><!-- row ends -->
	
	<!-- Partners Section -->
	<div class="container partners">
		<div class="row">
			<div class="col-md-6">
				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<img src="images/main.png" alt="Image">
							<div class="carousel-caption">
								<h3>Flight Management Activity System</h3>
								<p>You are your own Travel Agent</p>
							</div>
						</div>
						<div class="item">
							<img src="images/flight-itinerary.jpg" alt="Image">
							<div class="carousel-caption">
								<h3>Track and Manage Flight Itinerary</h3>
							</div>
						</div>
						<div class="item">
							<img src="images/private-transportation.jpg" alt="Image">
							<div class="carousel-caption">
								<h3>Private Transportation<br>Recommendations and Assistance</h3>
							</div>
						</div>
						<div class="item">
							<img src="images/public-transportation.jpg" alt="Image">
							<div class="carousel-caption">
								<h3>Public Transportation Assistance</h3>
							</div>
						</div>
						<div class="item">
							<img src="images/directions.jpg" alt="Image">
							<div class="carousel-caption">
								<h3>Directions to and from Airport</h3>
								<p>Powered by Google</p>
							</div>
						</div>
					</div><!-- carousel inner ends -->
				</div><!-- my caru=ousel ends -->
			</div><!-- col-md-6 ends -->
			
			<div class="col-md-6 partner-sec">
				<div class="page-header">
					<h1>Partners</h1>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<h4 class="partner-heading">Transportation & Hotels</h4>
						<ul>
							<li>Uber</li>
							<li>Lyft</li>
							<li>Hyatt Hotels</li>
							<li>Marriott Hotels</li>
							<li>The Ritx-Carlton</li>
							<li>Radisson</li>
							<li>Double Tree</li>
							<li>Embassy Suites</li>
							<li>Sheraton</li>
						</ul>
					</div>
					<div class="col-md-6 col-xs-6">
						<h4 class="partner-heading">Airlines</h4>
						<ul>
							<li>United Airlines</li>
							<li>Delta Air Lines</li>
							<li>Southwest Airlines</li>
							<li>Alaska Airlines</li>
							<li>Frontier Airlines</li>
							<li>Hawaiian Airlines</li>
							<li>JetBlue Airways</li>
							<li>Spirit Airlines</li>
							<li>Virgin America</li>
						</ul>
					</div>
				</div><!-- row ends -->
			</div><!-- col-md-6 ends -->
		</div><!-- row ends -->
	</div><!-- container ends -->

	<!-- Register Modal -->
	<div class="modal fade modal-z registration" id="reg-modal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close btn btn-lg btn-primary" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h3 class="modal-title">Register</h3>
				</div>
				<div class="modal-body">
					<form class="form-group" action="Register" method="post">
						<div class="row">
							<div class="col-md-2 col-label">
								<label for="name">Full Name</label>
							</div>
							<div class="col-md-10 col-field">
								<input class="form-control" type="text" name="name" /><br>
							</div>
						</div><!-- row ends -->
						<div class="row">
							<div class="col-md-2 col-label">
								<label for="address">Address:</label>
							</div>
							<div class="col-md-10 col-field">
								<input type="text" class="form-control" id="address" name="address"><br>
							</div>
						</div><!-- row ends -->
						<div class="row">
							<div class="col-md-2 col-label">
								<label for="city">City:</label>
							</div>
							<div class="col-md-4 col-field">
								<input type="text" class="form-control" id="city" name="city"><br>
							</div>
							<div class="col-md-1 col-label">
								<label for="state">State:</label>
							</div>
							<div class="col-md-2 col-field">
								<input type="text" class="form-control" id="state" name="state"><br>
							</div>
							<div class="col-md-1 col-label">
								<label for="zip">Zip:</label>
							</div>
							<div class="col-md-2 col-field">
								<input type="text" class="form-control" id="zip" name="zip"><br>
							</div>
						</div><!-- row ends -->
						<div class="row">
							<div class="col-md-2 col-label">
								<label for="phone">Phone:</label>
							</div>
							<div class="col-md-10 col-field">
								<input class="form-control" type="text" id="phone" name="phone" /><br>
							</div>
						</div><!-- row ends -->
						<div class="row">
							<div class="col-md-2 col-label">
								<label for="emailr">Email:</label>
							</div>
							<div class="col-md-10 col-field">
								<input type="email" class="form-control" id="emailr" name="emailr"><br>
							</div>
						</div><!-- row ends -->
						<div class="row">
							<div class="col-md-2 col-label">
								<label for="password1">Password:</label>
							</div>
							<div class="col-md-10 col-field">
								<input type="password" class="form-control" id="password1" name="password1"><br>
							</div>
						</div><!-- row ends -->
						<div class="row">
							<div class="col-md-2 col-label">
								<label for="password2">Re-Enter Password:</label>
							</div>
							<div class="col-md-10 col-field">
								<input type="password" class="form-control" id="password2" name="password2"><br>
							</div>
						</div><!-- row ends -->
						<button class="btn btn-md btn-default pull-right" type='submit'>Register</button>
					</form><!-- registration form ends -->
				</div><!-- modal body ends -->
			</div><!-- modal content ends -->
		</div><!-- modal dialog ends -->
	</div> <!-- modal ends -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	<!-- JavaScript -->
	<script src="js/bootstrap.js"></script>
</body>

<footer class="container-fluid pull-left">
	<a href="#">Terms of Service</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="#">Privacy Policy</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
	<a href="AboutUs.html">About Us</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="#">Contact Us</a>
</footer>

</html>