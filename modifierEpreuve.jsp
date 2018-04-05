<%@ page import="beans.Epreuve" %>
<%@ page import="java.util.List" %>
<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
    <jsp:param name = "titre" value = "Choisir une epreuve :" />
</jsp:include >


<%
    List<Epreuve> epreuves = (List<Epreuve>) request.getAttribute("epreuves");
    if ( request.getParameter("emodify") == null )
    {
        out.println("<form action = 'controleur' method = 'get'>");
        out.println("<input type = 'hidden' name = 'cmd' value = 'modifierEpreuve' >");
        out.println("<table>");

        out.print("<tr>");

        out.println("<td>");
        out.println("<select name=emodify>");
        for (Epreuve e : epreuves)
            out.println("<option value = " + e.getIde() + ">" + e.getNom() + "</option>");

        out.println("</select>");
        out.println("</td>");

        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println("<input type = submit value=Modifier>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table></form>");
    }
    else
    {
        out.println("<form action = 'controleur' method = \"get\">");
        out.println("<input type = \"hidden\" name = \"cmd\" value = \"ajouterEpreuveForm\" >");
        out.println("<table>");
        out.print("<tr>");

        out.println("<td>");
        out.println("nom");
        out.println("</td>");

        out.println("<td>");
        out.println("<input type=\"text\" name=\"nom\" required>");
        out.println("</td>");

        out.print("</tr>");

        out.print("<tr>");

        out.println("<td>");
        out.println("categ");
        out.println("</td>");

        out.println("<td>");
        out.println("<select name=\"categ\" required>\n" + "\n" + "           <option value=\"enfant\">enfant</option>\n" + "\n" +
                        "           <option value=\"ado\">ado</option>\n" + " <option value=\"adulte\">adulte</option>\n" + "\n" + "       </select>");
        out.println("</td>");

        out.println("</tr>");

        out.print("<tr>");

        out.println("<td>");
        out.println("datep");
        out.println("</td>");

        out.println("<td>");
        out.println("<input type=\"date\" name=\"datep\" required>");
        out.println("</td>");

        out.println("</tr>");

        out.println("<tr>");

        out.println("<td>");
        out.println("tarifclub");
        out.println("</td>");

        out.println("<td>");
        out.println("<input type=\"number\" name=\"tarifclub\" min='0' required>");
        out.println("</td>");

        out.println("</tr>");

        out.println("<tr>");

        out.println("<td>");
        out.println("tarifnonclub");
        out.println("</td>");

        out.println("<td>");
        out.println("<input type=\"number\" name=\"tarifnonclub\" required>");
        out.println("</td>");

        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println("<input type = submit>");
        out.println("<td>");
        out.println("</tr>");

        out.println("</table></form>");
    }
%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />