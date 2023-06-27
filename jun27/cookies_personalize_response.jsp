<!-- Read form data and also create or set cookies and pass them back to browser  -->
<html>

<head><title>Confirmation</title></head>

<%
	//read form data
	String favLang = request.getParameter("favoriteLanguage");

	//create a cookie
	Cookie theCookie = new Cookie("myApp.favoriteLanguage", favLang);
	
	//set life span..in total number of seconds
	theCookie.setMaxAge(60*60*24*365);
	
	//send cookie to browser
	response.addCookie(theCookie);
%>

<body>

Thanks! We set your favorite language to: ${param.favoriteLanguage}

<a href="cookies_homepage.jsp">Return to homepage</a>
</body>

</html>