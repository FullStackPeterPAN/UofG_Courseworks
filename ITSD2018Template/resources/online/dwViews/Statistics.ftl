<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		
		<!--Webpage Css-->
		<style type="text/css">
		.title{position:relative;top:25px;}
		.title1{font-size:85px;font-family:"Algerian"; color:#333333;}
		#contenr{ position:absolute; width:100%; height:510px; top:200px;background-color:rgba(220,232,244,0.45);}
		.status{font-family:"Times New Roman"; font-size:30px;position:relative; width:40%; height:60px; top:15px; margin: 0 auto;background-color:rgba(255,255,255,0.6);}
		.status1{background-color:rgba(255,255,255,0.6);}
		</style>
	</head>

    <body style="background-image:url(http://c1.peakpx.com/wallpaper/80/264/971/rural-road-countryside-snow-wallpaper.jpg); background-repeat:no-repeat; background-size:100% auto;"onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	
<div class="title">
	<div class="title1" align="center" vertical-align="middle">
	Top Trumps
	</div>
	</div>
	<div id="contenr">
	<div class="status">
			<div  align="center" vertical-align="middle">
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
    			<tr>
        			<th align="center" height="60">Previous Data</th>
       			</tr>
  			</table>

			<table width="100%" border="0" cellspacing="1" cellpadding="4" bgcolor="#cccccc" align="center">
     			<tr>
        			<td>Number of games played overall</td>
        			<td><p id="overall">Loading...</p></td>
   				</tr>
    			<tr>
        			<td>How many times the computer has won </td>
        			<td><p id="AI">Loading...</p></td>
    			</tr>
    			<tr>
        			<td>How many times the human has won   </td>
        			<td><p id="human">Loading...</p></td>
    			</tr>
    			<tr>
        			<td>The average number of draws </td>
        			<td><p id="draws">Loading...</p></td>
    			</tr>
    			<tr>
        			<td>The largest number of rounds played in a single game </td>
        			<td><p id="largest">Loading...</p></td>
    			</tr>
			</table>
			</div>
		</div>
	</div>
			<!-- Add your HTML Here -->
		
		
		<script type="text/javascript">
			connect();
			// Method that is called on page load
			function initalize() {
				connect();
				countOverAll();	
				countAIWin()
				countHumanWin();
				countDraws();
				largestRounds();
			}
			
			
			
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
		
		<!-- Here are examples of how to call REST API Methods -->
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
			
			//count over all games
			function countOverAll() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/countOverAll");
			// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var res = xhr.response;				
					document.getElementById("overall").innerHTML=res;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}

			//count over AI win
			function countAIWin() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/countAIWin");
			// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var res = xhr.response;				
					document.getElementById("AI").innerHTML=res;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			//count over human win
			function countHumanWin() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/countHumanWin");
			// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var res = xhr.response;				
					document.getElementById("human").innerHTML=res;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			//count draws
			function countDraws() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/countDraws");
			// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var res = xhr.response;				
					document.getElementById("draws").innerHTML=res;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			//largest rounds
			function largestRounds() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/largestRounds");
			// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var res = xhr.response;				
					document.getElementById("largest").innerHTML=res;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
		</script>
		
		</body>
</html>