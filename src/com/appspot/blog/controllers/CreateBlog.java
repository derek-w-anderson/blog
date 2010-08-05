package com.appspot.blog.controllers;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.appspot.blog.exceptions.ModelException;
import com.appspot.blog.models.Blog;
import com.appspot.blog.utilities.Dispatcher;
import com.appspot.blog.utilities.PMF;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Servlet that manages blog creation.
 * 
 * @author Derek W. Anderson
 */
public class CreateBlog extends HttpServlet
{
	private static final long serialVersionUID = -2026471020800554729L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		Dispatcher.forwardToJSP("/jsp/createBlog.jsp", req, resp, this);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		PersistenceManager pm = PMF.getInstance().getPersistenceManager();
		User user = UserServiceFactory.getUserService().getCurrentUser();

		// Get form parameters
		String name = req.getParameter("blogName");
		String address = req.getParameter("blogAddress");
		try {			
			// Create blog and store it in database
			Blog newBlog = new Blog(name, address, user);
			pm.makePersistent(newBlog);
			
			// Generate "success" message to show user
			String message = "Your blog has been created. ";
			message += "You may view it <a href=\"/view?blog=" + address + "\">here</a>.";
			req.setAttribute("message", message);
			Dispatcher.forwardToJSP("/jsp/success.jsp", req, resp, this);

		} catch (ModelException e) {
			// ERROR - Blog could not be created (return to form)
			req.setAttribute("blogName", name);
			req.setAttribute("blogAddress", address);
			req.setAttribute("error", e);
			Dispatcher.forwardToJSP("/jsp/createBlog.jsp", req, resp, this);

		} finally {
			pm.close();
		}
	}
}
