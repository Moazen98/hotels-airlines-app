var city = "Syria";
//console.log(city);

//<script>
$(document).ready(function() {
	$("#submitBtn").click(function() {
		//$("#myForm").submit(); // Submit the form
		 city = $(".inn").val();
		console.log(city);
	});

//</script>			
//$("#submitBtn").submit(function() {
	
$.getJSON("http://api.openweathermap.org/data/2.5/weather?q="+city+"&units=imperial&appid=d1f2d24005e7ac2614e5bad1a8d963d4",
		function(data){
	
	console.log(data);
	var icon = "http://api.openweathermap.org/img/w/"+data.weather[0].icon+".png";
	var temp = data.main.temp;
	var weather = data.weather[0].main;
	$(".icon").attr("src",icon);
	$(".temp").append(temp);
	$(".weather").append(weather);
	//console.log(city);
	
	
})
//})

});