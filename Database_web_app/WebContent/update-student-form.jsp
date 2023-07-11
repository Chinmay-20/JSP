<!DOCTYPE html>

<html>

<head>
	<title>Update Student</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body> 

	<div id="wrapper">
		<div id="header">
			<h2>Random University</h2>
		</div>	
	</div>
	
	<div id="container">
		<h3>Update Student</h3>
		
		<form action="StudentControllerServlet" method="GET"> <!-- send data to student controller servlet using get method, this will call doGet method -->
			<input type="hidden" name="command" value="UPDATE" />
			
			<input type="hidden" name="studentId" value="${THE_STUDENT.id}" /><!-- we actually need to track that student id so whem we do update and when we send it tp servlet the servlet knows in which id we need to perfrom update -->
			
			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td><input type="text" name="firstName" 
									value = "${THE_STUDENT.firstName}"/></td> <!--  this value will pre populate data and it id THE_STUDENT becuase in request attribute in studentcontroller servlet we set it as THE_STUDENT -->
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName" 
									value = "${THE_STUDENT.lastName}"/></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" 
									value = "${THE_STUDENT.email}"/></td>
					</tr>
					<tr>
						<td><label>SAVE:</label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div style = "clear: both;"></div>
		<p>
			<a href="StudentControllerServlet">Back to List</a>
		</p>
	</div>

</body>

</html>
