package com.bootcamp.latihan.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.bootcamp.latihan.entities.User;
import com.bootcamp.latihan.repository.UserRepository;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/message.do")
public class MessageServlet extends HttpServlet {
	
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("===========Object Message Servlet CREATED===========");
	}

	@Override
	public void destroy() {
		System.out.println("===========Object Message Servlet WILL BE DESTROYED===========");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserRepository repo = new UserRepository();
		List<User> users = repo.getAll();
		Writer out = resp.getWriter();
		String type = req.getParameter("type");

		if("csv".equals(type)) {
			resp.setContentType("application/csv");
			resp.setHeader("Content-disposition", "attachment;filename=test.csv");
			for(User user : users) {
				out.write( user.getUserName() + ",");
				out.write( user.getRole() + ",");
				out.write( user.getAddress() + ",");
				out.write("\n");
			}		
		} else {
			out.write("<table border=3>");
			for(User user : users) {
				out.write("<tr>");
				out.write("<td>" + user.getUserName() + "</td>");
				out.write("<td>" + user.getRole() + "</td>");
				out.write("<td>" + user.getAddress() + "</td>");
				out.write("</tr>");
			}		
			out.write("</table>");
		}
		
		
	}

	
}
