<%@ page import="beans.Epreuve" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
    <jsp:param name = "titre" value = "Modifier une epreuve :" />
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
        String [] tabCateg = {"enfant","ado","adulte"};
        String nom = "";
        String categ = "";
        Date  date = null;
        double tarifClub = 0, tarifNonClub = 0;
        //Récupération de l'epreuve actuelle
        for ( Epreuve e : epreuves)
            if ( e.getIde() == Integer.parseInt(request.getParameter("emodify")) ) {
                nom = e.getNom();
                categ = e.getCateg();
                tarifClub = e.getTarifClub();
                tarifNonClub = e.getTarifNonClub();
                date = e.getDatep();
            }

        out.println("<form action = 'controleur' method = \"get\">");
        out.println("<input type = \"hidden\" name = \"cmd\" value = \"modifierEpreuveForm\" >");
        out.println("<table>");
        out.print("<tr>");

        out.println("<td>");
        out.println("nom");
        out.println("</td>");

        out.println("<td>");
        out.println("<input type=\"text\" name=\"nom\" value = '" + nom + "' required>");
        out.println("</td>");

        out.print("</tr>");

        out.print("<tr>");

        out.println("<td>");
        out.println("categ");
        out.println("</td>");

        out.println("<td>");

        out.println("<select name=\"categ\" required>\n");
        for ( String c : tabCateg ) {
            out.print("<option value='" + c + "'");
            if ( c.equals(categ) ) out.print("selected='selected'");
            out.print("> " + c + "</option>");
        }
        out.println("</td>");

        out.println("</tr>");

        out.print("<tr>");

        out.println("<td>");
        out.println("datep");
        out.println("</td>");

        out.println("<td>");
        out.println("<input type=\"date\" name=\"datep\" value = '" + Date.valueOf(String.valueOf(date)) + "'required>");
        out.println("</td>");

        out.println("</tr>");

        out.println("<tr>");

        out.println("<td>");
        out.println("tarifclub");
        out.println("</td>");

        out.println("<td>");
        out.println("<input type=\"number\" name=\"tarifclub\" min='0' value = '" +  tarifClub + "'required>");
        out.println("</td>");

        out.println("</tr>");

        out.println("<tr>");

        out.println("<td>");
        out.println("tarifnonclub");
        out.println("</td>");

        out.println("<td>");
        out.println("<input type=\"number\" name=\"tarifnonclub\" min = 1 value = '" + tarifNonClub + "'required>");
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