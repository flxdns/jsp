<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
		<title>Bienvenue</title>
		<link rel="stylesheet" type="text/css" href="ihm/site.css">
	</head>

	<body>

		<%
			boolean DEBUG = true;

			// on vérifie que la JSP a été activée via le controleur
			Boolean controleurOK = (Boolean)request.getAttribute("controleurOK");
			if (controleurOK == null) {
				request.setAttribute("controleurOK",true);
		%>
				<jsp:forward page="/erreurs/erreurDroit.jsp" />
		<%
			}

			Integer droitUtil;

			try{
				droitUtil =  (Integer) session.getAttribute("droit");// A CHANGER !
			} catch(Exception e) {
				droitUtil = null;
			}
			/*
				Le droit de connexion doit etre 1 (consult) ou 2 (admin). Si
				ce n'est aucun des deux alors on est renvoyé vers la page de login.
			 */
			boolean afficheMenuAdmin;

			if (droitUtil == null) {
		%>
				<jsp:forward page="login.jsp" />
		<%
			}
			else {
				afficheMenuAdmin = (droitUtil == 2);
				if ((droitUtil != 1) && (droitUtil != 2)) {
		%>
					<jsp:forward page="login.jsp" />
		<%
				} //fin droit!=1 et droit!=2
			} //fin else
		%>


		<div class="haut">
			<div class="hautGauche">
				<img src="ihm/imagesWA3.png" alt="logo webapp"/>
			</div>
			<div class="hautCentre">
				Gestion de la base SPORT

				<%
					if (DEBUG) {
						String nomCmd = request.getParameter("cmd");
						String classeCmd = (String)request.getAttribute("classeCmd");
						String jsp = (String)request.getAttribute("jsp");
				%>
				<table class="erreur">
					<tr>
						<th> nom Commande </th>
						<th> nom Classe de commande </th>
						<th> nom JSP associée </th>
						</tr>
					<tr>
						<td> <%= nomCmd %> </td>
						<td> <%= classeCmd %> </td>
						<td> <%= jsp %> </td>
					</tr>
				</table>
				<%
					}
				%>
			</div>
		</div>

		<div class="milieu">
			<div class="menu">
				<a href="controleur?cmd=deconnect"> D&eacute;connexion </a>
				<br/>
				<br/>
				<hr/>
				Consultation
				<hr/>
				<ul>
					<li><a href="controleur?cmd=participants">Liste des participants</a></li>
					<li><a href="controleur?cmd=epreuves">Liste des &eacute;preuves</a></li>
					<li><a href="controleur?cmd=inscriptions">Liste des inscriptions</a></li>
				</ul>
				<%
					if (afficheMenuAdmin) {
				%>
				<br/>
				<hr/>
				Administration
				<hr/>
				<ul>
					<li><a href="controleur?cmd=epreuveMAJ"> Mise &agrave; jour des epreuves</a></li>
					<li><a href="controleur?cmd=ajoutParticipant"> Ajouter un participant </a></li>
					<li><a href="controleur?cmd=suppressionParticipants">Supression des participants </a></li>

					<li><a href="controleur?cmd=epreuvesMAJ"> Mise &agrave; jour des epreuves </a></li>
					<li><a href="controleur?cmd=ajoutEpreuves"> Ajouter un epreuve </a></li>
					<li><a href="controleur?cmd=suppressionEpreuves">Supression des epreuves </a></li>

					<li><a href="controleur?cmd=inscriptionsMAJ"> Mise &agrave; jour des inscriptions </a></li>
					<li><a href="controleur?cmd=ajoutInscriptions"> Ajouter une inscription </a></li>
					<li><a href="controleur?cmd=suppressionInscriptions">Supression des inscriptions </a></li>
				</ul>
				<%
					}
				%>

			</div>
			<div class="contenu">
				<div id="titre">
					<% if(request.getParameter("titre") == null) {
						//out.print(titre);
						} else {
							out.print(request.getParameter("titre"));
						}
					%>
				</div>
