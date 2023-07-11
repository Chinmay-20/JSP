package com.chinmay.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private StudentDBUtil studentDbUtil;
	
	// use java resource EE injection so that they inject resource for out project
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	//initialize studentDbUtil. to initialize it servlet life cycle have special method call init.
	//init method is called by app server when servlet is initialized
	
	
	//right click ->  source ->override /implement method -> generic class after getStringname
	//init method is called by tomcat when server is first loaded or initialized
	//inserting custom initialization work
	//what you do in constructors you would do in init method
	@Override
	public void init() throws ServletException {
		super.init();
			
		//create instance of student database util and pass in datasource object
		try {
			studentDbUtil = new StudentDBUtil(dataSource);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}
		
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// list students in MVC fashion get data, set attribute, use request dispatcher and send data to JSP
		
		//form send data using get request so ot will come in doget method before editing all we did is 
		//list the students, but now read the command  and route the code depending if to add student or list students
		
		try {
			
			//read the command parameter
			String theCommand = request.getParameter("command");
			
			//if command is missing the default to listing students
			if(theCommand==null) {
				theCommand="LIST";
			}
			
			//route to appropriate method
			switch(theCommand) {
			case "LIST":
				listStudents(request,response);  
				break;
			case "ADD":
				addStudent(request, response);
				break;
			case "LOAD":
				loadStudent(request, response);
				break;
			case "UPDATE":
				updateStudent(request, response);
				break;
			case "DELETE":
				deleteStudent(request,response);
			default:
				listStudents(request,response);
			}
			
			
			//list students in MVC fashion
			listStudents(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//read student id from form data
		String theStudentId = request.getParameter("studentId");
		
		//delete student from database
		studentDbUtil.deleteStudent(theStudentId);
		
		//send them back to "list students" page
		listStudents(request, response);
		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
			//read student info from form data
			int id=Integer.parseInt(request.getParameter("studentId"));
			String firstName =(request.getParameter("firstName"));
			String lastName =(request.getParameter("lastName"));
			String email =(request.getParameter("email"));
			
			//create a new student object
			Student theStudent = new Student(id, firstName, lastName, email);
			
			//perform update on database
			studentDbUtil.updateStudent(theStudent);
			
			//send them back to "lists students" page
			listStudents(request,response);
		
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		// read student id frpm form data
		String theStudentId = request.getParameter("studentId");
		
		//get student from database (db util)
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		
		//place student in request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		
		//send to jsp page : update studetent.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		//create a new student object
		Student theStudent=new Student(firstName, lastName, email);
		
		//add student to database
		studentDbUtil.addStudent(theStudent);
		
		//send back to main page (student list)
		listStudents(request, response);
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//get students from db util
		List<Student> students= studentDbUtil.getStudents();
		
		//add students to request
		request.setAttribute("STUDENT_LIST",students); //(name,object)
		
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}

	

}
