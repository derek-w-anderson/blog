package com.appspot.blog.controllers;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.appspot.blog.exceptions.ModelException;
import com.appspot.blog.models.Blog;
import com.appspot.blog.models.Post;
import com.appspot.blog.utilities.DB;
import com.appspot.blog.utilities.Dispatcher;
import com.appspot.blog.utilities.PMF;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Servlet that manages blog post creation.
 * 
 * @author Derek W. Anderson
 */
public class CreatePost extends HttpServlet
{
	private static final long serialVersionUID = 2431736243650719464L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		User user = UserServiceFactory.getUserService().getCurrentUser();
		String blogAddress = req.getParameter("blog");
		
		if (isBlogAuthor(blogAddress, user)) 
			Dispatcher.forwardToJSP("/jsp/createPost.jsp", req, resp, this);
		else 
			// ERROR - User is not blog's author (send to home page)
			Dispatcher.forwardToJSP("/jsp/welcome.jsp", req, resp, this);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		PersistenceManager pm = PMF.getInstance().getPersistenceManager();
		User user = UserServiceFactory.getUserService().getCurrentUser();
		
		String blogAddress = req.getParameter("blog");
		if (!isBlogAuthor(blogAddress, user)) {
			// ERROR - User is not blog's author (send to home page)
			Dispatcher.forwardToJSP("/jsp/welcome.jsp", req, resp, this);
			return;
		}
		// Get form parameters
		String title = req.getParameter("postTitle");
		String content = req.getParameter("postContent");
		try {
			// Get associated blog
			Blog blog = (Blog) DB.get(Blog.class, "address", blogAddress, pm);
			
			// Create post and store it in database
			Post newPost = new Post(title, new Text(content), user, blog);
			pm.makePersistent(newPost);

			// Generate "success" message to show user
			String message = "Your blog has been updated. ";
			message += "Click <a href=\"/view?";
			message += "blog=" + blog.getAddress() + "\">here</a> to return.";
			req.setAttribute("message", message);
			Dispatcher.forwardToJSP("/jsp/success.jsp", req, resp, this);

		} catch (ModelException e) {
			// ERROR - Post could not be created (return user to form)
			req.setAttribute("postTitle", title);
			req.setAttribute("postContent", content);
			req.setAttribute("error", e);
			Dispatcher.forwardToJSP("/jsp/createPost.jsp", req, resp, this);

		} finally {
			pm.close();
		}
	}
	
	private static boolean isBlogAuthor(String blogAddress, User user) 
	{
		PersistenceManager pm = PMF.getInstance().getPersistenceManager();
		try {
			// Get blog from database and compare author to user
			Blog blog = (Blog) DB.get(Blog.class, "address", blogAddress, pm);
			return blog.getAuthor().equals(user);
			
		} catch (Exception e) {
			// ERROR - Problem accessing database
			return false;
			
		} finally {	
			pm.close();
		}
	}
}
