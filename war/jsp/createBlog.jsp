<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<html>
<head>
	<title>${ baseTitle }</title>
	<link rel="stylesheet" type="text/css" href="../css/master.css" />
</head>
<body>
<div id="main">
<jsp:include page="/html/header.html"/>
<div id="content">
<h2>Create A New Blog</h2>
<%if (request.getAttribute("error") != null) {%>
	<p class="errorMessage">${ error }</p>
<%}%>
<form method="post" action="${ servletPath }">
<table>
	<tr>
		<td width=35% align="left"><strong>Blog name:</strong></td>
		<td><input type="text" name="blogName" size="58"
			<%if (request.getAttribute("blogName") != null) {%>
				value="${ blogName }"
			<%}%>
			>
		</td>
	</tr>
	<tr>
		<td align="left"><strong>Blog address (URL):</strong></td>
		<td>http://blog-ibm.appspot.com/view?blog=<input type="text" name="blogAddress" size="20"
			<%if (request.getAttribute("blogAddress") != null) {%>
				value="${ blogAddress }"
			<%}%>
			>
		</td>
	</tr>
	<tr>
		<td></td>
		<td align="right"><input type="submit" name="submit"
			value="Submit"></td>
	</tr>
</table>
</form>
</div>
</div>
</body>
</html>