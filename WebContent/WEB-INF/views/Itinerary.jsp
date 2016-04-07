<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<%-- Options for two different tab titles depends on whether they are creating a new plan
	or viewing a previously made one --%>
	<c:choose>
		<c:when test='${param.id == null }'>
			<title>New Flight Plan</title>
		</c:when>
		<c:otherwise>
			<title>Flight Itinerary</title>
		</c:otherwise>
	</c:choose>

	<!-- CSS -->
	<link rel="stylesheet" href="styles/bootstrap.css">
	<link rel="stylesheet" href="styles/itinerary.css">

	<!-- Fonts CSS -->
	<link href='https://fonts.googleapis.com/css?family=Cinzel+Decorative:900,400|Special+Elite|Alegreya+SC:400,900|Waiting+for+the+Sunrise' rel='stylesheet' type='text/css'>
</head>

<body>
	<!-- Navbar Section -->
	<nav class="navbar navbar-fixed-top navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="Welcome">FMAS</a>
			</div><!-- navbar header ends-->

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="Welcome">Home</a></li>
					<li><a href="MyPlans">My Travel Plans</a></li>
					<li><a href="MyPhotoBook">My Photo Book</a></li>
					<li><a href="Hotel">Hotel</a></li>
					<li><a href="Transportation.html">Transportation</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div><!-- container ends--> 
	</nav><!-- navbar ends-->

	<!-- Left Vertical Button -->
	<p class="static-btn">
		<button type="button" class="btn offcanvas-btn" data-toggle="offcanvas">
			<div id="maps">M<br>A<br>P<br>S</div>
			<div id="itinerary">I<br>T<br>I<br>N<br>E<br>R<br>A<br>R<br>Y</div>
		</button>
	</p>
	
	<!-- Photobook Ad on Right  -->
	<div class="well">
		<a href="MyPhotoBook"><img id="ad" src="images/Photobook_AD.png" alt=""></a>
	</div>
	
	<!-- Main Content -->
	<div class="container-main">
		<div class="jumbotron">
			<div class="text-center">
				<h1>Flight Itinerary</h1>
				<h3>FMAS is a proud supporter of you being your own Travel Agent</h3>
				<%-- If plan has not been saved it will display a save button otherwise it will 
				display a delete plan button --%>
				<c:choose>
					<c:when test="${param.id == null}">
						<a href="SavePlan"><button type="button" class="btn btn-md pull-right default-btn">Save Plan</button></a>
					</c:when>
					<c:otherwise>
						<a href="DeletePlan?id=${param.id}"><button type="button" class="btn btn-md pull-right default-btn">Delete Plan</button></a>
					</c:otherwise>
				</c:choose>
			</div>
		</div><!-- jumbotron ends -->

		<!-- Off Canvas Sections -->
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-md-12">
				<div class="row text-center">
					<div class="col-md-6">
						<div class="page-header">
							<h2>${flight.airline.name}:: Flight ${flight.airline.fs}${flight.flightNumber}</h2>
						</div>
						<div class="flight-info info">
							<div class="curtain">
								<h2 id="c-text1">Flight Destination</h2>
								<h2 id="c-text2">Flight Origin</h2>
							</div>
							<div id="origin">
								<p>${flight.airports[0].name}</p>
								<p>${flight.airports[0].city}, ${flight.airports[0].state}</p>
								<p>${flight.departureDate}</p>
								<p>${flight.departureTime}</p>
								<p>Terminal ${flight.departureTerminal}</p>
							</div>
							<div id="destination">
								<p>${flight.airports[1].name}</p>
								<p>${flight.airports[1].city}, ${flight.airports[1].state}</p>
								<p>${flight.arrivalDate}</p>
								<p>${flight.arrivalTime}</p>
								<p>Terminal ${flight.arrivalTerminal}</p>
							</div>
						</div>
						<c:if test="${param.id == null}">
							<a href="SessionReset"><button type="button" class="btn btn-md btn-block fmas-btn">Choose Another Flight</button></a>
						</c:if>
					</div><!-- col-md-6 ends-->
					
					<div class="col-md-6">
						<div class="page-header">
							<h2>Hotel Itinerary</h2>
						</div>
						<%-- Displays a form if hotel information has not been entered otherwise it shows
						the hotel information --%>
						<c:choose>
							<c:when test="${hotel == null}">
								<form class="form-group" action="NewPlan" method="post">
									<div class="row">
										<div class="col-md-6">
											<input class="form-control" type="text" name="confirm" placeholder="Confirmation Number" />
										</div>
										<div class="col-md-6">
											<input type="text" class="form-control" id="rooms" name="rooms" placeholder="# Rooms">
										</div>
									</div><!-- row ends -->
									<div class="row">
										<div class="col-md-12">
											<input class="form-control" type="text" name="name" placeholder="Hotel Name" />
										</div>
									</div><!-- row ends -->
									<div class="row">
										<div class="col-md-12">
											<input type="text" class="form-control" id="address" name="address" placeholder="Hotel Address">
										</div>
									</div><!-- row ends -->
									<div class="row">
										<div class="col-md-4">
											<input type="text" class="form-control" id="city" name="city" placeholder="City">
										</div>
										<div class="col-md-4">
											<input type="text" class="form-control" id="state" name="state" placeholder="State">
										</div>
										<div class="col-md-4">
											<input type="text" class="form-control" id="zip" name="zip" placeholder="Zip">
										</div>
									</div><!-- row ends -->
									<div class="row">
										<div class="col-md-6">
											<input class="form-control" type="text" id="in" name="in" placeholder="Check-In Date (eg. mm/dd/yyyy)" />
										</div>
										<div class="col-md-6 col-field">
											<input class="form-control" type="text" id="out" name="out" placeholder="Check-Out Date (eg. mm/dd/yyyy)" />
										</div>
									</div><!-- row ends -->

									<c:if test="${param.id == null}">
										<div class="row">
											<div class="col-md-12">
												<button id="submit" class="btn btn-md btn-block fmas-btn" type='submit' style='margin-top: 62px;'>Update Hotel Info</button>
											</div>
										</div><!-- row ends -->
									</c:if>
								</form><!-- form ends -->
							</c:when>
							<c:otherwise>
								<div class="text-center info hotel-info">
									<p>Confirmation Number : : ${hotel.confirmationNumber}</p>
									<p>${hotel.hotelName}</p>
									<p>${hotel.hotelAddress},${hotel.hotelCity}, ${hotel.hotelState} ${hotel.hotelZip}</p>
									<p>${hotel.checkInDate}-${hotel.checkOutDate}</p>
									<p>${hotel.numberOfRooms}Rooms</p>
								</div>
								
								<!-- Needed for Google Maps JS -->
								<input type="hidden" name="haddress" value="${hotel.hotelAddress}">
								<input type="hidden" name="hcity" value="${hotel.hotelCity}">
								<input type="hidden" name="hstate" value="${hotel.hotelState}">
								<input type="hidden" name="hzip" value="${hotel.hotelZip}">
							</c:otherwise>
						</c:choose>
					</div> <!-- col-md-6 for hotel ends -->
				</div><!-- row ends -->

				<!-- Weather Section -->
				<div class="page-header">
					<h2>10 Day Weather Forecast : : ${flight.airports[1].city}, ${flight.airports[1].state}</h2>
				</div>
				<div class="weather">
					<div class="container">
						<table class="table table-condensed table-hover table-striped">
							<thead>
								<tr>
									<th><p>Date</p></th>
									<th><p class="text-center">Description</p></th>
									<th><p class="text-center">Temperature</p></th>
									<th><p class="text-center">Humidity</p></th>
									<th><p class="text-center">Wind</p></th>
								</tr>
							</thead>
							<tbody>
								<%-- Each element of the weather attribute is placed on the table--%>
								<c:forEach items="${weather}" var="day">
									<tr>
										<td><img src="${day.icon_url}" class="icon"><h4><strong>${day.date}</strong></h4></td>
										<td class="text-center">${day.conditions}</td>
										<td class="text-center">${day.temperature}</td>
										<td class="text-center">${day.humidity}%</td>
										<td class="text-center">${day.wind}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table><!-- table ends -->
					</div><!-- container ends -->
				</div><!-- eather ends -->


				<!-- Map and Terminal Section -->
				<div class="col-md-12 sidebar-offcanvas" id="sidebar">
					<div class="panel-group map-panel" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse1"> ${CurrentUser.address} ${CurrentUser.city}, ${CurrentUser.state} ${CurrentUser.zip} to ${flight.airports[0].name} </a>
								</h4>
							</div><!-- panel-heading ends-->
							<div id="collapse1" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="map" id="homeToAirportMap"></div>
									<div class="right-panel" id="homeToAirportDirections"></div>
								</div>
							</div>
						</div><!-- panel ends-->
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse2"> ${flight.airports[1].name} to Hotel </a>
								</h4>
							</div>
							<div id="collapse2" class="panel-collapse collapse in">
								<div class="panel-body">
									<c:choose>
										<c:when test="${hotel != null}">
											<div class="map" id="hotelToAirportMap"></div>
											<div class="right-panel" id="hotelToAirportDirections"></div>
										</c:when>
										<c:otherwise>
											<h5>Enter the hotel information to generate the map.</h5>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div><!-- panel ends-->
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse3"> ${flight.airports[0].name} Terminal Maps</a>
								</h4>
							</div><!-- panel-heading ends-->
							<div id="collapse3" class="panel-collapse collapse">
								<div class="panel-body">
									<img src="images/airport_maps/${flight.airports[0].state}/${flight.airports[0].fs}/main.png" alt="">
									<img src="images/airport_maps/${flight.airports[0].state}/${flight.airports[0].fs}/${flight.departureTerminal}.png" alt="">
								</div>
							</div>
						</div><!-- panel ends -->
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse4"> ${flight.airports[1].name} Terminal Maps</a>
								</h4>
							</div><!-- panel-heading ends-->
							<div id="collapse4" class="panel-collapse collapse">
								<div class="panel-body">
									<img src="images/airport_maps/${flight.airports[1].state}/${flight.airports[1].fs}/main.png" alt="">
									<img src="images/airport_maps/${flight.airports[1].state}/${flight.airports[1].fs}/${flight.arrivalTerminal}.png" alt="">
								</div>
							</div>
						</div><!-- panel ends-->
					</div><!-- panel-group ends -->
				</div><!-- sidebar-offcanvas end -->
			</div><!-- col-md-12 ends-->
		</div><!-- row-offcanvas ends -->
	</div><!-- container-main ends -->

	<!-- Information needed for google directions Javascript -->
	<form>
		<input type="hidden" name="addressg" value="${CurrentUser.address}">
		<input type="hidden" name="cityg" value="${CurrentUser.city}">
		<input type="hidden" name="stateg" value="${CurrentUser.state}">
		<input type="hidden" name="zipg" value="${CurrentUser.zip}">
	</form>


	<!-- jQuery and Javascript-->
	<script src="js/jquery.js"></script>
	<script src="js/itinerary.js"></script>
	<script src="js/offcanvas.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCMQ2trZD7ScdWVyFfS2oetxliqjDdt9FA&callback=initMap"></script>

	<script>
		function initMap() {
			getMap1('homeToAirportMap', 'homeToAirportDirections');
			getMap2('hotelToAirportMap', 'hotelToAirportDirections');
		}

		function getMap1(mapID, directionsID) {
			var directionsDisplay = new google.maps.DirectionsRenderer;
			var directionsService = new google.maps.DirectionsService;
			var address = document.getElementsByName("addressg")[0].value;
			var city = document.getElementsByName("cityg")[0].value;
			var state = document.getElementsByName("stateg")[0].value;
			var zip = document.getElementsByName("zipg")[0].value;
			var start = address + ", " + city + ", " + state + " " + zip;
			var end = eval("${flight.airports[0].lat}") + ","
					+ eval("${flight.airports[0].lon}");

			var map = new google.maps.Map(document.getElementById(mapID), {
				scrollwheel : false,
				navigationControl : false,
				mapTypeControl : false,
				draggable : false,
				zoomControl : false
			});
			directionsDisplay.setMap(map);
			directionsDisplay.setPanel(document.getElementById(directionsID));

			calculateAndDisplayRoute(directionsService, directionsDisplay,
					start, end);
		}

		function getMap2(mapID, directionsID) {
			var directionsDisplay = new google.maps.DirectionsRenderer;
			var directionsService = new google.maps.DirectionsService;
			var address = document.getElementsByName("haddress")[0].value;
			var city = document.getElementsByName("hcity")[0].value;
			var state = document.getElementsByName("hstate")[0].value;
			var zip = document.getElementsByName("hzip")[0].value;
			var start = eval("${flight.airports[1].lat}") + ","
					+ eval("${flight.airports[1].lon}");
			var end = address + ", " + city + ", " + state + " " + zip;

			var map = new google.maps.Map(document.getElementById(mapID), {
				scrollwheel : false,
				navigationControl : false,
				mapTypeControl : false,
				draggable : false,
				zoomControl : false
			});
			directionsDisplay.setMap(map);
			directionsDisplay.setPanel(document.getElementById(directionsID));

			calculateAndDisplayRoute(directionsService, directionsDisplay,
					start, end);
		}

		function calculateAndDisplayRoute(directionsService, directionsDisplay, address1, address2) {
			var start = address1;
			var end = address2;
			directionsService.route({
				origin : start,
				destination : end,
				travelMode : google.maps.TravelMode.DRIVING
			}, function(response, status) {
				if (status === google.maps.DirectionsStatus.OK) {
					directionsDisplay.setDirections(response);
				} else {
					//window.alert('Directions request failed due to ' + status);
				}
			});
		}
	</script>
</body>

<footer class="container-fluid text-center"> 
	<a href="#">Terms of Service</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="#">Privacy Policy</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="AboutUs.html">About Us</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="#">Contact Us</a>
</footer>

</html>