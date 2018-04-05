<%@ page import="beans.Epreuve" %>
<%@ page import="java.util.List" %>
<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
    <jsp:param name = "titre" value = "Choisir une epreuve :" />
</jsp:include >


<%
    List<Epreuve> epreuves = (List<Epreuve>) request.getAttribute("epreuves");
    out.println( "<form action = 'controleur' method = 'get'>" );
    out.println( "<input type = 'hidden' name = 'cmd' value = 'CommandeModifierEpreuveForm' >" );
    out.println( "<table>" );

    out.print( "<tr>" );

    out.println( "<td>" );
    out.println( "<select id=\"participants\">\n");
    for (Epreuve e : epreuves)
        out.println("<option >"+ e.getNom() + "</option>" );

    out.println("</select>" );
    out.println( "</td>" );

    out.println( "</tr>" );

    out.println("<tr>");
    out.println("<td>");
    out.println("<input type = submit >");
    out.println("</td>");
    out.println("</tr>");
    out.println( "</table></form>" );
%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />