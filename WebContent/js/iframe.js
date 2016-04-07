$(document).ready(function(){
    $(".label").click(function(e) {
        e.preventDefault();
        
        $("#frame").attr("src", $(this).attr("href"));
    })
});