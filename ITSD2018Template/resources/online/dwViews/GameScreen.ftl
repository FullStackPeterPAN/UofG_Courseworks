<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,800" rel="stylesheet">
		
		<!--Webpage Css-->
		<style type="text/css">
		.title{position:relative;top:12px;}
		.title1{font-size:85px;font-family:"Algerian"; color:#333333;}
		.img{opacity:0.7;filter:alpha(opacity=70);}
		
		#contenr{ position:absolute; width:100%; height:700px; top:200px;background-color:			rgba(220,232,244,0.6);}
		.status{font-family:"Calibri";position:relative; width:56%; height:70px; top:15px; margin: 0 auto;background-color:rgba(255,255,255,0.6);}
		.status1{background-color:rgba(255,255,255,0.6);}
		.statusfont{font-family:"Calibri";font-size: 20px;}
		.buttonarea{position:relative; width:60%; height:50px; top:30px; margin: 0 auto;}
		
		.button3{
			background-color:#FFFFFF;
			border: none;
			color:#333333;
			border-radius: 12px;
			padding: 15px 32px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 20px;
			font-family:"Calibri";
			width:120px;
			height:80px;
			cursor: pointer;
			margin:5px 10px 5px 35px;
		}
	
		.container {
  width: 90%;
  top: 300px;
}
.shipinfo p {
  font-size: 20px;
  margin: 3px;
}
.shipimg img{
 
  width:100% ;
  height: 100px;
}
.shipname {
 
  max-width:40;
  font-size: 22;
  color:#333333;
  font-family:"Calibri";
}
.playername {
  float: left;
  max-width:50%;
  font-family:"Calibri";
  color:#333333;
  ;
}
.cardheader {
  width:100%;
  margin:0px;
  padding:5px 10px 0px 10px;
  min-height: 40px;
  background-color:#CCCCCC;
  border-radius: 12px;
}

.card {
  margin: 15px;
  max-width: 220px;
  padding: 0px;
  background-color:#CCCCCC;
/*  min-height:340px;
*/ border-radius: 12px;
}

.catfont{
font-family:"Calibri";
 float: left;
 color:#333333;
 font-size:20px;
}
	.dropdown {
   	 	position: relative;
    	display: inline-block;
	}
	.dropdown-content {
    	display: none;
    	position: absolute;
    	background-color: #f9f9f9;
	   	min-width: 150px;
	    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		border-radius: 12px;
		font-family:"Calibri";
	}
	.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}
.dropdown-content a:hover {background-color:#CCCCCC;}
.dropdown:hover .dropdown-content {display: block;}
.dropdown:hover .button3 {background-color:#CCCCCC;}

		</style>

	</head>

    <body style="background-image:url(http://c1.peakpx.com/wallpaper/80/264/971/rural-road-countryside-snow-wallpaper.jpg); background-repeat:no-repeat; background-size:100% auto;" onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
			<!-- Add your HTML Here --><h1></h1>
		<div class="title" align="center" vertical-align="middle">
		<div class="title1">
		Top Trumps
		</div>
		</div>
		
		<div id="contenr">
			<div class="status">
			<div class="statusfont">
			<p><p id="now">Now:Please choose rivals </p></p>
			</div>
		</div>
		
<div class="buttonarea" align="center" vertical-align="middle">
		<button class="button3" onClick="loadCard()" id="start_round">Shuffle Cards</button>
<div class="dropdown">
	<button class="button3" id="choose_rivals" disabled="true">Choose Rivals</button>
	<div style="display:block;" id= "hidden">
	<div class="dropdown-content" id="drop">
    <a href="#" onClick="setRival1()">1 Rival</a>
	<a href="#" onClick="setRival2()">2 Rivals</a>
    <a href="#" onClick="setRival3()">3 Rivals</a>
    <a href="#" onClick="setRival4()">4 Rivals</a>
  </div>
  </div>
  </div>
<button class="button3"  onClick="showCard()" id="showCard" disabled="true">New Round</button>
<button class="button3" onClick="logic()" id="nextRound" disabled="true">Compare Cards</button>
<button class="button3" onClick="javascript:location.href='http://localhost:7777/toptrumps'" id="return" >Selection Page</button>
</div>
</div>
<!--Card area-->

	<div class="container" id="ca" >

<div class="row justify-content-sm-center">
<div style="display:none;" id="AI1">
	<div class="col-sm card" >
	
            <div class="cardheader">
              <div class="playername">
                <h2>AI 1</h2>
              </div>
              <div class="shipname" id="AI1_name">
                <h2>Type</h2>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img id="AI1_image" src="https://www.macmillandictionary.com/external/slideshow/full/Grey_full.png" class="img-rounded" />
              </div>
              <div class="catfont">
              <p id="AI1_cat1">--</p>
              <p id="AI1_cat2">--</p>
              <p id="AI1_cat3">--</p>
              <p id="AI1_cat4">--</p>
              <p id="AI1_cat5">--</p>
             
				<p id="AI1_cards_left">Num+left</p>
			 </div>
						</div>
						</div>
          </div>
<div style="display:none;" id="AI2">
	<div class="col-sm card" >
	
            <div class="cardheader">
              <div class="playername">
                <h2>AI 2</h2>
              </div>
              <div class="shipname" id="AI2_name">
                <h2>Type</h2>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img id="AI2_image" src="https://www.macmillandictionary.com/external/slideshow/full/Grey_full.png" class="img-rounded" />
              </div>
              <div class="catfont">
              <p id="AI2_cat1">--</p>
              <p id="AI2_cat2">--</p>
              <p id="AI2_cat3">--</p>
              <p id="AI2_cat4">--</p>
              <p id="AI2_cat5">--</p>
             
				<p id="AI2_cards_left">Num+left</p>
			 </div>
						</div>
						</div>
          </div>
          <div style="display:none;" id="You">
		  <div class="col-sm card" >
            <div class="cardheader">
              <div class="playername">
                <h2>You </h2>
              </div>
              <div class="shipname">
                <div id="u_cardname">Type</div>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img id="u_image" src="https://www.macmillandictionary.com/external/slideshow/full/Grey_full.png" class="img-rounded" />
              </div>
              
                <button type="button" onClick="setSize()" class="btn btn-secondary btn-sm btn-block " id="u_cat1" ></button>
                <button type="button" onClick="setSpeed()" class="btn btn-secondary btn-sm  btn-block " id="u_cat2" ></button>
                <button type="button" onClick="setRange()" class="btn btn-secondary btn-sm btn-block " id="u_cat3" ></button>
                <button type="button" onClick="setFirepower()" class="btn btn-secondary btn-sm btn-block " id="u_cat4" ></button>
                <button type="button" onClick="setCargo()" class="btn btn-secondary btn-sm btn-block " id="u_cat5" ></button>
                </p>
                <p id="u_cards_left" class="catfont"> </br>Num+left</p>
               
            </div>
            </div>
          </div>
          <div style="display:none;" id="AI3">
		  <div class="col-sm card"  >
            <div class="cardheader">
              <div class="playername">
                <h2>AI 3</h2>
              </div>
              <div class="shipname">
                <div id="AI3_name">Type</div>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img id="AI3_image" src="https://www.macmillandictionary.com/external/slideshow/full/Grey_full.png" class="img-rounded" />
              </div>
              <div class="catfont">
              <p id="AI3_cat1">--</p>
              <p id="AI3_cat2">--</p>
              <p id="AI3_cat3">--</p>
              <p id="AI3_cat4">--</p>
              <p id="AI3_cat5">--</p>
				<p id="AI3_cards_left">Num+left</p>
			  </div>            
            </div>
           </div>
          </div>
          <div style="display:none;" id="AI4" >
		  <div class="col-sm card" >
            <div class="cardheader">
              <div class="playername">
                <h2>AI 4</h2>
              </div>
              <div class="shipname">
                <div id="AI4_name">Type</div>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img id="AI4_image" src="https://www.macmillandictionary.com/external/slideshow/full/Grey_full.png" class="img-rounded" />
              </div>
              <div class="catfont">
              <p id="AI4_cat1">--</p>
              <p id="AI4_cat2">--</p>
              <p id="AI4_cat3">--</p>
              <p id="AI4_cat4">--</p>
              <p id="AI4_cat5">--</p>
              <p id="AI4_cards_left">Num+left</p>
			 </div>
            </div>
          </div>
          </div>
    </div>

	
		
		<script type="text/javascript">
					
			// variable for storing the number of rivals
			var rivalNum=0;
			var category="";
			var winner = "";
			var k = 0;
			var playerN = 0;
			var left0 = 0;
			var left1 = 0;
			var left2 = 0;
			var left3 = 0;
			var left4 = 0;
						
			// Method that is called on page load
			function initalize() {

			//test

			}
			
			
			function loadCard()
			{
				// need to choose at least one AI player
				if(rivalNum==0)	{
					document.getElementById("now").innerHTML="Please choose the number of rivals!";
				}
				
				else {
					//start the game, shuffle and divide the card to player
					startTheGame(rivalNum + 1);
					
					//disable the botton
					var dis1=document.getElementById('start_round');
					dis1.disabled="disabled";
					var dis2=document.getElementById('showCard');
					dis2.disabled=false;
					document.getElementById("now").innerHTML="The cards have been shuffled, now you can press New Round to play! ";
				}
			}
			
		    //function for showing card area
			function showCard()
			{
				// need to choose at least one AI player
				if(rivalNum==0)
				{
					document.getElementById("now").innerHTML="Please choose the number of rivals!";
				}
				else {
					choiceAI();
					winner = getWinner();
					var img1 = document.getElementById("AI1_image");
					img1.src = "https://www.macmillandictionary.com/external/slideshow/full/Grey_full.png";
					var img2 = document.getElementById("AI2_image");
					img2.src = "https://www.macmillandictionary.com/external/slideshow/full/Grey_full.png";
					var img3 = document.getElementById("AI3_image");
					img3.src = "https://www.macmillandictionary.com/external/slideshow/full/Grey_full.png";
					var img4 = document.getElementById("AI4_image");
					img4.src = "https://www.macmillandictionary.com/external/slideshow/full/Grey_full.png";															
					document.getElementById("AI1_name").innerHTML ="--";
					document.getElementById("AI2_name").innerHTML ="--";
					document.getElementById("AI3_name").innerHTML ="--";
					document.getElementById("AI4_name").innerHTML ="--";					
					document.getElementById("AI1_cat1").innerHTML ="Size: --";
					document.getElementById("AI2_cat1").innerHTML ="Size: --";
					document.getElementById("AI3_cat1").innerHTML ="Size: --";
					document.getElementById("AI4_cat1").innerHTML ="Size: --";
					document.getElementById("AI1_cat2").innerHTML ="Speed: --";
					document.getElementById("AI2_cat2").innerHTML ="Speed: --";
					document.getElementById("AI3_cat2").innerHTML ="Speed: --";
					document.getElementById("AI4_cat2").innerHTML ="Speed: --";
					document.getElementById("AI1_cat3").innerHTML ="Range: --";
					document.getElementById("AI2_cat3").innerHTML ="Range: --";
					document.getElementById("AI3_cat3").innerHTML ="Range: --";
					document.getElementById("AI4_cat3").innerHTML ="Range: --";
					document.getElementById("AI1_cat4").innerHTML ="Firepower: --";
					document.getElementById("AI2_cat4").innerHTML ="Firepower: --";
					document.getElementById("AI3_cat4").innerHTML ="Firepower: --";
					document.getElementById("AI4_cat4").innerHTML ="Firepower: --";
					document.getElementById("AI1_cat5").innerHTML ="Cargo: --";
					document.getElementById("AI2_cat5").innerHTML ="Cargo: --";
					document.getElementById("AI3_cat5").innerHTML ="Cargo: --";
					document.getElementById("AI4_cat5").innerHTML ="Cargo: --";										
					
					if(rivalNum==1){
						var show=document.getElementById('You');
						show.style.display='block';
						var show1=document.getElementById('AI1');
						show1.style.display='block';
						showName(0);
						showSize(0);
						showSpeed(0);
						showRange(0);
						showFirepower(0);
						showCargo(0);
						left0 = showHandLeft(0);
						left1 = showHandLeft(1);																																
					}
				
					else if(rivalNum==2){
						var show=document.getElementById('You');
						show.style.display='block';
						var show1=document.getElementById('AI1');
						show1.style.display='block';
						var show2=document.getElementById('AI2');
						show2.style.display='block';
						showName(0);
						showSize(0);
						showSpeed(0);
						showRange(0);
						showFirepower(0);
						showCargo(0);
						left0 = showHandLeft(0);
						left1 = showHandLeft(1);						
						left2 = showHandLeft(2);																									
					}
				
					else if(rivalNum==3){
						var show=document.getElementById('You');
						show.style.display='block';
						var show1=document.getElementById('AI1');
						show1.style.display='block';
						var show2=document.getElementById('AI2');
						show2.style.display='block';
						var show3=document.getElementById('AI3');
						show3.style.display='block';
						showName(0);
						showSize(0);
						showSpeed(0);
						showRange(0);
						showFirepower(0);
						showCargo(0);
						left0 = showHandLeft(0);
						left1 = showHandLeft(1);						
						left2 = showHandLeft(2);
						left3 = showHandLeft(3);																					
					}
				
					else if(rivalNum==4){
						var show=document.getElementById('You');
						show.style.display='block';
						var show1=document.getElementById('AI1');
						show1.style.display='block';
						var show2=document.getElementById('AI2');
						show2.style.display='block';
						var show3=document.getElementById('AI3');
						show3.style.display='block';
						var show4=document.getElementById('AI4');
						show4.style.display='block';
						showName(0);
						showSize(0);
						showSpeed(0);
						showRange(0);
						showFirepower(0);
						showCargo(0);
						left0 = showHandLeft(0);
						left1 = showHandLeft(1);						
						left2 = showHandLeft(2);
						left3 = showHandLeft(3);
						left4 = showHandLeft(4);																				
					}
				}
				
					var dis1=document.getElementById('showCard');
					dis1.disabled=true;
			}
			
			//function for choosing the number of rivals
			function setRival1()
				{
					rivalNum=1;
					var dis=document.getElementById('choose_rivals');
					dis.disabled="disabled";
					var drop=document.getElementById('hidden');
					drop.style.display='none';
				}
			function setRival2()
				{
					rivalNum=2;
					var dis=document.getElementById('choose_rivals');
					dis.disabled="disabled";
					var drop=document.getElementById('hidden');
					drop.style.display='none';
				}
			function setRival3()
				{
					rivalNum=3;
					var dis=document.getElementById('choose_rivals');
					dis.disabled="disabled";
					var drop=document.getElementById('hidden');
					drop.style.display='none';
				}
			function setRival4()
				{
					rivalNum=4;
					var dis=document.getElementById('choose_rivals');
					dis.disabled="disabled";
					var drop=document.getElementById('hidden');
					drop.style.display='none';
				}

			//function for human to choose the category
			function setSize()	{
					category = "Size";
					document.getElementById("now").innerHTML = "The select category is: " + category+"<br>Please press Compare Cards to continue.";
					var dis=document.getElementById('nextRound');
					dis.disabled=false;
				}
			function setSpeed() {
					category = "Speed";
					document.getElementById("now").innerHTML = "The select category is: " + category+"<br>Please press Compare Cards to continue.";
					var dis=document.getElementById('nextRound');
					dis.disabled=false;
				}
			function setRange() {
					category = "Range";
					document.getElementById("now").innerHTML = "The select category is: " + category+"<br>Please press Compare Cards to continue.";
					var dis=document.getElementById('nextRound');
					dis.disabled=false;
				}
			function setFirepower() {
					category = "Firepower";
					document.getElementById("now").innerHTML = "The select category is: " + category+"<br>Please press Compare Cards to continue.";
					var dis=document.getElementById('nextRound');
					dis.disabled=false;
				}
			function setCargo() {
					category = "Cargo";
					document.getElementById("now").innerHTML = "The select category is: " + category+"<br>Please press Compare Cards to continue.";
					var dis=document.getElementById('nextRound');
					dis.disabled=false;
				}

			// logic of the game
			function logic() {
						showName(0);
						showName(1);
						showName(2);
						showName(3);	
						showName(4);					
						showSize(0);
						showSize(1);						
						showSize(2);
						showSize(3);
						showSize(4);
						showSpeed(0);
						showSpeed(1);						
						showSpeed(2);
						showSpeed(3);
						showSpeed(4);
						showRange(0);
						showRange(1);						
						showRange(2);
						showRange(3);
						showRange(4);			
						showFirepower(0);
						showFirepower(1);						
						showFirepower(2);
						showFirepower(3);
						showFirepower(4);	
						showCargo(0);
						showCargo(1);						
						showCargo(2);
						showCargo(3);
						showCargo(4);
				
				
				if(winner=="Player") {
					if(category=="Size"){
						rules("Size");
						var dis1=document.getElementById('showCard');
						dis1.disabled=false;
						var dis2=document.getElementById('nextRound');
						dis2.disabled=true;
					}
				
					else if(category==("Speed")) {
						rules("Speed");
						var dis1=document.getElementById('showCard');
						dis1.disabled=false;
						var dis2=document.getElementById('nextRound');
						dis2.disabled=true;				
					}
					else if(category==("Range")) {
						rules("Range");
						var dis1=document.getElementById('showCard');
						dis1.disabled=false;
						var dis2=document.getElementById('nextRound');
						dis2.disabled=true;				
					}
					else if(category==("Firepower")) {
						rules("Firepower");
						var dis1=document.getElementById('showCard');
						dis1.disabled=false;
						var dis2=document.getElementById('nextRound');
						dis2.disabled=true;					
					}
					else if(category==("Cargo")) {
						rules("Cargo");
						var dis1=document.getElementById('showCard');
						dis1.disabled=false;
						var dis2=document.getElementById('nextRound');
						dis2.disabled=true;					
					}
					else
					{
						document.getElementById("now").innerHTML="Please choose a category!";
					}
				}
				
				else {
					rules(category);
					var dis1=document.getElementById('showCard');
					dis1.disabled=false;
					var dis2=document.getElementById('nextRound');
					dis2.disabled=true;		
				}
				playerN=playerNum();
				if(playerN =="1"){
					var dis1=document.getElementById('showCard');
					dis1.disabled=true;
					var dis2=document.getElementById('nextRound');
					dis2.disabled=true;
				}
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
			// start the game
			function startTheGame(numPlayer) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/startTheGame?numPlayer="+numPlayer); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// get the number of players
			function playerNum() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playerNum"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
					playerN = responseText;
					if(playerN =="1"){
						var dis1=document.getElementById('showCard');
						dis1.disabled=true;
						var dis2=document.getElementById('nextRound');
						dis2.disabled=true;
						showHandLeft(0);
						showHandLeft(1);
						showHandLeft(2);
						showHandLeft(3);
						showHandLeft(4);
						document.getElementById("now").innerHTML = "The winner is: " + winner + ". Please press Selection Page to play a new game.";
					}
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}			
			
			// show Name
			function showName(i) {
				
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showName?i="+i); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
					if(i<1) {
					document.getElementById("u_cardname").innerHTML = responseText;
					var img = document.getElementById("u_image");
					img.src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" +responseText+ ".jpg";
					}
					else if(i<2){
					document.getElementById("AI1_name").innerHTML = responseText;
					var img = document.getElementById("AI1_image");
					img.src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" +responseText+ ".jpg";
					}
					
					else if(i<3){
					document.getElementById("AI2_name").innerHTML = responseText;
					var img = document.getElementById("AI2_image");
					img.src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" +responseText+ ".jpg";
					}
					else if(i<4){
					document.getElementById("AI3_name").innerHTML = responseText;
					var img = document.getElementById("AI3_image");
					img.src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" +responseText+ ".jpg";
					}
					else if(i<5){
					document.getElementById("AI4_name").innerHTML = responseText;
					var img = document.getElementById("AI4_image");
					img.src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" +responseText+ ".jpg";
					}
						
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			// show size
			function showSize(i) {
				
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showSize?i="+i); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
					if(i<1) {
					document.getElementById("u_cat1").innerHTML = "SIZE: " + responseText;
					}
					else if(i<2){
					document.getElementById("AI1_cat1").innerHTML ="SIZE: " + responseText;
					}
					else if(i<3){
					document.getElementById("AI2_cat1").innerHTML ="SIZE: " + responseText;
					}
					else if(i<4){
					document.getElementById("AI3_cat1").innerHTML ="SIZE: " + responseText;
					}
					else if(i<5){
					document.getElementById("AI4_cat1").innerHTML ="SIZE: " + responseText;
					}
						
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}

			// show speed
			function showSpeed(i) {
				
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showSpeed?i="+i); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
					if(i<1) {
					document.getElementById("u_cat2").innerHTML = "Speed: " + responseText;
					}
					else if(i<2){
					document.getElementById("AI1_cat2").innerHTML ="Speed: " + responseText;
					}
					else if(i<3){
					document.getElementById("AI2_cat2").innerHTML ="Speed: " + responseText;
					}
					else if(i<4){
					document.getElementById("AI3_cat2").innerHTML ="Speed: " + responseText;
					}
					else if(i<5){
					document.getElementById("AI4_cat2").innerHTML ="Speed: " + responseText;
					}
						
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			// show range
			function showRange(i) {
				
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showRange?i="+i); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
					if(i<1) {
					document.getElementById("u_cat3").innerHTML = "Range: " + responseText;
					}
					else if(i<2){
					document.getElementById("AI1_cat3").innerHTML ="Range: " + responseText;
					}
					else if(i<3){
					document.getElementById("AI2_cat3").innerHTML ="Range: " + responseText;
					}
					else if(i<4){
					document.getElementById("AI3_cat3").innerHTML ="Range: " + responseText;
					}
					else if(i<5){
					document.getElementById("AI4_cat3").innerHTML ="Range: " + responseText;
					}
						
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}			

			// show firepower
			function showFirepower(i) {
				
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showFirepower?i="+i); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
					if(i<1) {
					document.getElementById("u_cat4").innerHTML = "Firepower: " + responseText;
					}
					else if(i<2){
					document.getElementById("AI1_cat4").innerHTML ="Firepower: " + responseText;
					}
					else if(i<3){
					document.getElementById("AI2_cat4").innerHTML ="Firepower: " + responseText;
					}
					else if(i<4){
					document.getElementById("AI3_cat4").innerHTML ="Firepower: " + responseText;
					}
					else if(i<5){
					document.getElementById("AI4_cat4").innerHTML ="Firepower: " + responseText;
					}
						
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			// show cargo
			function showCargo(i) {
				
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showCargo?i="+i); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
					if(i<1) {
					document.getElementById("u_cat5").innerHTML = "Cargo: " + responseText;
					}
					else if(i<2){
					document.getElementById("AI1_cat5").innerHTML ="Cargo: " + responseText;
					}
					else if(i<3){
					document.getElementById("AI2_cat5").innerHTML ="Cargo: " + responseText;
					}
					else if(i<4){
					document.getElementById("AI3_cat5").innerHTML ="Cargo: " + responseText;
					}
					else if(i<5){
					document.getElementById("AI4_cat5").innerHTML ="Cargo: " + responseText;
					}
						
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}			

			// show Hand Left
			function showHandLeft(i) {
				
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showHandLeft?i="+i); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
					if(i<1) {
						document.getElementById("u_cards_left").innerHTML = "Left cards: " + responseText;
						if(responseText<1){
							var show=document.getElementById('You');
							show.style.display= "none";
						}
					}
					else if(i<2){
						document.getElementById("AI1_cards_left").innerHTML ="Left cards: " + responseText;
						if(responseText<1){
							var show=document.getElementById('AI1');
							show.style.display="none";
						}
					}
					else if(i<3){
						document.getElementById("AI2_cards_left").innerHTML ="Left cards: " + responseText;
						if(responseText<1){
							var show=document.getElementById('AI2');
							show.style.display= "none";
						}
					}
					else if(i<4){
						document.getElementById("AI3_cards_left").innerHTML ="Left cards: " + responseText;
						if(responseText<1){
							var show=document.getElementById('AI3');
							show.style.display="none";
						}
					}
					else if(i<5){
						document.getElementById("AI4_cards_left").innerHTML ="Left cards: " + responseText;
						if(responseText<1){
							var show=document.getElementById('AI4');
							show.style.display="none";
						}
					}
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			// get Winner
			function getWinner() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getWinner"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
					winner = responseText;
				    document.getElementById("now").innerHTML="It's " + responseText + "'s round. <br>Please press Compare Cards to continue.";
				    if(responseText=="Player") {
				    	var dis=document.getElementById('u_cat1');
						dis.disabled=false;
				    	var dis=document.getElementById('u_cat2');
						dis.disabled=false;
						var dis=document.getElementById('u_cat3');
						dis.disabled=false;	
						var dis=document.getElementById('u_cat4');
						dis.disabled=false;	
						var dis=document.getElementById('u_cat5');
						dis.disabled=false;
						document.getElementById("now").innerHTML="It's " + responseText + "'s round. <br>Please select category.";					    
				    }
				    else{
				    	var dis=document.getElementById('nextRound');
						dis.disabled=false;
				    	var dis=document.getElementById('u_cat1');
						dis.disabled=true;
				    	var dis=document.getElementById('u_cat2');
						dis.disabled=true;
						var dis=document.getElementById('u_cat3');
						dis.disabled=true;	
						var dis=document.getElementById('u_cat4');
						dis.disabled=true;	
						var dis=document.getElementById('u_cat5');
						dis.disabled=true;	
						document.getElementById("now").innerHTML="It's " + responseText + "'s round. <br>Please press Compare Cards to continue.";						
				    }

				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// get k
			function getK() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getK"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
					k = responseText;
					
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}			

			// rules
			function rules(category) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/rules?category="+category); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
					document.getElementById("now").innerHTML= responseText;
					var dis=document.getElementById('u_cat1');
					dis.disabled=true;
				   	var dis=document.getElementById('u_cat2');
					dis.disabled=true;
					var dis=document.getElementById('u_cat3');
					dis.disabled=true;	
					var dis=document.getElementById('u_cat4');
					dis.disabled=true;	
					var dis=document.getElementById('u_cat5');
					dis.disabled=true;	
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

			// AI will choose the largest number it has
			function choiceAI() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/choiceAI"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response;
					category = responseText;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
		</script>
		
		</body>
</html>