<%-- ======================================
		epreuveMAJ.jsp
========================================= --%>

<%@ page import="java.util.*,bdd.*,beans.*" %>

<jsp:include page="ihm/miseEnPageSPORT1.jsp">
	<jsp:param name="titre" value="LISTE DES PARTICIPANTS"/>
</jsp:include>

<%	// ==============  CORPS =================================================
	out.println("Poisson");
	// ==============  CORPS =================================================
%>

<jsp:include page="ihm/miseEnPageSPORT2.jsp" />
