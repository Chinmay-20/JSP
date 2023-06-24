<html>

<head><title>Student Confirmation Title</title></head>
<body>

The student is confirmed : ${param.firstName} ${param.lastName} ${param.Country}


<br/>

The student is confirmed : <%= request.getParameter("firstName") %> <%=request.getParameter("lastName") %> <%=request.getParameter("Country") %>

</body>

</html>