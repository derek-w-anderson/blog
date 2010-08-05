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
<h2>Create A New Post</h2>
<%if (request.getAttribute("error") != null) {%>
	<p class="errorMessage">${ error }</p>
<%}%>
<form method="post" action="${ servletPath }">
<table>
	<tr>
		<td width=30% align="left"><strong>Title:</strong></td>
		<td><input type="text" name="postTitle" size="50"
			<%if (request.getAttribute("postTitle") != null) {%>
				value="${ postTitle }"
			<%}%>
			>
		</td>
	</tr>
	<tr>
		<td align="left" valign="top"><strong>Content:</strong></td>
		<td>
			<textarea name="postContent" rows="20" cols="50"><%if (request.getAttribute("postContent") != null) {%>${ postContent }<%}%></textarea>
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