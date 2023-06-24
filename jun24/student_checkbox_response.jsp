<html>

<head><title>Student Confirmation Title</title></head>
<body>

The student is confirmed : ${param.firstName} ${param.lastName} 
<br/>

Student's Country : ${param.Country}
<br/>

Student's Favorite Programming Language : ${param.favoriteLanguage}

<br/>

The student is confirmed : <%= request.getParameter("firstName") %> <%=request.getParameter("lastName") %>

<br/>

Students Country <%=request.getParameter("Country") %>

<br/>

Student's Favorite Language : <%= request.getParameter("favoriteLanguage") %>

<br/>

<%
	String[] lang=request.getParameterValues("favoriteLanguage2");
	
	if(lang!=null)
	{
	for (String temp : lang){
		if(temp!=null)
		{
		out.println("<li>" + temp + "<li>");
		}
	}
	}
%>

</body>

</html>