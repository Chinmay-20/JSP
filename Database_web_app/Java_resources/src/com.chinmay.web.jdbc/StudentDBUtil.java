package com.chinmay.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {
	
	private DataSource dataSource;
	
	public StudentDBUtil(DataSource dataSource) {
		 this.dataSource=dataSource; 
		}//somebody will create studentDBUtil, pass reference to our dataSource object servlet will do this work
		 
	
	
	public List<Student> getStudents() throws Exception//this method will list students
	{
		List<Student> students = new ArrayList<>();
		
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try {
		// get a connection
		myConn=dataSource.getConnection();
		
		//create a sql statement
		String stmt="select * from student order by last_name";
		myStmt = myConn.createStatement();
		
		
		//execute query
		myRs= myStmt.executeQuery(stmt);
		
		//process result set
		while(myRs.next())
		{
			//retrieve data from result set row
			int id=myRs.getInt("id");
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");
			
			
			//create new student object
			Student tempStudent = new Student(id, firstName, lastName, email);
			
			
			//add it to list of students
			students.add(tempStudent);
			
		}
		
		
			return  students;
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
		
	}


 
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		try {
			if(myRs !=null) {
				myRs.close();
			}
			if(myStmt !=null) {
				myStmt.close();
			}
			if(myConn!=null) {
				myConn.close(); //doesn't close it..just puts back in connection pool
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}



	public void addStudent(Student theStudent) throws SQLException {
		
		//the student object is student class object so we can access all methods in that class
		Connection myConn=null;
		PreparedStatement myStmt=null;
		 
		try {
		//get db connection
		myConn = dataSource.getConnection();
		
		//create sql statement for insert
		String sql = "insert into student " + "(first_name, last_name, email) " + "values (?,?,?)";
		
		myStmt=myConn.prepareStatement(sql);
		
		
		//set param values for student
		myStmt.setString(1, theStudent.getFirstName());
		myStmt.setString(2, theStudent.getLastName());
		myStmt.setString(3, theStudent.getEmail());
		
		//execute sql insert
		myStmt.execute();
		
		}
		finally {
		//clean JDBC objects
			close(myConn,myStmt, null);
		}
	}



	public Student getStudent(String theStudentId) throws Exception {
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet rs=null;
		int studentId;
		Student theStudent;
		try {
			//convert id to int
			studentId=Integer.parseInt(theStudentId);
			
			//get connection to database
			myConn=dataSource.getConnection();
			
			//create sql statement
			String sql = "Select * from student where id = ?";
			
			//create prepared statement
			myStmt=myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1,studentId);
			
			//execute statement
			rs=myStmt.executeQuery();
			
			//retieve data from result
			if(rs.next())
			{
				String firstName=rs.getString("first_name");
				String lastName=rs.getString("last_name");
				String email=rs.getString("email");
				
				// use studentId during construction
				theStudent=new Student(studentId, firstName, lastName, email);
			} 
			else
			{
				throw new Exception("could not find student id: " + studentId);
			}
			return theStudent;
		}
		finally
		{
			//clean jdbc objects 
			close(myConn, myStmt, rs);
		}
	}



	public void updateStudent(Student theStudent) throws Exception {
		
		Connection myConn=null;
		PreparedStatement myStmt = null;
		try {
		//get db connection
		myConn = dataSource.getConnection();
		
		//create sql statement
		String sql = "update student " + "set first_name=?, last_name=?, email=? " + "where id=?";
		
		//prepared statement
		myStmt=myConn.prepareStatement(sql);
		
		//set params
		myStmt.setString(1, theStudent.getFirstName());
		myStmt.setString(2, theStudent.getLastName());
		myStmt.setString(3, theStudent.getEmail());
		myStmt.setInt(4, theStudent.getId());
		
		//execute sql statetement
		myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
	}



	public void deleteStudent(String theStudentId) throws Exception{
		
		Connection myConn=null;
		PreparedStatement myStmt = null;
		
		try {
			
			//convert student id to int
			int studentId=Integer.parseInt(theStudentId);  
			
			//get connection to database
			myConn=dataSource.getConnection();
			
			//create sql to delete student
			String sql = "delete from student where id =?";
			
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, studentId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
			
	}
}
//http://localhost:8080/web-student-tracker/StudentControllerServlet?command=LOAD&studentId=19
