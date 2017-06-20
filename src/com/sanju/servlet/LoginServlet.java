package com.sanju.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanju.servlet.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String time;
	private String header;
	
	/**
	 * The initialization method
	 */
	public void init() throws ServletException{
		Calendar c = new GregorianCalendar();
		String am_pm;
		
		int h = c.get(Calendar.HOUR);
		int m = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		
		if(c.get(Calendar.AM_PM) == 0)
			am_pm = "AM";
		else
			am_pm = "PM";
		
		time = h + ":" + m + ":" + s + " " + am_pm;
		header = "<header><h1>" + time + "</h1></header>";
	}

	/**
	 * On call of /login by GET Request Method 
	 * Login form will be displayed with User name and password fields
	 * 
	 * It has a refresh header to refresh page in every 5 seconds to display updated time
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//set refresh autoload time as 5 second
		//response.setIntHeader("refresh", 5);
		
		//set response content type
		response.setContentType("text/html");
		
		
		String title = "Login Page";
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>" +
				    title + "</title></head>" +
				    "<body bgcolor = \"#f0f0f0\">" + header +
				    "<h2 align=\"center\">" + title + "</h2>" +
				    "<form action=\"login\" method=\"post\">" +
				    "<table><tr><td>Enter name:</td>" +
				    "<td><input type=\"text\" name=\"username\"></td></tr>" +
				    "<tr><td>Enter Password:</td>" +
				    "<td><input type=\"password\" name=\"password\"></td></tr>" +
				    "<tr><td></td><td><input type=\"submit\"></td></tr>" +
				    "</form></body></html>"
				);
		
	}

	/**
	 * On call of /login by POST Request Method
	 * Check for the request parameter and then generates the corresponding result
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//set refresh autoload time as 5 second
		//response.setIntHeader("refresh", 5);
				
		//set response content type
		response.setContentType("text/html");

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter writer = response.getWriter();
		
		LoginService service = new LoginService();
		if (service.authenticate(userName, password)) {
			writer.print("<html><head><title>Success</title></head>" +
						 "<body>" + header + "<h2>You Loginned successfully</h2>" +
					     "<h3>Hello " + userName + ", you are welcome here</h3></body></html>"
					    );
		} else {
			writer.print("<html><head><title>Failure</title></head>" +
					 "<body>" + header + "<h2>No Login Possible</h2>" +
				     "<h3>" + userName + ", you are not a registered member</h3>" +
					 "<p><a href=\"register\">Click here to Register</a></p>" +
					 "</body></html>"
				    );
		}
	}

}
