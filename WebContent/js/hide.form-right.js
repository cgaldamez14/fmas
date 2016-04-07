$(window).load(function(){
	$('#left-invalid-modal').modal('show');
});

$(document).ready(function(){
	
	$("#create_form").show();
	$("#ticket-purchased").hide();
	$(".left-form").show();
	
	$('#new-plan').on('click',function(){	
			// "easing" which is the second parameter can be either swing or linear
			$("#create_form").slideDown(1000, "swing");
	});
	$('#close').on('click',function(){

			$("#create_form").slideUp(1000, "swing");

	});
	
	$('#ticket-purchased').click(function(){

			$(".right-form").hide( "slide", {direction: "left" }, 500 );
			 $( "#search" ).delay(300).fadeIn(500);
			$("#ticket-purchased").hide();
			$(".left-form").show( "slide", {direction: "right" }, 500 );


	});
	
	$('#search').click(function(){

		$(".left-form").hide( "slide", {direction: "right" }, 3000 );
		 $("#ticket-purchased").delay(3000).fadeIn(1000);
		$("#search").hide();
		$(".right-form").show( "slide", {direction: "left" }, 3000 );

	});
});