<%-- ======================================
		participants.jsp
========================================= --%>

<%@ page import="java.util.*,bdd.*,beans.*" %>

<jsp:include page="ihm/miseEnPageSPORT1.jsp">
	<jsp:param name="titre" value="LISTE DES PARTICIPANTS"/>
</jsp:include>

<%	// ==============  CORPS =================================================
	List<Participant> participants = (List<Participant>)request.getAttribute("participants");
	String coul = "lignePaire";
	Integer idp = 0;
	if( participants != null) {
		if((request.getParameter("idp")) != null)
			idp = Integer.parseInt(request.getParameter("idp"));

		String href1 = "controleur?cmd=participants";
		String href2 = "controleur?cmd=participantsNom&idp=" + idp;
		String href3 = "controleur?cmd=participantsAge&idp=" + idp;
		out.println("\t\t\t\t<table>");
		out.println("\t\t\t\t\t<tr class=\"enteteTableau\">");
		out.println("\t\t\t\t\t\t<th><a href=\"" + href1 + "\">idp</a></th>");
		out.println("\t\t\t\t\t\t<th><a href=\"" + href2 + "\">nom</a></th>");
		out.println("\t\t\t\t\t\t<th><a href=\"" + href3 + "\">age</a></th>");
		out.println("\t\t\t\t\t</tr>");
		for (Participant p : participants) {
			coul = (coul.equals("lignePaire"))?"ligneImpaire":"lignePaire";
			if(idp == p.getIdp()) {
				coul = "ligneId";
				out.println("<tr class=\"" + coul + "\">");
			} else
				out.println("\t\t\t\t\t<tr class=\"" + coul + "\">");
			String href = "controleur?cmd=inscription&idp=" + p.getIdp();
			out.println("\t\t\t\t\t\t<td><a href=" + href + ">" + p.getIdp() + "</a></td>");
			out.println("\t\t\t\t\t\t<td>" + p.getNom() + "</td>");
			out.println("\t\t\t\t\t\t<td>" + p.getAge() + "</td>");
			out.println("\t\t\t\t\t</tr>");
		}
	}
	out.println("\t\t\t\t</table>");
	// ==============  CORPS =================================================
%>

<jsp:include page="ihm/miseEnPageSPORT2.jsp" />
