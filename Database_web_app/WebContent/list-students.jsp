<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>


<head>
	<title>Student Tracker App</title>
	
	  <link type="text/css" rel="stylesheet" href="css/style.css"> 
</head>

<body>

<div id="wrapper">
	<div id="header">
		<h2>Random university</h2>
	</div>
	
	<div id="container">
		<div id="content">
		
		<!--  add new "add student" button" -->
		<input type="button" value="Add Student"
				onclick="window.location.href='add-student-form.jsp';return false;"
				class="add-student-button"
		/>
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
					<c:forEach var="tempStudent" items="${STUDENT_LIST}">				
					<tr>
						<!-- set up a link for each student -->
						<c:url var="tempLink" value="StudentControllerServlet"> <!-- url is JSTL tag it will define a link to url and also we can pass parameters to it-->
							<c:param name="command" value="LOAD"/>
							<c:param name="studentId" value="${tempStudent.id}" /> <!--  using JSP expression language to get ID -->
						</c:url>
						<!-- set up a link to delete a student -->
						<c:url var="deleteLink" value="StudentControllerServlet"> <!-- url is JSTL tag it will define a link to url and also we can pass parameters to it-->
							<c:param name="command" value="DELETE"/>
							<c:param name="studentId" value="${tempStudent.id}" /> <!--  using JSP expression language to get ID -->
						</c:url>
						
						<td> ${tempStudent.firstName} </td> <!-- This will call get() method -->
						<td> ${tempStudent.lastName}  </td>
						<td> ${tempStudent.email}  </td>
						<td> 
						<a href="${tempLink}">Update</a> 
						| 
						<a href="${deleteLink}"
						onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a> <!-- java script student -->
						</td>
					</tr>
					
				</c:forEach>
			</table>
		</div>
	</div>
</div>

</body>



</html>
