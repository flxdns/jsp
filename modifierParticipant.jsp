<%@ page import="beans.Participant" %>
<%@ page import="java.util.List" %>
<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
    <jsp:param name = "titre" value = "Modifier un participant " />
</jsp:include >


<%
    List<Participant> participants = (List<Participant>) request.getAttribute("participants");
    //Si on se connecte pour la premier fois sur la page
    if ( request.getParameter("pmodify") == null )
    {
        out.println("<form action = 'controleur' method = 'get'>");
        out.println("<input type = 'hidden' name = 'cmd' value = 'modifierParticipant' >");
        out.println("<table>");

        out.print("<tr>");

        out.println("<td>");
        out.println("<select name=pmodify>\n");
        for (Participant p : participants)
            out.println("<option value = " + p.getIdp() + " >" + p.getNom() + "</option>");

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
    //Sinon on affiche la page pour modifier
    else
    {
        String nom = "";
        int age = 0;
        int idp = 0;
        //Récupération du participant actuel
        for (Participant p : participants)
            if ( p.getIdp() == Integer.parseInt(request.getParameter("pmodify")) ) {
                nom = p.getNom();
                age = p.getAge();
                idp = p.getIdp();
            }

        out.println( "<form action = 'controleur' method = 'get'>" );
        out.println( "<input type = 'hidden' name = 'cmd' value = 'modifierParticipantForm' >" );
        out.println( "<input type = 'hidden' name = 'idp' value = " + idp + ">" );
        out.println( "<table>" );

        out.print( "<tr>" );

        out.println( "<td>" );
        out.println( "nom" );
        out.println( "</td>" );

        out.println( "<td>" );
        out.println( "<input type='text' name='nom' " +  "value = '" + nom +"'required >" );
        out.println( "</td>" );

        out.print( "</tr>" );

        out.print( "<tr>" );

        out.println( "<td>" );
        out.println( "age" );
        out.println( "</td>" );

        out.println( "<td>" );
        out.println( "<input type='number' name='age' +  value = '" + age + "' required min=6 >" );
        out.println( "</td>" );

        out.println( "</td>" );
        out.println( "<tr>" );
        out.println( "<td>" );
        out.println( "<input type = 'submit'>" );
        out.println( "<td>" );
        out.println( "</tr>" );
        out.println( "</table></form>" );
    }
%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />