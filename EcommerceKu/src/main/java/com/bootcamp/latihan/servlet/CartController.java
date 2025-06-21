package com.bootcamp.latihan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bootcamp.latihan.entities.Order;
import com.bootcamp.latihan.entities.OrderItem;
import com.bootcamp.latihan.entities.Product;
import com.bootcamp.latihan.repository.ProductRepository;
import com.bootcamp.latihan.service.ProductService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/mvc/carts")
public class CartController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String uri = req.getRequestURI();
		if ("mycart".equals(action))

		{
			HttpSession session = req.getSession();
			List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

			int total = 0;
			if (cart != null) {
				for (OrderItem item : cart) {
					total += item.getPrice() * item.getQuantity();
				}
			} else {
				req.setAttribute("message", "Keranjang Anda kosong.");

			}

			req.setAttribute("totalPrice", total);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
			dispatcher.forward(req, resp);
		}

		else if ("logout".equals(action)) {
			HttpSession session = req.getSession(false);
			if (session != null) {
				session.invalidate();
			}

			RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
			dispatcher.forward(req, resp);
		}

		else {

			ProductService productService;
			try {
				productService = new ProductService();
				List<Product> products = productService.findAll();

				req.setAttribute("products", products);
				HttpSession session = req.getSession();

				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/product_list.jsp");
				dispatcher.forward(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		 if ("addToCart".equals(action)) {
			try {
				int id = Integer.parseInt(req.getParameter("id"));
				String name = req.getParameter("name");
				String type = req.getParameter("type");
				int price = Integer.parseInt(req.getParameter("price"));
				int quantity = Integer.parseInt(req.getParameter("quantity"));

				OrderItem item = new OrderItem(id, name, type, price, quantity);
				HttpSession session = req.getSession();
				List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

				if (cart == null) {
					cart = new ArrayList<>();
				}

				boolean itemExists = false;
				for (OrderItem existing : cart) {
					if (existing.getId() == id) {
						existing.setQuantity(existing.getQuantity() + quantity);
						itemExists = true;
						break;
					}
				}

				if (!itemExists) {
					cart.add(item);
				}

				session.setAttribute("cart", cart);
				resp.sendRedirect("carts");

			} catch (NumberFormatException e) {
				resp.getWriter().write("Input tidak valid.");
			}

		} 
		else if ("placeOrder".equals(action)) {
			HttpSession session = req.getSession();
			List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

			if (cart != null && !cart.isEmpty()) {
				Order order = new Order();
				for (OrderItem item : cart) {
					order.addItem(item);
				}

				try {
					ProductService service = new ProductService();
					service.order(order);
					session.removeAttribute("cart");
					resp.sendRedirect("carts");
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				resp.getWriter().write("Cart kosong.");
			}

		} 
	}

}
