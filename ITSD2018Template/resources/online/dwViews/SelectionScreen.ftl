<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">
		<!--Webpage Css-->
		<style type="text/css">
		.title{position:relative;top:25px;}
		.title1{font-size:85px;font-family:"Algerian"; color:#333333;}
		.img{opacity:0.7;filter:alpha(opacity=70);}
		.button {
		background-color:#FFFFFF;
		border: none;
		color:#333333;
		border-radius: 12px;
		padding: 15px 32px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 60px;
		font-family:"Algerian";
		margin:140px 120px 0px 0px;
		width:400px;
		cursor: pointer;
	}
	.button2 {
		background-color:#FFFFFF;
		border: none;
		color:#333333;
		border-radius: 12px;
		padding: 15px 32px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 60px;
		font-family:"Algerian";
		width:400px;
		cursor: pointer;
	}
		</style>
		
	</head>

    <body style="background-image:url(http://c1.peakpx.com/wallpaper/80/264/971/rural-road-countryside-snow-wallpaper.jpg); background-repeat:no-repeat; background-size:100% auto;" onload="initalize()" > <!-- Call the initalize method when the page loads -->
    	
	<!-- Add your HTML Here -->
			
	<div class="title" align="center" vertical-align="middle">
	<div class="title1">
	Top Trumps
	</div>
	</div>
	<div class="img" align="center" vertical-align="middle">
	<a href="http://localhost:7777/toptrumps/game"><button class="button">Start Game</button></a>
	<a href="http://localhost:7777/toptrumps/stats"><button class="button2">View Past Statistics</button></a>
	</div>
	
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {

				connect();
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		<script type="text/javascript">
		
			//call database
			function connect() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/connectData");
			// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
  					var responseText = xhr.response; // the text of the response
				}
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}

		</script>
		
		</body>
</html>