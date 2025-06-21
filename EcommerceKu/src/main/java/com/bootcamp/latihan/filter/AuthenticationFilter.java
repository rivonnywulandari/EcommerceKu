package com.bootcamp.latihan.filter;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter("/mvc/*")
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		String action = req.getParameter("action");

		if (action == null || "login".equals(action) || req.getRequestURI().endsWith("login.jsp")) {
			chain.doFilter(request, response);
			return;
		}

		if (session != null && session.getAttribute("username") != null) {
			if ("user".equals(action) || "carts".equals(action) || "placeOrder".equals(action)) {
				chain.doFilter(request, response);
				return;
			}
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
