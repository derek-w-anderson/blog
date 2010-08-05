package com.appspot.blog.utilities;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Utility class that manages forwarding requests from servlets to JSPs.
 * 
 * @author Derek W. Anderson
 */
public final class Dispatcher
{
	public static void forwardToJSP(
			String targetJSP, 
			HttpServletRequest request,
			HttpServletResponse response, 
			HttpServlet currentServlet) throws ServletException, IOException 
	{
		ServletContext context = currentServlet.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(targetJSP);
		dispatcher.forward(request, response);
	}

	public static void forwardToServlet(
			String targetServlet, 
			HttpServletRequest request,
			HttpServletResponse response, 
			HttpServlet currentServlet) throws ServletException, IOException 
	{	
		ServletContext context = currentServlet.getServletContext();
		RequestDispatcher dispatcher = context.getNamedDispatcher(targetServlet);
		dispatcher.forward(request, response);
	}
	
	public static void forwardToErrorPage(
			HttpServletRequest request,
			HttpServletResponse response, 
			HttpServlet currentServlet) throws ServletException, IOException 
	{
		ServletContext context = currentServlet.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/jsp/error.jsp");
		request.setAttribute("error", "The page you are looking for does not exist.");
		dispatcher.forward(request, response);
	}
}
