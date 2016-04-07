<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Hotel</title>
	<meta charset="utf-8">

	<link rel="stylesheet" href="styles/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="styles/confirmation.css">

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
			</div>
			<!-- Navbar header -->

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="Welcome">Home</a></li>
					<li><a href="MyPhotoBook">My Photo Book</a></li>
					<li class="active"><a href="Hotel">Hotel</a></li>
					<li><a href="Transportation.html">Transportation</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div>
		<!-- Container -->
	</nav>
	<!-- Navbar -->

	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="images/airport_chairs.jpg" alt="airport">
				</div>
				<div class="item">
					<img src="images/business_man.jpg" alt="airport">
				</div>
				<div class="item">
					<img src="images/palms.jpg" alt="airport">
				</div>
				<div class="item">
					<img src="images/hotel_room.jpg" alt="airport">
				</div>
				<div class="item">
					<img src="images/terrace.jpg" alt="airport">
				</div>
				<form class="col-sm-12" id="confirmation" action="Hotel" method = "post">
					<div class="form-group col-sm-4 col-sm-offset-4">
						<div class="input-group">
							<input type="text" name = "confirmationNumber" class="form-control" placeholder="Enter hotel confirmation Number" aria-label="true">
							<div class="input-group-btn">
								<button type="submit" class="btn btn-default">
									<i class="glyphicon glyphicon-circle-arrow-right"></i>
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
		<!-- Invalid Modal -->
	<div class="modal fade" id="invalid-modal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close btn btn-lg btn-primary" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span></button>
					<h3 class="modal-title">No results found</h3>
				</div>
				<div class="modal-body">
					<div class="well">
						<p>We could not find any hotel plans with the specified confirmation number. Please modify your search or create a new plan.</p>
					</div>
					<button class="btn btn-md btn-default" data-dismiss="modal" type='button'>Ok</button>
				</div><!-- modal body ends -->
			</div><!-- modal content ends -->
		</div><!-- modal dialog ends -->
	</div><!-- invalid modal ends -->


	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>

	<c:if test="${invalid}">
		<script src="js/invalid.modal.js"></script>
	</c:if>
</body>

<footer class="container-fluid text-center">
	<a href="#">Terms of Service</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
	<a href="#">Privacy Policy</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
	<a href="AboutUs.html">About Us</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="#">Contact Us</a></p>
</footer>
</html>