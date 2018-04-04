<%-- ======================================
		epreuves.jsp
========================================= --%>

<%@ page import="java.util.*,bdd.*,beans.*" %>

<jsp:include page="ihm/miseEnPageSPORT1.jsp">
	<jsp:param name="titre" value="LISTE DES EPREUVES"/>
</jsp:include>

<%	// ==============  CORPS =================================================
	List<Epreuve> epreuves = (List<Epreuve>)request.getAttribute("epreuves");
	Integer ide = 0;
	String coul="lignePaire";
	
	if(epreuves != null)
	{
		if((request.getParameter("ide")) != null)
		ide = Integer.parseInt(request.getParameter("ide"));

		out.println("\t\t\t\t<table>");
		String href1 = "controleur?cmd=epreuvesId&ide=" + ide;
		String href2 = "controleur?cmd=epreuvesNom&ide=" + ide;
		String href3 = "controleur?cmd=epreuvesCateg&ide=" + ide;
		String href4 = "controleur?cmd=epreuvesDateP&ide=" + ide;
		String href5 = "controleur?cmd=epreuvesTarifClub&ide=" + ide;
		String href6 = "controleur?cmd=epreuvesTarifNonClub&ide=" + ide;
	
		out.println("\t\t\t\t\t<tr class=\"enteteTableau\">");
		out.println("\t\t\t\t\t\t<th><a href=\"" + href1 + "\">ide</a></th>");
		out.println("\t\t\t\t\t\t<th><a href=\"" + href2 + "\">nom</a></th>");
		out.println("\t\t\t\t\t\t<th><a href=\"" + href3 + "\">categ</a></th>");
		out.println("\t\t\t\t\t\t<th><a href=\"" + href4 + "\">datep</a></th>");
		out.println("\t\t\t\t\t\t<th><a href=\"" + href5 + "\">tarifclub</a></th>");
		out.println("\t\t\t\t\t\t<th><a href=\"" + href6 + "\">tarifnonclub</a></th>");
		out.println("\t\t\t\t\t</tr>");
		for (Epreuve e : epreuves) {
			coul=(coul.equals("lignePaire"))?"ligneImpaire":"lignePaire";

			if(ide == e.getIde()) {
				coul = "ligneId";
				out.println("\t\t\t\t\t<tr class=\"" + coul + "\">");
			} else
				out.println("\t\t\t\t\t<tr class=\"" + coul + "\">");

			String href = "controleur?cmd=inscriptionEpreuves&ide=" + e.getIde();
			out.println("\t\t\t\t\t\t<td><a href="+href+">" + e.getIde() + "</a></td>");
			out.println("\t\t\t\t\t\t<td>" + e.getNom() + "</td>");
			out.println("\t\t\t\t\t\t<td>" + e.getCateg() + "</td>");
			out.println("\t\t\t\t\t\t<td>" + e.getDatep() + "</td>");
			out.println("\t\t\t\t\t\t<td>" + e.getTarifClub() + "</td>");
			out.println("\t\t\t\t\t\t<td>" + e.getTarifNonClub() + "</td>");
			out.println("\t\t\t\t\t</tr>");
		}}
	out.println("\t\t\t\t</table>");
	// ==============  CORPS =================================================
%>

<jsp:include page="ihm/miseEnPageSPORT2.jsp" />
