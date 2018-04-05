<%@ page import="java.util.*,cmd.*,beans.*" %>

<% String titre = "Ajout d'un participant"; %>

<%@include file="ihm/miseEnPageSPORT1.jsp" %>


<%		out.println("<form>");
		out.println("<table>");
		out.println("<tr>");
			out.println("<td>");
			out.println("idp");
			out.println("</td>");

			out.println("<td>");
			out.println("<input type=\"text\" name=\"idp\">");
			out.println("</td>");
		out.print("</tr>");

		out.print("<tr>");

			out.println("<td>");
			out.println("nom");
			out.println("</td>");

			out.println("<td>");
			out.println("<input type=\"text\" name=\"nom\">");
			out.println("</td>");

		out.print("</tr>");

		out.print("<tr>");

		out.println("<td>");
		out.println("age");
		out.println("</td>");

		out.println("<td>");
		out.println("<input type=\"text\" name=\"age\">");
		out.println("</td>");

		out.println("</td>");
	out.println("</table></form>");
%>

<jsp:include page="ihm/miseEnPageSPORT2.jsp" />




