<jsp:include page="ihm/miseEnPageSPORT1.jsp">
    <jsp:param name="titre" value="Ajouter une inscription"/>
</jsp:include>


<% out.println("<form action = 'controleur' method = 'get'>");
    out.println("<input type = 'hidden' name = 'cmd' value = 'ajoutInscriptionForm' >");
    out.println("<table>");

    out.print("<tr>");

    out.println("<td>");
    out.println("nom");
    out.println("</td>");

    out.println("<td>");
    out.println("<input type='text' name='nom'>");
    out.println("</td>");

    out.print("</tr>");

    out.print("<tr>");

    out.println("<td>");
    out.println("age");
    out.println("</td>");

    out.println("<td>");
    out.println("<input type='number' name='age'>");
    out.println("</td>");

    out.println("</td>");
    out.println("<tr>");
    out.println("<td>");
    out.println("<input type = 'submit'>");
    out.println("<td>");
    out.println("</tr>");
    out.println("</table></form>");
%>

<jsp:include page="ihm/miseEnPageSPORT2.jsp"/>




