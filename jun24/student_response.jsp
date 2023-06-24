<html>

<head><title>Student Confirmation Title</title></head>
<body>

The student is confirmed : ${param.firstName} ${param.lastName} 


<br/>

The student is confirmed : <%= request.getParameter("firstName") %> <%=request.getParameter("lastName") %>

</body>

</html>