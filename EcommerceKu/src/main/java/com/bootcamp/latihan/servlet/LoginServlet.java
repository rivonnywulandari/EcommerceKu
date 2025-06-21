package com.bootcamp.latihan.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login/*")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("password");
		
		System.out.println(request.getPathInfo());
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());
		System.out.println(request.getRemoteAddr());
		
		Writer out = response.getWriter();
		if("admin123".equals(userPassword) && "admin123".equals(userName)) {
			out.write("<h1> Welcome " + userName.toUpperCase() +  " </h1>");
		} else {
			out.write("<h1> User atau Password salah ... " + userName.toUpperCase() +  " </h1>");
		}
		
		
	}

}
