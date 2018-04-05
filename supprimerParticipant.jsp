<%@ page import="beans.Participant" %>
<%@ page import="java.util.List" %>
<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
    <jsp:param name = "titre" value = "Supprimer un participant " />
</jsp:include >


<%

    out.println( "<form action = 'controleur' method = 'get'>" );
    out.println( "<input type = 'hidden' name = 'cmd' value = 'delParticipant' >" );

    out.print("<select name=\"numparticipant\">");

    List<Participant> participants = (List<Participant>)request.getAttribute("participants");

    for (Participant p : participants )
        out.print("<option value=\"" + p.getIdp() + "\">" + p.getNom() + "</option>");

    out.print("</select>");

    out.println( "<input type = 'submit' value='Supprimer'>" );
    out.println( "</form>" );

%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />