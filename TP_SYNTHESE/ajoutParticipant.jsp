<%-- ======================================
		participants.jsp
========================================= --%>

<%@ page import="java.util.*,bdd.*,beans.*" %>

<% String titre = "LISTE DES PARTICIPANTS"; %>

<%@include file="ihm/miseEnPageSPORT1.jsp" %>



<%	// ==============  CORPS =================================================
	List<Participant> participants = (List<Participant>)request.getAttribute("participants");
	String coul="lignePaire";
	Integer idp = 0;
	if( participants != null)
	{
	
		if((request.getParameter("idp")) != null){
		idp = Integer.parseInt(request.getParameter("idp"));
	}
		String href1="controleur?cmd=participantId&idp="+idp;
		String href2="controleur?cmd=participantNom&idp="+idp;
		String href3="controleur?cmd=participantAge&idp="+idp;
		out.println("<form>");
		out.println("<table>");
		out.println("<tr class=\"enteteTableau\"><th><a href=\""+href1+"\">idp</a></th><th><a href=\""+href2+"\">nom</a></th><th><a href=\""+href3+"\">age</a></th></tr>");
		for (Participant p : participants) {
			coul=(coul.equals("lignePaire"))?"ligneImpaire":"lignePaire";
			if(idp == p.getIdp())
			{
				coul = "ligneId";
				out.println("<tr class=\""+coul+"\">");
			}
			else
			{
			    out.println("<tr class=\""+coul+"\">");
			}
			String href="controleur?cmd=inscription&idp="+p.getIdp();
			out.println("<td><a href="+href+">"+p.getIdp()+"</a></td>");
			out.println("<td>"+p.getNom()+"</td>");
			out.println("<td>"+p.getAge()+"</td>");
			out.println("</tr>");
		}}
		out.println("<tr class=\""+coul+"\">");
		out.println("<td>");
		out.println("<input type=\"text\" name=\"idp\">");
		out.println("</td>");
		
		out.println("<td>");
		out.println("<input type=\"text\" name=\"nom\">");
		out.println("</td>");
		
		out.println("<td>");
		out.println("<input type=\"text\" name=\"age\">");
		out.println("</td>");
	out.println("</table></form>");
	// ==============  CORPS =================================================
%>

<jsp:include page="ihm/miseEnPageSPORT2.jsp" />




