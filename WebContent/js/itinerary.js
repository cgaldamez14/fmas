
$(document).ready(function(){
	var origin = true;
	var main = true;
	$('.flight-info').on('click',function(){	
			if(origin){
				$("#origin").hide();
				$("#c-text1").hide();
				$("#destination").show();
				$("#c-text2").show();
				origin = false;
			}
			else{
				$("#destination").hide();
				$("#c-text2").hide();
				$("#origin").show();
				$("#c-text1").show();
				origin = true;
			}
	});
	
	$('.offcanvas-btn').on('click',function(){	
		if(main){
			$("#maps").hide();
			$("#itinerary").show();
			main = false;
		}
		else{
			$("#itinerary").hide();
			$("#maps").show();
			main = true;
		}
});
});