package com.odilon;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

@WebServlet("/signin")	
public class LoginServelet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if ("admin@gmail.com".equals(email) && "password".equals(password)) {
        	String userName = "Admin";
            HttpSession session = request.getSession();
            session.setAttribute("userName", userName);
            response.sendRedirect("welcome.html?userName=" + URLEncoder.encode(userName, "UTF-8"));
           // response.sendRedirect("welcome.html");
        } else {
          
            RequestDispatcher dispatcher = request.getRequestDispatcher("signup.html");
            dispatcher.forward(request, response);
        }
    }
	
}


