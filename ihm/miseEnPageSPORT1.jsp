<%
    boolean DEBUG = true;

    // on vÃ©rifie que la JSP a Ã©tÃ© activÃ©e via le controleur
    Boolean controleurOK = ( Boolean ) request.getAttribute( "controleurOK" );
    if( controleurOK == null ) {
        request.setAttribute( "controleurOK" , true );
%>
<jsp:forward page = "/erreurs/erreurDroit.jsp" />
<%
    }

    Integer droitUtil;

    try {
        droitUtil = ( Integer ) session.getAttribute( "droit" );
    } catch( Exception e ) {
        droitUtil = null;
    }

	/*
		Le droit de connexion doit etre 1 (consult) ou 2 (admin). Si
		ce n'est aucun des deux alors on est renvoyÃ© vers la page de login.
	 */
    boolean afficheMenuAdmin = false;

    if( droitUtil == null ) {
%>
<jsp:forward page = "login.jsp" />
<%
}
else {
    afficheMenuAdmin = ( droitUtil == 2 );
    if( ( droitUtil != 1 ) && ( droitUtil != 2 ) ) {
%>
<jsp:forward page = "login.jsp" />
<%
        } //fin droit!=1 et droit!=2
    } //fin else
%>


<!DOCTYPE html>
<html >
<head >
    <meta charset = "UTF-8" />
    <meta http-equiv = "Content-type" content = "text/html;charset=UTF-8" >
    <title >Bienvenue</title >
    <link rel = "stylesheet" type = "text/css" href = "ihm/site.css" >
</head >
<body >

<div class = "haut" >
    <div class = "hautGauche" >
        <img src = "ihm/imagesWA3.png" alt = "logo webapp" />
    </div >
    <div class = "hautCentre" >
        Gestion de la base SPORT

        <%
            if( DEBUG ) {
                String nomCmd    = request.getParameter( "cmd" );
                String classeCmd = ( String ) request.getAttribute( "classeCmd" );
                String jsp       = ( String ) request.getAttribute( "jsp" );
        %>
        <table class = "erreur" >
            <tr >
                <th > nom Commande</th >
                <th > nom Classe de commande</th >
                <th > nom JSP associÃ©e</th >
            </tr >
            <tr >
                <td ><%= nomCmd %>
                </td >
                <td ><%= classeCmd %>
                </td >
                <td ><%= jsp %>
                </td >
            </tr >
        </table >
        <%
            }
        %>

    </div >
</div >

<div class = "milieu" >
    <div class = "menu" >
        <a href = "controleur?cmd=deconnect" > D&eacute;connexion </a >
        <hr />
        Consultation
        <hr />
        <ul >
            <li ><a href = "controleur?cmd=participants" >Liste des participants</a ></li >
            <li ><a href = "controleur?cmd=epreuves" >Liste des &eacute;preuves</a ></li >
            <li ><a href = "controleur?cmd=inscriptions" >Liste des inscriptions</a ></li >
        </ul >
        <%
            if( afficheMenuAdmin ) {
        %>
        <br />
        <hr />
        Administration
        <hr />
        <ul >

            <li ><a href = "controleur?cmd=ajouterParticipant" > Nouveau participant </a ></li >
            <li ><a href = "controleur?cmd=ajouterEpreuve" > Nouvelle &eacute;preuve </a ></li >
            <li ><a href = "controleur?cmd=ajoutInscription" > Nouvelle inscription </a ></li >

            <br />

            <li ><a href = "controleur?cmd=modifierParticipant" > Modifier participant </a ></li >
            <li ><a href = "controleur?cmd=modifierEpreuve" > Modifier &eacute;preuve </a ></li >
            <li ><a href = "controleur?cmd=modifierInscription" > Modifier inscription </a ></li >

            <br />

            <li ><a href = "controleur?cmd=supprimerParticipant" > Supprimer participant </a ></li >
            <li ><a href = "controleur?cmd=supprimerEpreuve" > Supprimer &eacute;preuve </a ></li >
            <li ><a href = "controleur?cmd=supprimerInscription" > Supprimer inscription </a ></li >

        </ul >
        <%
            }
        %>

    </div >
    <div class = "contenu" >
        <div id = "titre" >
            <%= request.getParameter( "titre" )%>
        </div >