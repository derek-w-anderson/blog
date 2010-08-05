package com.appspot.blog.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
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
 * Servlet that manages requests for blogs. 
 * 
 * @author Derek W. Anderson
 */
public class ViewBlog extends HttpServlet
{
	private static final long serialVersionUID = -4299499155807202340L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		PersistenceManager pm = PMF.getInstance().getPersistenceManager();
		
		try {
			// Get blog from database
			String address = req.getParameter("blog");
			Blog blog = (Blog) DB.get(Blog.class, "address", address, pm);

			if (blog == null) {
				// ERROR - Blog does not exist
				Dispatcher.forwardToErrorPage(req, resp, this);
				return;
			}
			// Get post(s) from database
			List posts = new ArrayList();
			String postID = req.getParameter("post");		
			
			if (postID != null) {
				// Get individual post for blog
				Post post = (Post) DB.get(Post.class, "id", postID, pm);
				if (post == null) {
					// ERROR - Post does not exist
					Dispatcher.forwardToErrorPage(req, resp, this);
					return;
				}
				posts.add(post);

			} else {
				// Get all posts for blog
				Query query = pm.newQuery(Post.class, "blog.address == \"" + address + "\"");
				query.setOrdering("dateCreated DESC");
				posts = (List) query.execute();
			}
			// Add blog and posts to request
			req.setAttribute("blog", blog);
			req.setAttribute("posts", posts);
			Dispatcher.forwardToJSP("/jsp/viewBlog.jsp", req, resp, this);

		} catch (Exception e) {
			// ERROR - Problem accessing database
			req.setAttribute("error", e.toString().replace("\n", "<br>"));
			Dispatcher.forwardToJSP("/jsp/error.jsp", req, resp, this);
			
		} finally {
			pm.close();
		}
	}
}
