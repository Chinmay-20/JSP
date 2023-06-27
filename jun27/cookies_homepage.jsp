<html>

<body>
<h3> Training Portal</h3>

<!--  read favorite programmiung language cookie -->
<%
	String favLang="Java";

	Cookie[] theCookies = request.getCookies();
	
	//find for favorite lang cookie
	
	if(theCookies!=null){
		for (Cookie tempCookie : theCookies){
			if("myApp.favoriteLanguage".equals(tempCookie.getName())){
				favLang = tempCookie.getValue();
				break;
			}
		}
	}

%>

<!-- now show a personalized language -->

<!--  show books -->
<h4>New books for <%= favLang %></h4>
<ul>
<li>abcd</li>
<li>efgh</li>
</ul>

<h4>New reports for <%= favLang %></h4>
<ul>
<li>abcd</li>
<li>efgh</li>
</ul>

<h4>hot jobs for <%= favLang %></h4>
<ul>
<li>abcd</li>
<li>efgh</li>
</ul>

<a href="cookies_personalize_form.html">Personalize this page</a>
</body>

</html>
