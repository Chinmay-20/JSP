<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!--  prefix=c means short form for core -->
<!-- we used a core tag library to set a variable and display it. --> 
<html>

<body>

<c:set var="stuff" value="<%= new java.util.Date() %>" />

Time on server is ${stuff}

</body>

</html>
