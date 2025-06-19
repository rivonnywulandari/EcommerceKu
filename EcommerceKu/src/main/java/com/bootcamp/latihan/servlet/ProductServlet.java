package com.bootcamp.latihan.servlet;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

import com.bootcamp.latihan.entities.Product;
import com.bootcamp.latihan.repository.ProductRepository;
import com.bootcamp.latihan.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/oldproducts")
public class ProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		String id = req.getParameter("id");
		if(id != null) {
			showSingleProduct(req, resp);
		} else {
			showAllProduct(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String type = req.getParameter("type");
		int price = Integer.parseInt(req.getParameter("price"));

		Product product = new Product();
		product.setName(name);
		product.setType(type);
		product.setPrice(price);

		ProductService productRepo;
		try {
			productRepo = new ProductService();
			productRepo.add(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Writer out = resp.getWriter();
//		out.write("sukses");
		
		resp.sendRedirect("oldproducts");
	}

	
	private void showSingleProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer out = resp.getWriter();
		ProductService productRepo;
		try {
			productRepo = new ProductService();
//			String isbn = req.getParameter("isbn");
			int id = Integer.parseInt(req.getParameter("id"));
			Product product = productRepo.findById(id);			
			out.write("<table border=3>");
				out.write("<tr>");
				out.write("<td>" + product.getId() + "</td>");
				out.write("<td>" + product.getName() + "</td>");
				out.write("<td>" + product.getType() + "</td>");
				out.write("<td>" + product.getPrice() + "</td>");
				out.write("</tr>");		
			out.write("</table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void showAllProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer out = resp.getWriter();
		ProductService productRepo;
		try {
			productRepo = new ProductService();
			out.write("<table border=3>");
			for(Product product : productRepo.findAll()) {				
				out.write("<tr>");
				out.write("<td>" + product.getId() + "</td>");
				out.write("<td>" + product.getName() + "</td>");
				out.write("<td>" + product.getType() + "</td>");
				out.write("<td>" + product.getPrice() + "</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			out.write("<br><a href='product_form.html'>Add New Product</a>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		

	}
	
}
