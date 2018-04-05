<jsp:include page="ihm/miseEnPageSPORT1.jsp">
    <jsp:param name="titre" value="Ajouter un participant"/>
</jsp:include>


<% out.println("<form action = 'controleur' method = 'get'>");
    out.println("<input type = 'hidden' name = 'cmd' value = 'ajoutParticipantForm' >");
    out.println("<table>");

    out.print("<tr>");

    out.println("<td>");
    out.println("idp");
    out.println("</td>");

    out.println("<td>");
    out.println("<input type='number' name='idp' required>");
    out.println("</td>");

    out.print("</tr>");

    out.print("<tr>");

    out.println("<td>");
    out.println("ide");
    out.println("</td>");

    out.println("<td>");
    out.println("<input type='number' name='ide' required>");
    out.println("</td>");

    out.println("</td>");
    out.println("</tr>");
    out.print("<tr>");

    out.println("<td>");
    out.println("categorie de tarif");
    out.println("</td>");

    out.println("<td>");
    out.println(
            "<select name=\"categtarif\" required>\n" + "\n" + "           <option value=\"club\">Club</option>\n" +
                    "\n" + "           <option value=\"exterieur\">Exterieur</option>\n" + "\n" + "       </select>");
    out.println("</td>");
    out.println("</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>");
    out.println("<input type = 'submit'>");
    out.println("<td>");
    out.println("</tr>");
    out.println("</table></form>");
%>

<jsp:include page="ihm/miseEnPageSPORT2.jsp"/>




