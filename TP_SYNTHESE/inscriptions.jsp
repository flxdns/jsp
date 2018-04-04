<%-- ======================================
		inscriptions.jsp
========================================= --%>

<%@ page import="java.util.*,bdd.*,beans.*" %>

<jsp:include page="ihm/miseEnPageSPORT1.jsp">
	<jsp:param name="titre" value="LISTE D'INSCRIPTIONS"/>
</jsp:include>

<%	// ==============  CORPS =================================================
	List<Inscription> inscriptions = (List<Inscription>)request.getAttribute("inscriptions");
	String coul="lignePaire";

	out.println("\t\t\t\t<table>");

	Integer idp = 0;
	Integer ide = 0;
	if(inscriptions != null) {
		if((request.getParameter("idp")) != null)
			idp = Integer.parseInt(request.getParameter("idp"));

		if((request.getParameter("ide")) != null)
			ide = Integer.parseInt(request.getParameter("ide"));

		String href1 = "controleur?cmd=inscription1&idp="+idp+"&ide=" + ide;
		String href2 = "controleur?cmd=inscription2&idp="+idp+"&ide=" + ide;
		String href3 = "controleur?cmd=inscription3&idp="+idp+"&ide=" + ide;

		out.println("\t\t\t\t\t<tr class=\"enteteTableau\">");
		out.println("\t\t\t\t\t\t<th><a href=\"" + href1 + "\">idp</a></th>");
		out.println("\t\t\t\t\t\t<th><a href=\"" + href2 + "\">ide</a></th>");
		out.println("\t\t\t\t\t\t<th><a href=\"" + href3 + "\">categTarif</a></th>");
		out.println("\t\t\t\t\t</tr>");
		for (Inscription i : inscriptions) {
			coul=(coul.equals("lignePaire"))?"ligneImpaire":"lignePaire";

			if(idp == i.getIdp() || ide == i.getIde()) {
				coul = "ligneId";
				out.println("\t\t\t\t\t<tr class=\"" + coul + "\">");
				//out.println(""+idp);
			} else
				out.println("\t\t\t\t\t<tr class=\"" + coul + "\">");

			String hrefIdp = "controleur?cmd=inscriptionParticipant&idp=" + i.getIdp();
			String hrefIde = "controleur?cmd=inscriptionEpreuve&ide=" + i.getIde();
			out.println("\t\t\t\t\t\t<td><a href=" + hrefIdp + ">" + i.getIdp() + "</a></td>");
			out.println("\t\t\t\t\t\t<td><a href=" + hrefIde + ">" + i.getIde() + "</a></td>");
			out.println("\t\t\t\t\t\t<td>" + i.getCategTarif() + "</td>");
			out.println("\t\t\t\t\t</tr>");
		}
	}
	out.println("\t\t\t\t</table>");
	// ==============  CORPS =================================================
%>

<jsp:include page="ihm/miseEnPageSPORT2.jsp" />
