package com.appspot.blog.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.appspot.blog.utilities.Dispatcher;

/**
 * Manages the distribution of requests to servlets. 
 * 
 * @author Derek W. Anderson
 */
public class ServletDispatcher extends HttpServlet
{
	private static final long serialVersionUID = 3418352647087385213L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		processRequest(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{		
		// Get URL parameters
		String blog = req.getParameter("blog");
		String action = req.getParameter("action");
		
		if (blog == null) {
			// ERROR - No blog address specified
			Dispatcher.forwardToErrorPage(req, resp, this);
			return;
		}
		if (action == null) 
			// Forward user to blog page
			Dispatcher.forwardToServlet("ViewBlog", req, resp, this);
		else
			// Forward user to post creation page
			Dispatcher.forwardToServlet("CreatePost", req, resp, this);	
	}
}
