<%@ page import="java.util.*" %>

<html>
<body>
    <!-- Step 1: create HTML form -->
    <form action="todo_demo.jsp">
        Add new item: <input type="text" name="theItem"/>
        <input type="submit" value="Submit"/>
    </form>

    <%-- Step 2: Add new item to "To Do" list --%>
    <%
        // Get the "myToDoList" ArrayList from session
        List<String> itemsSet = (List<String>) session.getAttribute("myToDoListSet");

        // If the ArrayList doesn't exist in session, create a new one
        if (itemsSet == null){
            itemsSet = new ArrayList<String>();
            session.setAttribute("myToDoListSet", itemsSet);
        }

        // Get the "myToDoList" ArrayList from request
        List<String> itemsGet = new ArrayList<String>();

        // See if there is form data to add
        String theItem = request.getParameter("theItem");

        if(theItem != null){
            itemsSet.add(theItem);  // Add to the "myToDoListSet" ArrayList
            itemsGet.add(theItem);  // Add to the "myToDoListGet" ArrayList
        }
    %>

    <!-- Step 3: Display all "To Do" items from session and request -->
    <hr>
    <b>To List Items from Set Attribute: </b> <br/>
    <ol>
        <%
            for (String temp : itemsSet){
                out.println("<li>" + temp +"</li>");
            }
        %>
    </ol>

    <b>To List Items from Get Attribute: </b> <br/>
    <ol>
        <%
            for (String temp : itemsGet){
                out.println("<li>" + temp +"</li>");
            }
        %>
    </ol>
</body>
</html>
