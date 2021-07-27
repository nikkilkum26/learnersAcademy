package com.learnersacademy.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learnersacademy.dao.Admin_db;
import com.learnersacademy.dao.Classes_db;
import com.learnersacademy.dao.Courses_db;
import com.learnersacademy.dao.Students_db;
import com.learnersacademy.model.Admin;
import com.learnersacademy.model.Classes;
import com.learnersacademy.model.Courses;
import com.learnersacademy.model.Students;

import com.learnersacademy.dao.Teachers_db;
import com.learnersacademy.model.Teachers;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Students_db studentDb;
	private Teachers_db teacherDb;
	private Classes_db classesDb;
	private Courses_db coursesdb;
	private Admin_db adminDb;


	public void init() {
		studentDb = new Students_db();
		teacherDb = new Teachers_db();
		classesDb = new Classes_db();
		coursesdb = new Courses_db();
		adminDb = new Admin_db();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
				
			case "/newteacher":
				showNewFormTeacher(request, response);
				break;
			
				
			case "/insertteacher":
				insertUserTeacher(request, response);
				break;	
			case "/deleteteacher":
				deleteUserTeacher(request, response);
				break;
			case "/editteacher":
				showEditFormTeacher(request, response);
				break;
			case "/updateteacher":
				updateUserTeacher(request, response);
				break;	
			
			case "/newclassroom":
				newClassRoom(request, response);
				break;	
				
			case "/insertclassroom":
				insertClassRoom(request, response);
				break;
				
			case "/updateclassroom":
				updateClassRoom(request, response);
				break;
			case "/deleteclassroom":
				deleteClassRoom(request, response);
				break;
			case "/editclassroom":
				showEditClassRoom(request, response);
				break;	
				
				
			case "/newcourse":
				newCourse(request, response);
				break;	
				
			case "/insertcourse":
				insertCourse(request, response);
				break;
				
			case "/updatecourse":
				updateCourses(request, response);
				break;
			case "/deletecourse":
				deleteCourses(request, response);
				break;
			case "/editcourse":
				showEditCourses(request, response);
				break;	
				
			case "/list-report":
				 showReport(request,response);
				 break;
			case "/login":
				login(request,response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
  
    
    private void login(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	Cookie c = new Cookie("login","false");
		c.setMaxAge(0);
		response.addCookie(c);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("adminLogin.jsp");
		dispatcher.forward(request, response);
    }
    private void showReport(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
		dispatcher.forward(request, response);
    }
    
    private void newClassRoom(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	List <Teachers> teachers = teacherDb.selectAllTeachers();
    	request.setAttribute("teachersList", teachers);
    	List <Courses> mycourse = coursesdb.selectAllSubjects();
    	request.setAttribute("courses", mycourse);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("newClassRoom-form.jsp");
		dispatcher.forward(request, response);
    }
    
    private void newCourse(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	List <Courses> mycourses = coursesdb.selectAllSubjects();
    	request.setAttribute("coursesList", mycourses);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("courses-form.jsp");
		dispatcher.forward(request, response);
    }
    
    private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
    	Boolean login =false;
    	Cookie c[]=request.getCookies(); 
    	for(int i=0;i<c.length;i++){  
    		 if(c[i].getName().equals("login" ) && c[i].getValue().equals("true")) {
    			 login=true;
    		 }
    		}
    	if(login) {
    		List<Students> listUser = studentDb.selectAllStudents();
    		request.setAttribute("listStudent", listUser);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
    		dispatcher.forward(request, response);
    	}
    	else {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("adminLogin.jsp");
    		dispatcher.forward(request, response);
    	}
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List <Courses> mycourse = coursesdb.selectAllSubjects();
    	request.setAttribute("listCourses", mycourse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request, response);
	}
	private void showNewFormTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Students existingUser = studentDb.selectStudent(id);
		List <Courses> mycourse = coursesdb.selectAllSubjects();
    	request.setAttribute("listCourses", mycourse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		request.setAttribute("student", existingUser);
		dispatcher.forward(request, response);

	}
	
	private void showEditClassRoom(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Classes existingUser = classesDb.selectSubject(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("newClassRoom-form.jsp");
		List <Teachers> teachers = teacherDb.selectAllTeachers();
    	request.setAttribute("teachersList", teachers);
    	List <Courses> mycourse = coursesdb.selectAllSubjects();
    	request.setAttribute("courses", mycourse);
		request.setAttribute("c", existingUser);
		dispatcher.forward(request, response);

	}
	
	private void showEditCourses(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Courses existingUser = coursesdb.selectSubject(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("courses-form.jsp");
		request.setAttribute("c", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String course = request.getParameter("course");

		Students newStudent = new Students(name, email, country,course);
		studentDb.insertStudent(newStudent);
		response.sendRedirect("/LearnersAcademy/");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String course = request.getParameter("course");


		Students book = new Students(id, name, email, country,course);
		studentDb.updateStudent(book);
		response.sendRedirect("/LearnersAcademy/");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		studentDb.deleteStudent(id);
		response.sendRedirect("/LearnersAcademy/");

	}
	
//-------------------------------
	
	private void showEditFormTeacher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Teachers existingUser = teacherDb.selectTeacher(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
		request.setAttribute("teacher", existingUser);
		dispatcher.forward(request, response);

	}
	
	


	private void insertClassRoom(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String duration = request.getParameter("duration");
		String teacher = request.getParameter("teacher");
		String[] courses = request.getParameterValues("courses");
		String finalResult="";
		List<String> list = new ArrayList<String>();
		list = Arrays.asList(courses);
		for(int i=0; i<list.size();i++) {
			finalResult+=(list.get(i));
			if(i!=list.size()-1) {
				finalResult+=(" , ");
			}
			
		}
		
	
		Classes c = new Classes(name, teacher, duration, finalResult);
		classesDb.insertSubject(c);
		response.sendRedirect("list-class");
	}
	
	
	
	private void insertCourse(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String course = request.getParameter("course");
		Courses c = new Courses(course);
	
		coursesdb.insertSubject(c);
		response.sendRedirect("list-courses");
	}
	
	private void insertUserTeacher(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String exp = request.getParameter("experience");
		Teachers newTeacher = new Teachers(name, email, exp);
		teacherDb.insertTeacher(newTeacher);
		response.sendRedirect("list-teacher");
	}

	private void updateUserTeacher(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String exp = request.getParameter("experience");

		Teachers t = new Teachers(id, name, email, exp);
		teacherDb.updateTeacher(t);
		response.sendRedirect("list-teacher");
	}
	
	private void updateClassRoom(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String teacher = request.getParameter("teacher");
		String dur = request.getParameter("duration");
		String[] courses = request.getParameterValues("courses");
		String finalResult="";
		List<String> list = new ArrayList<String>();
		list = Arrays.asList(courses);
		for(int i=0; i<list.size();i++) {
			finalResult+=(list.get(i));
			if(i!=list.size()-1) {
				finalResult+=(" , ");
			}
			
		}
		System.out.println(finalResult);
		System.out.println("check final");

		Classes t = new Classes(id, name, teacher, dur,finalResult);
		classesDb.updateClasses(t);
		response.sendRedirect("list-class");
	}
	
	private void updateCourses(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String course = request.getParameter("course");
		

		Courses t = new Courses(id, course);
		coursesdb.updateClasses(t);
		response.sendRedirect("list-courses");
	}

	private void deleteUserTeacher(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		teacherDb.deleteTeacher(id);
		response.sendRedirect("list-teacher");

	}
	private void deleteClassRoom(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		classesDb.deleteClasses(id);
		response.sendRedirect("list-class");

	}

	private void deleteCourses(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		coursesdb.deleteClasses(id);
		response.sendRedirect("list-courses");

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
