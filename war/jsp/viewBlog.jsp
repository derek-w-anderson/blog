<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.List"%>
<%@ page import="com.appspot.blog.models.Blog, com.appspot.blog.models.Post"%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date " %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<html>
<head>
	<title>${ applicationContext.baseTitle }</title>
	<link rel="stylesheet" type="text/css" href="../css/master.css" />
</head>
<body>
<div id="main">
<jsp:include page="/html/header.html"/>
<div id="content">
	<div id="leftColumn" class="column">
	<h2>${ blog.name }</h2>
	<%if (((List) request.getAttribute("posts")).isEmpty()) {%>
	<p>
	There is nothing here yet. 
		<%
		UserService userService = UserServiceFactory.getUserService();
    	User user = userService.getCurrentUser();
    	if (user != null && user.equals(((Blog) request.getAttribute("blog")).getAuthor())) {
    	%>
    	Care to <a href="/view?blog=${ blog.address }&action=create-post">make a post</a>?
		<%
		}
		%>
	</p>
	<%	
	} else {
		for (Post post : ((List<Post>) request.getAttribute("posts"))) {
	%>
	<div class="post">
		<h3 style="text-decoration:underline;"><%= post.getTitle() %></h3>
		<p><%= post.getContent().getValue().replace("\n", "<br>") %></p>
		<div class="signature">Posted by <strong><%= post.getAuthor().getNickname() %></strong> on 
		<% 
			SimpleDateFormat format1 = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
			out.println(format1.format(post.getDateCreated()));			
		 %>
		</div>
	</div>
	<% 	}
	} %>
	</div>
	<div id="rightColumn" class="column">
		<h3>About This Blog</h3>
		<table>
		<tr>
		<td><strong>Author:</strong> </td> 
		<td><%= ((Blog) request.getAttribute("blog")).getAuthor().getNickname() %></td>
		</tr>
		<tr>
		<td><strong>Created:</strong> </td>
		<td><%
			SimpleDateFormat format2 = new SimpleDateFormat("MMMM d, yyyy");	
			Date date = ((Blog) request.getAttribute("blog")).getDateCreated();
			out.println(format2.format(date));	
		%></td>
		</tr>
		</table>
		<%
		UserService userService = UserServiceFactory.getUserService();
    	User user = userService.getCurrentUser();
		if (!((List) request.getAttribute("posts")).isEmpty() && 
				user.equals(((Blog) request.getAttribute("blog")).getAuthor())) {%>
		<p>Want to
			<a href="/view?blog=<% out.print(((Blog) request.getAttribute("blog")).getAddress()); %>&action=create-post">make another post</a>?
		</p>
		<%}%>
	</div>
</div>
</div>
</body>
</html>