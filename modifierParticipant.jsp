<%@ page import="beans.Participant" %>
<%@ page import="java.util.List" %>
<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
    <jsp:param name = "titre" value = "Choisir un participant " />
</jsp:include >


<%
    List<Participant> participants = (List<Participant>) request.getAttribute("participants");
    out.println( "<form action = 'controleur' method = 'get'>" );
    out.println( "<input type = 'hidden' name = 'cmd' value = 'modifierParticipantForm' >" );
    out.println( "<table>" );

    out.print( "<tr>" );

    out.println( "<td>" );
    out.println( "<select id=\"participants\">\n");
    for (Participant p : participants)
        out.println("<option >"+ p.getNom() + "</option>" );

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