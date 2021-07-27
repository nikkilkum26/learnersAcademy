package com.learnersacademy.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.learnersacademy.dao.Courses_db;
import com.learnersacademy.model.Classes;
import com.learnersacademy.model.Courses;

/**
 * Servlet implementation class ListClasses
 */
@WebServlet("/list-courses")
public class ListCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Courses_db coursesdb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCourses() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() {
    	coursesdb = new Courses_db();
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
				listUser(request, response);

		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	
		
		
	}
	  private void listUser(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
		  Boolean login =false;
	    	Cookie c[]=request.getCookies(); 
	    	for(int i=0;i<c.length;i++){  
	    		 if(c[i].getName().equals("login" ) && c[i].getValue().equals("true")) {
	    			 System.out.println("Peace Bro");
	    			 login=true;
	    		 }
	    		}
	    	if(login) {
	    		List<Courses> listUser = coursesdb.selectAllSubjects();
				request.setAttribute("listCourses", listUser);
				RequestDispatcher dispatcher = request.getRequestDispatcher("list-courses.jsp");
				dispatcher.forward(request, response);
	    	}
	    	else {
	    		RequestDispatcher dispatcher = request.getRequestDispatcher("adminLogin.jsp");
	    		dispatcher.forward(request, response);
	    	}
			
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
