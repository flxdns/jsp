<%@ page import="java.util.List" %>
<%@ page import="beans.Epreuve" %>
<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
    <jsp:param name = "titre" value = "Supprimer une epreuve " />
</jsp:include >


<%

    out.println( "<form action = 'controleur' method = 'get'>" );
    out.println( "<input type = 'hidden' name = 'cmd' value = 'supprimerEpreuve' >" );

    out.print("<select name=\"menu_destination\">");

    List<Epreuve> epreuves = (List<Epreuve>) request.getAttribute("epreuves");

    for (int i=0; i<epreuves.size();i++)
        out.print("<option value=\"" + epreuves.get(i) + "\">" + epreuves.get(i).getNom() + "</option>");

    out.print("</select>");

    out.println( "<input type = 'submit' value='Supprimer'>" );
    out.println( "</form>" );

%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />