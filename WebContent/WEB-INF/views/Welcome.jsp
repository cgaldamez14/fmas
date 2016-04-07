<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Welcome</title>

	<!-- CSS -->
	<link rel="stylesheet" href="styles/bootstrap.css">
	<link rel="stylesheet" href="styles/welcome.css">

	<!-- Fonts CSS -->
	<link href='https://fonts.googleapis.com/css?family=Cinzel+Decorative:900,400|Special+Elite|Alegreya+SC:400,900|Fredericka+the+Great' rel='stylesheet' type='text/css'>
</head>

<body>
	<!-- Navigation Bar Section -->
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
			</div><!-- navigation bar header -->

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="Welcome">Home</a></li>
					<li><a href="MyPhotoBook">My Photo Book</a></li>
					<li><a href="Hotel">Hotel</a></li>
					<li><a href="Transportation.html">Transportation</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div><!-- container -->
	</nav><!-- navigation bar ends -->
	
	<!-- Hidden Form that will be shown when new plan button is clicked -->
	<div id="create_form">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="text-center vertical-align options">
						<a class="option-text" id="ticket-purchased">Already have ticket</a>
					</div>
					<!-- Left hidden form -->
					<div class="left-form">
						<form action="Welcome" method="post">
							<h3>Choose airline and enter the flight number and departure date:</h3>
							<select class="form-control" name="name">
								<option value="">Choose the airline you are flying with</option>
								<%--Options are saved in the application scope, for each loop iterated through them to set them as options.
								This is only for presentation purposes. Actual application will have a form that auto-completes as user types --%>
								<c:forEach items="${airlines}" var="airline">
									<c:choose>
										<c:when test="${name == airline}">
											<option value="${airline}" selected="selected">${airline}</option>
										</c:when>
										<c:otherwise>
											<option value="${airline}">${airline}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select><br>
							<%-- After form is submitted if no results were found sticky form is displayed with previously entered flight info.
							Following code chooses between placing a black form or form with flight information. --%>
							<%-- Invalid is in the request scope, it is true if not flight information was found --%>
							<c:choose>
								<c:when test="${invalid == true}">
									<input class="form-control" type="text" name="flight-num" placeholder="Flight Number (eg. 1234)" value="${flightNum}" />
									<div class="row row20">
										<div class="col-md-4 col-xs-4">
											<input class="form-control" type="text" name="month" placeholder="Month" value="${month}" />
										</div>
										<div class="col-md-4 col-xs-4">
											<input class="form-control" type="text" name="day" placeholder="Day" value="${day}" />
										</div>
										<div class="col-md-4 col-xs-4">
											<input class="span1 form-control" type="text" name="year" placeholder="Year" value="${year}" />
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<input class="form-control" type="text" name="flight-num" placeholder="Flight Number (eg. 1234)" />
									<div class="row row20">
										<div class="col-md-4 col-xs-4">
											<input class="form-control" type="text" name="month" placeholder="Month" />
										</div>
										<div class="col-md-4 col-xs-4">
											<input class="form-control" type="text" name="day" placeholder="Day" />
										</div>
										<div class="col-md-4 col-xs-4">
											<input class="span1 form-control" type="text" name="year" placeholder="Year" />
										</div>
									</div>
								</c:otherwise>
							</c:choose>
							<button class="btn btn-md btn-default pull-right" type='submit' name="form" value="left">Search</button>
						</form> <!-- end of form -->
					</div><!-- end of left form -->
				</div><!-- end of col-md-6 -->

				<div class="col-md-6">
					<div class="text-center vertical-align options">
						<a class="option-text" id="search">Search for future flights</a>
					</div>
					<!-- Right hidden form -->
					<div class="right-form">
						<form action="Welcome" method="post">
							<h3>Choose departure/arrival airport and enter departure date:</h3>
							<select class="form-control" name="depart">
								<option value="">Choose the airport you wish to depart from</option>
								<%-- Some concept as the left-form. Airports are also saved on application scope. But this is only for 
								presentation purposes --%>
								<%--<c:forEach items="${airports}" var="airport">--%>
									<c:choose>
										<c:when test="${depart == airports[0]}">
											<option value="${airports[0]}" selected="selected">${airports[0]}</option>
										</c:when>
										<c:otherwise>
											<option value="${airports[0]}">${airports[0]}</option>
										</c:otherwise>
									</c:choose>
								<%--</c:forEach>--%>
							</select><br> <select class="form-control" name="arrive">
								<option value="">Choose the airport you wish to arrive to</option>
								<c:forEach items="${airports}" var="airport">
									<c:choose>
										<c:when test="${arrive == airport}">
											<option value="${airport}" selected="selected">${airport}</option>
										</c:when>
										<c:otherwise>
											<option value="${airport}">${airport}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<%-- Again this part choose a blank form or form with previously entered information. All based on whether 
							flight was found--%>
							<c:choose>
								<c:when test="${invalid == true}">
									<div class="row row20">
										<div class="col-md-4 col-xs-4">
											<input class="form-control" type="text" name="month2" placeholder="Month" value="${month2}" />
										</div>
										<div class="col-md-4 col-xs-4">
											<input class="form-control" type="text" name="day2" placeholder="Day" value="${day2}" />
										</div>
										<div class="col-md-4 col-xs-4">
											<input class="span1 form-control" type="text" name="year2" placeholder="Year" value="${year2}" />
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="row row20">
										<div class="col-md-4 col-xs-4">
											<input class="form-control" type="text" name="month2" placeholder="Month" />
										</div>
										<div class="col-md-4 col-xs-4">
											<input class="form-control" type="text" name="day2" placeholder="Day" />
										</div>
										<div class="col-md-4 col-xs-4">
											<input class="span1 form-control" type="text" name="year2" placeholder="Year" />
										</div>
									</div>
								</c:otherwise>
							</c:choose>
							<button class="btn btn-md btn-default pull-left" type='submit' name="form" value="right">Search</button>
						</form><!-- end of form -->
					</div><!-- end of right form -->
				</div><!-- end of col-6-md  -->
			</div><!-- end of row -->
		</div> <!-- end of container for hidden form -->
		<!-- Large button that hides form -->
		<a id="close" class="btn-block text-center lg-form-btn" href="#" role="button"> <span class="glyphicon glyphicon-chevron-up" aria-hidden="true"></span></a>
	</div><!-- end of hidden form division -->


	<div class="row">
		<div class="col-md-12">
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<div class="carousel-inner" role="listbox">
					<c:forEach items="${images}" var="image">
						<c:choose>
							<c:when test="${image.id == 1}">
								<div class="item active">
									<img src="${image.path}" alt="Image">
								</div>
								<div class="carousel-caption">
									<h3 class="welcome">Welcome, ${CurrentUser.name}!</h3>
								</div>
							</c:when>
							<c:otherwise>
								<div class="item">
									<img src="${image.path}" alt="Image">
								</div>
								<div class="carousel-caption">
									<h3 class="welcome">Welcome, ${CurrentUser.name}!</h3>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<!-- buttons shown on top of carousel -->
					<div class="buttons">
						<div><img src="images/logo.png" alt="" height=260 width=260></div>
						<button type="button" id="new-plan" class="btn btn-block side-btn">New Travel Plan</button>
						<a href="#plans"><button type="button" class="btn btn-block side-btn">My Travel Plans</button></a>
						<a href="#history"><button type="button" class="btn btn-block side-btn">Travel History</button></a>
						<a href="MyPhotoBook"><button type="button" class="btn btn-block side-btn">My Photo Book</button></a>
						<a href="#"><button type="button" class="btn btn-block side-btn">Account Settings</button></a>
					</div>
				</div>
			</div><!-- my caurosel ends -->
			
			<!-- Carousel Controls -->
			<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> 
			<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div><!-- col-md-12 ends -->
	</div><!-- row ends -->
	
	<!-- Upcomings Flight Plans Section -->
	<div id="plans" class="plans">
		<div class="container">
			<div class="page-header2">
				<h1>Upcoming Flights</h1>
			</div>
			<table class="table table-hover table-condensed">
				<tbody data-link="row" class="rowlink">
					<%-- Flights planned that are occuring on the current day or later are shown here after being queried in db --%>
					<c:forEach items="${plans}" var="plan">
						<tr>
							<td class="text-center"><a href="Itinerary?id=${plan.id}"><p>${plan.airline.fs}${plan.flightNumber}</p></a></td>
							<td class="text-center"><p>${plan.departureDate}</p></td>
							<td class="text-center"><p>${plan.departureTime}</p></td>
							<td class="text-center"><p>${plan.airports[0].city}, ${plan.airports[0].state} to ${plan.airports[1].city}, ${plan.airports[1].state}</p></td>
						</tr>
					</c:forEach>
				</tbody>
			</table><!-- table ends -->
		</div><!-- container ends -->
	</div><!-- plans section ends -->

	<!-- Past Flight Section -->
	<div class="history" id="history">
		<div class="container">
			<div class="page-header2">
				<h1>Past Flights</h1>
			</div>
			<div class="panel-group history-panel" id="accordion">
				<%-- Flights planned that are before current day are shown here after being queried in db --%>
				<c:forEach items="${old}" var="old" varStatus="loop">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapse${loop.index + 1}"><p>${old.departureDate}<span class="pull-right"> ${old.airports[1].city}, ${old.airports[1].state}</span></p></a>
							</h4>
						</div><!-- panel-heading -->
						<div id="collapse${loop.index + 1}" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="row">
									<div class=col-md-3>
										<img src='http://online.anyflip.com/memm/${photobooks[loop.index].url}/files/shot.jpg' data-rel='fh5-light-box-demo' data-href='http://online.anyflip.com/memm/${photobooks[loop.index].url}/' data-width='700' data-height='425' data-title='tree' height='100' width='100'>
									</div>
									<div class=col-md-9>
										<p style="padding-top: 15px;">${old.airports[0].name}</p>
										<p>${old.airline.name} Flight ${old.flightNumber}</p>
									</div>
								</div> <!-- panel-body row ends -->
							</div><!-- panel body ends -->
						</div>
					</div><!-- panel -->
				</c:forEach>
			</div><!-- accordion ends -->
		</div><!-- container ends -->
	</div><!-- history section ends -->

	<!-- Partners Section -->
	<div class="partners">
		<div class="container text-center">
			<h2 class="page-header">Our Partners</h2>
			<br>
			<div class="well">
				<div class="row">
					<div class="col-sm-2">
						<img src="images/uber.png" class="img-responsive" style="width: 100%" alt="Image">
					</div>
					<div class="col-sm-2">
						<img src="images/lyft.png" class="img-responsive" style="width: 100%" alt="Image">
					</div>
					<div class="col-sm-2">
						<img src="images/hilton.png" class="img-responsive" style="width: 100%" alt="Image">
					</div>
					<div class="col-sm-2">
						<img src="images/ritzcarlton.png" class="img-responsive" style="width: 100%" alt="Image">
					</div>
					<div class="col-sm-2">
						<img src="images/marriott.png" class="img-responsive" style="width: 100%" alt="Image">
					</div>
					<div class="col-sm-2">
						<img src="images/radisson.png" class="img-responsive" style="width: 100%" alt="Image">
					</div>
				</div><!-- row ends -->
			</div><!-- well ends -->
		</div><!-- container ends -->
	</div><!-- partners section ends -->

	<!-- Results Modal -->
	<div class="modal fade" id="results-modal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close btn btn-lg btn-primary" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<c:if test="${flight != null}">
						<h3 class="modal-title">Flight ${flight.airline.fs}${flight.flightNumber}</h3>
					</c:if>
					<c:if test="${flights != null}">
						<h3 class="modal-title">Flights departing on ${flights[0].departureDate}</h3>
					</c:if>
				</div>
				<div class="modal-body">
					<%-- Displays info for only one flight result --%>
					<c:if test="${flight != null}">
						<div class="well">
							<p><strong>Carrier Name:</strong> ${flight.airline.name} (${flight.airline.fs})</p>
							<p><strong>Flight Number:</strong> ${flight.flightNumber}</p>
							<p><strong>Departing From:</strong> ${flight.airports[0].name} (${flight.airports[0].fs})</p>
							<p><strong>Arriving At:</strong> ${flight.airports[1].name} (${flight.airports[1].fs})</p>
							<p><strong>Departure Terminal:</strong> ${flight.departureTerminal}</p>
							<p><strong>Arriving Terminal:</strong> ${flight.arrivalTerminal}</p>
							<p><strong>Departure Time:</strong> ${flight.departureTime}</p>
							<p><strong>Arrival Time:</strong> ${flight.arrivalTime}</p>
							<p><strong>Departure Date:</strong> ${flight.departureDate}</p>
							<p><strong>Arrival Date:</strong> ${flight.arrivalDate}</p>
						</div><!-- well ends -->

						<p>Is the above information correct?</p>
						<a href="NewPlan?name=${flight.airline.name}&num=${flight.flightNumber}&date=${flight.departureDate}&airport=${flight.airports[0].fs}">
							<button class="btn btn-md btn-default" type='button'>Yes</button>
							<button class="btn btn-md btn-default" data-dismiss="modal" type='button'>No</button>
						</a>
					</c:if>
					<%-- Displays info for all flight results as table links --%>
					<c:if test="${flights != null}">
						<div class="well">
							<div class="scroll-pane">
								<table class="table table-striped table-bordered table-hover">
									<tbody data-link="row" class="rowlink">
										<%-- List of flight is iterated and all flights are displayed on table --%>
										<c:forEach items="${flights}" var="flight">
											<tr>
												<td><a href="NewPlan?name=${flight.airline.name}&num=${flight.flightNumber}&date=${flight.departureDate}&airport=${flight.airports[0].fs}"><strong>${flight.airline.name}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${flight.departureTime} - ${flight.arrivalTime}<br>${flight.airports[0].name} <strong>to</strong> ${flight.airports[1].name}</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table><!-- table ends -->
							</div><!-- scrollpane ends -->
						</div><!-- well ends -->
					</c:if>
				</div><!-- modal body ends -->
			</div><!-- modal content ends -->
		</div><!-- modal dialog ends-->
	</div><!-- results modal ends -->

	<!-- Invalid Modal -->
	<div class="modal fade" id="left-invalid-modal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close btn btn-lg btn-primary" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h3 class="modal-title">Flight Not Found</h3>
				</div>
				<div class="modal-body">
					<div class="well">
						<p>We could not find ${name} flight ${flightNum} departing on ${month}/${day}/${year}. Make the sure the information you entered is correct!</p>
					</div>
					<button class="btn btn-md btn-default" data-dismiss="modal" type='button'>Ok</button>
				</div><!-- modal body ends -->
			</div><!-- modal content ends -->
		</div><!-- modal dialog ends -->
	</div><!-- invalid modal ends -->

	<!-- Invalid Modal -->
	<div class="modal fade" id="right-invalid-modal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close btn btn-lg btn-primary" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span></button>
					<h3 class="modal-title">No results found</h3>
				</div>
				<div class="modal-body">
					<div class="well">
						<p>We could not find any flights departing from ${depart} on ${month2}/${day2}/${year2} and arriving at ${arrive}. Please modify your search.</p>
					</div>
					<button class="btn btn-md btn-default" data-dismiss="modal" type='button'>Ok</button>
				</div><!-- modal body ends -->
			</div><!-- modal content ends -->
		</div><!-- modal dialog ends -->
	</div><!-- invalid modal ends -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	
	<!-- Photobook js -->
	<script src="//anyflip.com/plugin/LightBox/js/anyflp-light-box-api-min.js"></script>
	
	<!-- Javascript -->
	<script src="js/rowlink.js"></script>
	<script src="js/bootstrap.js"></script>

	<c:if test="${flight != null or flights != null}">
		<script src="js/results.modal.js"></script>
	</c:if>
	<c:if test="${form == null}">
		<script src="js/hide.form-both.js"></script>
	</c:if>
	<c:if test="${form == 'left'}">
		<script src="js/hide.form-right.js"></script>
	</c:if>
	<c:if test="${form == 'right'}">
		<script src="js/hide.form-left.js"></script>
	</c:if>
	
	<!-- Scroll Pane js -->
	<script>
		$(function() {
			$('.scroll-pane').jScrollPane();
		});
	</script>
</body>

<footer class="container-fluid text-center"> 
	<a href="#">Terms of Service</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="#">Privacy Policy</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="AboutUs.html">About Us</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="#">Contact Us</a>
</footer>

</html>
