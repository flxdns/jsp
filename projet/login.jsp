<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Connexion</title>
<link rel="stylesheet" type="text/css" href="ihm/site.css">
</head>
<body>

<% 
	String erreur= (String)request.getAttribute("erreur");
  	if(erreur!= null)
		out.println("<div class=\"messerreur\">"+erreur+"</div>");
%>	
<table class="login">
<form action='controleur' method="get">
<input type="hidden" name="cmd" value="login"
<tr><td>nom:<input name="nom"></td></tr>
<tr><td>pass:<input type="password" name="pass"></td></tr>
<tr><td><input type="submit"></td></tr>
</form>
</table>

</body>
</html>
