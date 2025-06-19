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

@WebServlet("/mvc/products")
public class ProductController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String uri = req.getRequestURI();
		if ("form-view".equals(action)) {
			 HttpSession session = req.getSession(false);
			 String username = (session != null) ? (String) session.getAttribute("username") : null;

			if (!"admin123".equals(username)) {
		        resp.sendRedirect("products?action=user");
		        return;
		    }

			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/product_form.jsp");
			dispatcher.forward(req, resp);
		} else if ("view".equals(action)) {
			
			 HttpSession session = req.getSession(false);
			 String username = (session != null) ? (String) session.getAttribute("username") : null;

			if (!"admin123".equals(username)) {
		        resp.sendRedirect("products?action=user");
		        return;
		    }
			
			String idParam = req.getParameter("id");
			if (idParam != null) {
				try {
					int id = Integer.parseInt(idParam);
					ProductService service = new ProductService();
					Product product = service.findById(id); //
					req.setAttribute("product", product);

					RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/product_view.jsp");
					dispatcher.forward(req, resp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if ("download".equals(action)) {
			ProductService service = new ProductService();
			List<Product> products = null;
			try {
				products = service.findAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			resp.setContentType("text/csv");
			resp.setHeader("Content-Disposition", "attachment; filename=\"products.csv\"");

			PrintWriter writer = resp.getWriter();
			writer.println("ID,Name,Type,Price");

			for (Product p : products) {
				writer.printf("%d,%s,%s,%d%n", p.getId(), p.getName(), p.getType(), p.getPrice());
			}
			writer.flush();
			return;
		} else if ("admin".equals(action)) {

			ProductService productService;
			try {
				productService = new ProductService();
				List<Product> products = productService.findAll();

				req.setAttribute("products", products);
				HttpSession session = req.getSession();

				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/product_list_admin.jsp");
				dispatcher.forward(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("user".equals(action)) {

			ProductService productService;
			try {
				productService = new ProductService();
				List<Product> products = productService.findAll();

				req.setAttribute("products", products);
				HttpSession session = req.getSession();
				session.setAttribute("message", "Selamat Datang Admin");

				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/product_list.jsp");
				dispatcher.forward(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if ("cart".equals(action))

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

			RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
			dispatcher.forward(req, resp);

		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		if ("update".equals(action)) {
			try {
				int id = Integer.parseInt(req.getParameter("id"));
				String name = req.getParameter("name");
				String type = req.getParameter("type");
				int price = Integer.parseInt(req.getParameter("price"));

				Product product = new Product(id, name, type, price);
				ProductService repo = new ProductService();
				repo.update(product);

				resp.sendRedirect("products?action=admin");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ("delete".equals(action)) {
			String idParam = req.getParameter("id");
			if (idParam != null) {
				try {
					int id = Integer.parseInt(idParam);
					ProductService service = new ProductService();
					service.delete(id);
					resp.setStatus(HttpServletResponse.SC_OK);
				} catch (Exception e) {
					resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					e.printStackTrace();
				}
			}

		} else if ("login".equals(action)) {
			String userName = req.getParameter("username");
			String userPassword = req.getParameter("password");

			HttpSession session = req.getSession();

			try {
				ProductService productService = new ProductService();
				List<Product> products = productService.findAll();

				if ("admin123".equals(userName) && "admin123".equals(userPassword)) {
					session.setAttribute("username", userName);
					req.setAttribute("products", products);
					resp.sendRedirect("products?action=admin");
				} else if ("alia".equals(userName) && "alia".equals(userPassword)) {
					session.setAttribute("username", userName);
					req.setAttribute("products", products);
					resp.sendRedirect("products?action=user");
				} else {
					session.setAttribute("error", "Username atau password salah.");
					resp.sendRedirect("products");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				session.setAttribute("error", "Terjadi kesalahan pada server.");
				resp.sendRedirect("products");
			}
		}

		else if ("addToCart".equals(action)) {
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
				resp.sendRedirect("products?action=user");

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
					resp.sendRedirect("products?action=user");
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				resp.getWriter().write("Cart kosong.");
			}

		} else {
			// Default add product
			try {
				String name = req.getParameter("name");
				String type = req.getParameter("type");
				int price = Integer.parseInt(req.getParameter("price"));

				Product product = new Product();
				product.setName(name);
				product.setType(type);
				product.setPrice(price);

				ProductService repo = new ProductService();
				repo.add(product);
				resp.sendRedirect("products?action=admin");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
