<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-----------------------------------------------------------
** file name    :   jsp
** authors      :   谢坚柏
** created      :   2011-2012
** copyright    :   (c)2011 Vitular Inc. All Rights Reserved.
-------------------------------------------------------------->
<html>
<head>
<base href="<%=basePath%>"></base>
<title>Welcome to Spring3 world</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="js/jquery-1.7.js"></script>
<script type="text/javascript">
	$(function() {
		$("#button").click(
				function() {
					$.getJSON("gson.do", function(data) {
						$("#gson").empty();
						$.each(data, function(key, value) {
							$("#gson").append(
									"<tr><td>Id</td><td>" + key
											+ "</td><td>Name</td><td>" + value
											+ "</td></tr>");
						});

						$("#gson td").each(function() {
							$(this).attr("class", "mes");
						});
					});
				});
	});
</script>
<style type="text/css">
.pcl {
	background-color: red;
	color: Red;
}

.mes {
	background-color: Gray;
	font-size: 30px;
	font-weight: 30px;
	color: Red;
}
</style>
</head>
<body>
	<h1 align="center">
		Welcome to Spring Three study world!
		<h1>
			<hr>
			<a href="hello.do">Say Hello</a>
			<hr>
			<input type="button" id="button" value="按钮">
			<table id="gson">
			</table>
			<p>
			<a href="validation.do?userName=winzip&email=adfadfadf">表单验证</a>
			<a href="j_spring_security_logout">Logout</a>
</body>
</html>

