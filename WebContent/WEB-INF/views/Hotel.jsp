<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Hotel</title>
	<meta charset="utf-8">
	
	<!-- Hotel -->
	<link rel="stylesheet" href="styles/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="styles/hotel.css">
	
	<!-- Fonts CSS -->
	<link href='https://fonts.googleapis.com/css?family=Cinzel+Decorative:900,400|Special+Elite|Alegreya+SC:400,900|Fredericka+the+Great' rel='stylesheet' type='text/css'>
</head>

<body>
	<nav class="navbar navbar-fixed-top navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="Welcome">FMAS</a>
			</div><!-- Navbar header -->

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="Welcome">Home</a></li>
					<li><a href="MyPhotoBook">My Photo Book</a></li>
					<li class = "active"><a href="Hotel">Hotel</a></li>
					<li><a href="Transportation.html">Transportation</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div><!-- Container -->
	</nav><!-- Navbar -->

	<div class="container">
		<div class = "row">
			<div class = "col-md-12">
				<h1 class = "page-header">Hotel Confirmation Info</h1>
			</div>
		</div>
		<div class = "row">
			<div class = "col-md-6">
				<table class="table table-condensed ">
					<tr>
						<!-- Guest Name -->
						<th>Guest Name:</th>
						<td>${CurrentUser.name}</td>
					</tr>
					<tr>
						<!-- Hotel Name -->
						<th>Hotel Name:</th>
						<td>${hotel.hotelName }</td>
					</tr>
					<tr>
						<!-- Hotel Address -->
						<th>Hotel Name:</th>
						<td>${hotel.hotelAddress}, ${hotel.hotelCity}, ${hotel.hotelState} ${hotel.hotelZip}</td>
					</tr>
					<tr>
						<!-- confirmation number -->
						<th>Confirmation Number:</th>
						<td>${hotel.confirmationNumber}</td>
					</tr>
					<tr>
						<!-- Room Type -->
						<th>Room Type:</th>
						<td>One King Bed</td>
					</tr>
					<tr>
						<!-- check in date-->
						<th>Check-In Date:</th>
						<td>${hotel.checkInDate }</td>
					</tr>
					<tr>
						<!-- check out date-->
						<th>Check-Out Date:</th>
						<td>${hotel.checkOutDate }</td>
					</tr>
					<tr>
						<!--Total Price-->
						<th>Total Price:</th>
						<td>$280.00</td>
					</tr>
				</table>

				<a href = "Hotel"><button type="submit" class="btn btn-default btn-block">New Search</button></a>
		</div>
		<div class = "col-md-4 col-md-offset-1">
					<iframe src="http://cdnres.willyweather.com/widget/loadView.html?id=42017" width="100%" height="277" frameborder="0" scrolling="no"></iframe>
		</div>
	</div>
	</div>
	<div class = "row search">
		<div class = "col-md-12 text-center">
		<h2 class = "page-header">Explore the hotels</h2>
		<div class = "well">
			<h4>
				<a href="http://www.ritzcarlton.com/en/Default.htm" class="label label-default" target="frame">Ritz Carlton</a>	
				<a href="http://www.hyatt.com/" class="label label-default" target="frame">Hyatt</a> 
				<a href="http://doubletree3.hilton.com/en/index.html" class="label label-default" target="frame">Double Tree</a>
				<a href="http://embassysuites3.hilton.com/en/index.html" class="label label-default" target="frame">Embassy Suites</a>
				<a href="http://www3.hilton.com/en/index.html" class="label label-default" target="frame">Hilton</a>
				<a href="http://www.ihg.com/holidayinn/hotels/us/en/reservation" class="label label-default" target="frame">Holiday Inn</a>
				<a href="http://www.marriott.com/default.mi" class="label label-default" target="frame">Marriot</a>
				<a href="http://www.starwoodhotels.com/sheraton/index.html?language=en_US" class="label label-default" target="frame">Sheraton</a>
				<a href="https://www.montecarlo.com/en.html" class="label label-default" target="frame">Monte Carlo</a>
			</h4>
		</div>
			<iframe id="frame" src="http://www.ritzcarlton.com/en/Default.htm" width=100% height=500px name = "frame"></iframe>
		</div>
	</div>

	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/iframe.js"></script>
</body>

<footer class="container-fluid text-center">
	<a href="#">Terms of Service</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
	<a href="#">Privacy Policy</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
	<a href="AboutUs.html">About Us</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="#">Contact Us</a></p>
</footer>

</html>