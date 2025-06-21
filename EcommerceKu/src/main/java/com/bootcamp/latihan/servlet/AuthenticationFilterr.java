package com.bootcamp.latihan.servlet;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter("/mvc/products")
public class AuthenticationFilterr implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String action = req.getParameter("action");

        // Allow access to login processing and login page
        if (action == null || "login".equals(action) || req.getRequestURI().endsWith("login.jsp")) {
            chain.doFilter(request, response);
            return;
        }

        // Allow only if user already logged in
        if (session != null && session.getAttribute("username") != null) {
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
