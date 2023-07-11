package com.chinmay.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	//Define datasource/connection pool for resource Injection. use Java EE feature in which java app
	//server tomcat will inject connection pool for us.
	@Resource(name="jdbc/web_student_tracker") // name is similar to resource name in context.xml
	private DataSource dataSource;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//step 1 : setting up print writer
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		
		//step 2 : get connection to database
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myConn = dataSource.getConnection(); //getting connection to database from connection pool of DataSource
		
		//step 3 : create sql statement
		String sql = "Select * from student";
		myStmt = myConn.createStatement();
		
		//step 4 ; execute sql query
		myRs = myStmt.executeQuery(sql);
		
		//step 5 : process result
		while (myRs.next()) {
			String email=myRs.getString("email");
			out.println(email);
		}
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
