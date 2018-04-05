<%@ page import="beans.Participant" %>
<%@ page import="java.util.List" %>
<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
    <jsp:param name = "titre" value = "Supprimer un participant " />
</jsp:include >


<%

    out.println( "<form action = 'controleur' method = 'get'>" );
    out.println( "<input type = 'hidden' name = 'cmd' value = 'supprimerParticipant' >" );

    out.print("<select name=\"menu_destination\">");

    List<Participant> participants = (List<Participant>)request.getAttribute("epreuves");

    for (int i=0; i<participants.size();i++)
        out.print("<option value=\"" + participants.get(i) + "\">" + participants.get(i).getNom() + "</option>");

    out.print("</select>");

    out.println( "<input type = 'submit'>" );
    out.println( "</form>" );

%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />