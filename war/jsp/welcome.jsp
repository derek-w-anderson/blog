<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List, java.text.SimpleDateFormat" %>
<%@ page import="com.appspot.blog.models.Blog, com.appspot.blog.models.Post"%>
<html>
<head>
	<title>${ baseTitle }</title>
	<link rel="stylesheet" type="text/css" href="../css/master.css" />
</head>
<body>
<div id="main">
	<jsp:include page="/html/header.html"/>
	<div id="content">
	<div id="leftColumn" class="column">		
		<h2>Most Recent Posts</h2>
		<% for (Post post : ((List<Post>) request.getAttribute("posts"))) { %>
		<div class="post">
		<h3><%= post.getAuthor().getNickname() %> posted <u><%= post.getTitle() %></u></h3>
		<p><%= post.getContent().getValue().replace("\n", "<br>") %></p>
		<div class="signature">Posted to <a href="/view?blog=<%= post.getBlog().getAddress() %>"><%= post.getBlog().getName()%></a> on 
		<% 
			SimpleDateFormat format1 = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
			out.println(format1.format(post.getDateCreated()));			
		 %>
		</div>
		</div>
		<% } %>
	</div>
	<div id="rightColumn" class="column">
		<div id="about" style="padding-bottom:1px;">
		<h2>Interested?</h2>
		<p>Create <a href="/create-blog">your own blog</a> today!</p>
		<p>Or check out the <a href="http://github.com/ander1dw/blog-ibm">source</a> at Github!
		</div>
		<h3>Newest Blogs</h3>
		<% 
		for (Blog b : (List<Blog>) request.getAttribute("blogs")) {
			out.println("<div class=\"blogSummary\"");
			out.print("<a href=\"/view?blog=" + b.getAddress());
			out.println("\">" + b.getName() + "</a><br>");
			out.println("by " + b.getAuthor().getNickname() + "<br>");
			out.println("</div>");
		} 
		%>
	</div>
	</div>
</div>
</body>
</html>