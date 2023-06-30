<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%
	String[] cities = {"Mumbai", "Singapore", "Phili"};
	
pageContext.setAttribute("mycities", cities);
%>


<html>

<body>

<c:forEach var="tempCity" items="${mycities}">

${tempCity }<br/>
</c:forEach>


</body>

</html>