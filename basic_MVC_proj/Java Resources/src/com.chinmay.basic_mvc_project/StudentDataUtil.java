package com.chinmay.basic_mvc_project;

import java.util.ArrayList;
import java.util.List;

//this is our model. So it will return list of student objects when servlet calls. basically it is called 
// by servlet

public class StudentDataUtil {
	
	public static List<Student> getStudents(){
		 //create an empty list
		
		List<Student> students = new ArrayList<>();
		
		//add sample data
		
		students.add(new Student("a","b","c"));
		students.add(new Student("d","e","f"));
		students.add(new Student("i","j","k"));
		
		
		//return the list
		return students;
	}

}
