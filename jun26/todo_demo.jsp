<%@ page import="java.util.*" %>

<html>

<body>

<!-- Step 1: create HTML form -->

<form action="todo_demo.jsp">
	Add new item: <input type="text" name="theItem"/>
	
	<input type="submit" value="Submit"/>
</form>

<!--  <br> -->
<!--  Item entered:<request.getParameter("theItem") %> -->

<!-- Step 2: Add new item to "To Do" -->

<%
	//get to do items from session
	List<String> items=(List<String>)session.getAttribute("myToDoList");
	
	//if the to do items doesn't exist then create a new one
	if (items==null){
		items = new ArrayList<String>();
		session.setAttribute("myToDoList",items); //adds a list to session
	}
	//see if there is form data to add
	String theItem = request.getParameter("theItem");
	
	if(theItem != null){
		items.add(theItem);
	}

%>

<!-- Step 3: Display all "To DO" item from session -->
<hr>
<b>To List Items : </b> <br/>

<ol>
<%
	for (String temp : items){
		out.println("<li>" + temp +"</li>");
	}
%>
</ol>
</body>

</html>

<!--  -->
