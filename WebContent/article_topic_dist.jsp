<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- saved from url=(0041)http://v3.bootcss.com/examples/jumbotron/ -->
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="http://v3.bootcss.com/favicon.ico">
<title>Demo</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/jumbotron.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript" src="jquery-3.1.1.min.js"></script>
</head>

<body id="body">
	<!-- Fixed navbar -->
	
    
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">You can search</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="./topic.jsp">Topic</a></li>
					<li><a href="./word.jsp">Word</a></li>
					<li class="active"><a href="./article.jsp">Article</a></li>
					<li>hidden element</li>
					<a class="navbar-brand" href="#"> More detail in</a>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Charts <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Nav header</li>
							<li><a href="#">Separated link</a></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>
				${index } <br> Article - Topic Distribution Charts!
			</h1>
			<br>
			<ul id="myTab" class="nav nav-tabs">
				<li class="active"><a href="#columnChart" data-toggle="tab">
						Column Chart </a></li>
				<li><a href="#pieChart" data-toggle="tab">Pie chart</a></li>
			</ul>
			<div id="myTabContent" class="tab-content" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto;">
				<div class="tab-pane fade in active" id="columnChart">
					<p>This is column chart</p>
					<script type="text/javascript" src="js/columnchart.js"></script>
				</div>
				<div class="tab-pane fade" id="pieChart">
				<!-- <div id="pieChart" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto;"> -->
					<!-- <p>This is pie chart</p> -->
					<script type="text/javascript" src="js/highchart.js"></script>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	
</body>

</html>
