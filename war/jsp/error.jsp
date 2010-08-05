<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" %> 
<%@ page isELIgnored="false" %>
<html>
<head>
	<title>${ baseTitle }</title>
	<link rel="stylesheet" type="text/css" href="../css/master.css" />
</head>
<body>
<div id="main">
<jsp:include page="/html/header.html" />
<div id="content">
<h2>Uh oh...</h2>
<p>${ error }</p>
</div>
</div>
</body>
</html>