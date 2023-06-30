<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.*, com.chinmay.jsp.tagdemo.Student" %>

<%

	//creating sample data normally provided by MVC
	List<Student> data= new ArrayList<>();
	
	data.add(new Student("A","B", false));
	data.add(new Student("C","D", false));
	data.add(new Student("E","F", true));
	
	pageContext.setAttribute("myStudents", data);
%>


<html>

<body>
	<table border="1">
	<tr>
	<th>First Name</th>
	<th>Last Name</th>
	<th>Gold membership</th>
	</tr>

<c:forEach var="tempStudent" items="${myStudents}">
	<tr>
		<td>${tempStudent.firstName} </td>
		<td>${tempStudent.lastName}</td>
		<td>${tempStudent.goldCustomer} </td>
	</tr>  
	
	
	
	
	<!--  in jsp expression methods they will call getter methods   behind the scenes it will caught tempStudent.getFirstName() -->
	<!--  For gold customers it is little bit different it calls tempStudent.isgoldCustomer  -->
	<!--  it all happens behind the scenes as part of JSP Expression language -->
</c:forEach>
</table>


</body>


</html>
