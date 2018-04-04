<%-- ======================================
		deconnexion.jsp
========================================= --%>

<%@ page import="java.util.*,bdd.*,beans.*" %>

<% 

	String titre="DECCONEXION"; 

	String idSession = session.getId();
	out.println("Id session:" + idSession + "<br>");
	session.invalidate();

%>

<%@include file="ihm/miseEnPageSPORT1.jsp" %>

<jsp:include page="ihm/miseEnPageSPORT2.jsp" />
