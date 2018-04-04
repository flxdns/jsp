<%-- ======================================
		erreurDroit.jsp
========================================= --%>

<!--
	JSP permettant d'afficher un message d'erreur suite &agrave; une
	tentative d'accès à une page sans en avoir les droits (en tapant l'URL par exemple)	
-->

<% String titre = "ERREUR"; %>

<%@include file="/ihm/miseEnPageSPORT1.jsp" %>

VOUS N'AVEZ PAS LES DROITS POUR ACCEDER A CETTE FONCTIONNALITE !

<jsp:include page="/ihm/miseEnPageSPORT2.jsp" />




