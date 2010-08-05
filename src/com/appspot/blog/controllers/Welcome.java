package com.appspot.blog.controllers;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.appspot.blog.models.Blog;
import com.appspot.blog.models.Post;
import com.appspot.blog.utilities.DB;
import com.appspot.blog.utilities.Dispatcher;
import com.appspot.blog.utilities.PMF;

/**
 * Servlet that manages the home page. 
 * 
 * @author Derek W. Anderson
 */
public class Welcome extends HttpServlet
{
	private static final long serialVersionUID = -3799730147238330108L;
	
	private static final int MAX_BLOGS = 10;
	private static final int MAX_POSTS = 5;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		PersistenceManager pm = PMF.getInstance().getPersistenceManager();
		
		try {
			List blogs = DB.getList(Blog.class, "dateCreated DESC", 0, MAX_BLOGS, pm);		
			List posts = DB.getList(Post.class, "dateCreated DESC", 0, MAX_POSTS, pm);
			
			// Add blogs and posts to request
			req.setAttribute("blogs", blogs);
			req.setAttribute("posts", posts);
			Dispatcher.forwardToJSP("/jsp/welcome.jsp", req, resp, this);
		
		} finally {
			pm.close();
		}
	}
}
