<!DOCTYPE html>
<html >
<head >
<meta http-equiv = "Content-type" content = "text/html;charset=UTF-8" >
<title >Connexion</title >
<link rel = "stylesheet" type = "text/css" href = "ihm/site.css" >
</head >
<body >

<%
    String erreur = ( String ) request.getAttribute( "erreur" );
    if( erreur != null ) out.println( "<div class=\"messerreur\">" + erreur + "</div>" );
%>

<form action = 'controleur' method = "get" >
    <input type = "hidden" name = "cmd" value = "login" >
    <table class = "login" >
            <tr >
                <td >nom</td >
                <td >
                    <input name = "nom" title = "" value = "admin" >
                </td >
            </tr >
            <tr >
                <td >mot de passe</td >
                <td ><input type = "password" name = "pass" title = "" value = "adminpwd" ></td >
            </tr >
        <tr >
            <td rowspan = "2" >
                <input type = "submit" >
            </td >
        </tr >
    </table >
</form >


</body >
</html >
