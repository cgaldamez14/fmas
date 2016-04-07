<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset= utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>My Photobook</title>

	<!-- CSS -->
	<link rel="stylesheet" href="styles/bootstrap.css">
	<link rel="stylesheet" href="styles/photobook.css">

	<!-- Fonts CSS -->
	<link href='https://fonts.googleapis.com/css?family=Cinzel+Decorative:900,400|Special+Elite|Alegreya+SC:400,900|Fredericka+the+Great' rel='stylesheet' type='text/css'>
</head>

<body class="full">
	<!-- Navbar Section -->
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

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="Welcome">Home</a></li>
					<li class="active"><a href="#">My Photo Book</a></li>
					<li><a href="Hotel">Hotel</a></li>
					<li><a href="Transportation.html">Transportation</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div><!-- container ends -->
	</nav><!-- navbar section ends -->

	<div class="jumbotron">
		<h1>My Photobooks</h1>
	</div>
	
	<!-- Google Map Division -->
	<div id="map"></div>
	
	<!-- Empty section under map -->
	<div class="empty-well"></div>

	<%-- After photobook objects are created by querying db they put in hidden fields to be accessed by js script --%>
	<c:forEach items="${photobooks}" var="photobook" varStatus="loop">
		<img class="books" id="photobook${loop.index}" src='#' data-rel='fh5-light-box-demo' data-href='http://online.anyflip.com/memm/${photobook.url}/' data-width='800' data-height='625' data-title='${photobook.name}'>
		<input type="hidden" name="name${loop.index}" value="${photobook.name}">
		<input type="hidden" name="description${loop.index}" value="${photobook.description}">
		<input type="hidden" name="lat${loop.index}" value="${photobook.latitude}">
		<input type="hidden" name="lon${loop.index}" value="${photobook.longitude}">
	</c:forEach>

	<script>
		function initMap() {
			/* Photobook view is not fully implemented so lat and lon values are hardcoded.
			Once implemented lat and lon will be given to the js from the db*/
			var mapCenter = {lat : 39.8333, lng : -98.5833};
			var points = [{lat : 41.2227041, lng : -112.0427139},
			              {lat : 39.1722733, lng : -84.6834601}, 
			              {lat : 39.8838019, lng : -75.4134768},
			              {lat : 35.2546817, lng : -101.6460667},
			              {lat : 41.2095246, lng : -124.0068332}];

			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 5,
				center : mapCenter,
				keyboardShortcuts : false,
				mapTypeControl : false,
				panControl : false,
				rotateControl : false,
				scaleControl : false,
				scrollwheel : false,
				streetViewControl : false,
				zoomControl : false
			});
			
			// Create marker for each lat and lon pair
			for (var i = 0; i < eval("${count}"); i++)
				createMarker(points[i], map, i);
		}

		function createMarker(n, map, index) {
			// Descriptions for each marker
			var contentString = [
			 		"<h2>Christmas 2015</h2><br><p>Christmas time is here, everyone is in a feeling of cheer. It was a Christmas to remember; as it was Granda Tywins' last. Ogden had such a wonderful parade. We were able to take so many family pictures, with the set up backdrops. Ethan and Missi weren't able to reach the top of the Christmas tree so bart, our dog, helped lift them up.</p>",
					"<h2>OctoberFest 2014</h2><br><p>Octoberfest. How we love thee. You bring us great cheer with the giant mugs of beer. All the people are either happily buzzed or miserably hung over. We have the crazies to the beauties walking around. Oh...and lets not forget all the nights where we danced to the music that made us fall off tables. Ahhh, Octoberfest how I wish I could remember everything...but at least we have pictures to piece together the moments that are really blank.</p>",
					"<h2>Pumpkin Patch 2013</h2><br><p>A rare occasion where the whole family gets together. Aunt Martha and Uncle Benjin were even able to attend. The little ones really enjoyed the pumpkin patch and hay ride. The older kids enjoyed the local made apple cider and teasing the adults about being too slow. Little Mary Lu lost her first baby teeth on this trip and Sally and Johnson got engaged.</p>",
					"<h2>Route 66 Road Trip 2016</h2><br><p>TBW</p>",
					"<h2>Camping Summer 2016</h2><br><p>TBW</p>"]
			
			var infowindow = new google.maps.InfoWindow({
				content : contentString[index]
			});
			
			// Icon relative paths
			var images = [ 'images/photobooks/icon/tree.png',
						   'images/photobooks/icon/beer.png',
						   'images/photobooks/icon/pumpkin.png',
						   'images/photobooks/icon/car.png',
			               'images/photobooks/icon/tent.png']
			
			var marker = new google.maps.Marker({
				position : n,
				map : map,
				icon : images[index],
			});

			google.maps.event.addListener(marker, 'click', function() {
				document.getElementById('photobook' + index).click();
			});

			marker.addListener('mouseover', function() {
				infowindow.open(map, marker);
			});

			google.maps.event.addListener(marker, 'mouseout', function() {
				infowindow.close(map, marker);
			});

		}
	</script>
	
	<script src="//anyflip.com/plugin/LightBox/js/anyflp-light-box-api-min.js"></script>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCMQ2trZD7ScdWVyFfS2oetxliqjDdt9FA&callback=initMap"></script>
</body>

<footer class="container-fluid text-center"> 
	<a href="#">Terms of Service</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
	<a href="#">Privacy Policy</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="AboutUs.html">About Us</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="#">Contact Us</a> 
</footer>

</html>