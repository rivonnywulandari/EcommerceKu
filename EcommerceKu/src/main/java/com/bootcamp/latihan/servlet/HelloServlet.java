package com.bootcamp.latihan.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bootcamp.latihan.entities.Product;
import com.google.gson.Gson;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(urlPatterns = {"/hello.do", "/hello.action", "/hello"})
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer out = response.getWriter();
		HttpSession session = request.getSession();
		
		String message = (String) session.getAttribute("message");
		if(message == null) message = "";
		
		session.setAttribute("message", null);
		
		String jsonData = "{\"name\":\"Budiman\", \"address\":\"jalan tali 7\", \"age\":\"27\" }";
		response.setContentType("application/json");
		
		Product product = new Product(1, "Product AAA", "Aksesoris", 25000);
//		product.setId("1");
		String productJson = new Gson().toJson(product);
		out.write(productJson);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer out = response.getWriter();
		out.write("Hello World POST");
	}

}
