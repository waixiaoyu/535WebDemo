<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Demo</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {

		var index = ${topicIndex}
		var pr = ${topicPr}

		for (var i = 0; i < index.length; i++) {
			var btn = $("<input type='submit' id='Btn"+i+"' value='"+index[i]+":"+pr[i]+"' onclick='change("+index[i]+")'>");
			$("#topicdetailform").append(btn);

		}
	});
	
	function change(value){  
		$("#topicindex").val(value)
	}  
</script>
</head>
<body>
	${title }
	<p>${content }
	<p>
	<form id="topicdetailform" action="topicDetail">
		<input type="text" id="topicindex" name="topicindex" value="" hidden="true">
	</form>
</body>
</html>