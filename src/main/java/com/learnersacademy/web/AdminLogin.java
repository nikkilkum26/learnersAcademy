package com.learnersacademy.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.learnersacademy.dao.Admin_db;
import com.learnersacademy.model.Admin;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/verifylogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Admin_db admin = new Admin_db();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Admin login = new Admin();
		login.setUserName(username);
		login.setPassword(password);
		
		if(admin.validate(login)) {
			Cookie c = new Cookie("login","true");
			c.setMaxAge(1800);
			response.addCookie(c);
			response.sendRedirect("/LearnersAcademy/");
		}
		else {
			response.sendRedirect("adminLogin.jsp");
		}
	}

}
