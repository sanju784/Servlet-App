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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
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
	 * Called when /register url with GET Request method
	 * 
	 *   Displays a form to allow new users to register
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//set refresh autoload time as 5 second
//		response.setIntHeader("refresh", 5);
		
		//set response content type
		response.setContentType("text/html");
		
		
		String title = "Login Page";
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>" +
				    title + "</title></head>" +
				    "<body bgcolor = \"#f0f0f0\">" + header +
				    "<h2 align=\"center\">" + title + "</h2>" +
				    
				    "<form action=\"register\" method=\"post\">" +
				    "<table><tr><td>Enter name:</td>" +
				    "<td><input type=\"text\" name=\"username\"></td></tr>" +
				    "<tr><td>Enter Password:</td>" +
				    "<td><input type=\"password\" name=\"password\"></td></tr>" +
				    "<tr><td>Confirm Password:</td>" +
				    "<td><input type=\"password\" name=\"password2\"></td></tr>" +
				    "<tr><td></td><td><input type=\"submit\"></td></tr>" +
				    "</form></body></html>"
				);
	}

	/**
	 * Called when /register with POST Request Method
	 * 
	 * takes the input Parameter and decides the result
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//set refresh autoload time as 5 second
//		response.setIntHeader("refresh", 5);
				
		//set response content type
		response.setContentType("text/html");

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		PrintWriter writer = response.getWriter();
		
		LoginService service = new LoginService();
		if (service.validate(userName, password, password2)) {
			service.addUser(userName, password);
			
			writer.print("<html><head><title>Success</title></head>" +
					 "<body>" + header + "<h2>You have registered successfully</h2>" +
				     "<h3>Hello " + userName + ", you are welcome here</h3></body></html>"
				    );
		} else {
			writer.print("<html><head><title>Failure</title></head>" +
					 "<body>" + header + "<h2>Registration not done</h2>" +
				     "<h3>Hello " + userName + ", some problem occured during registration. Try again to register</h3>" +
					 "<p><a href=\"register\">Click here to Register</a></p>" +
					 "</body></html>"
				    );
		}
	}

}
