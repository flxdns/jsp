<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
	<jsp:param name = "titre" value = "Ajout d'une epreuve" />
</jsp:include >


<% out.println( "<action = 'controleur' method = \"get\">" );
    out.println("<input type = \"hidden\" name = \"cmd\" value = \"ajoutEpreuve\" >");
	out.println( "<table>" );
    out.print( "<tr>" );

    out.println( "<td>" );
    out.println( "nom" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println( "<input type=\"text\" name=\"nom\">" );
    out.println( "</td>" );

    out.print( "</tr>" );

    out.print( "<tr>" );

    out.println( "<td>" );
    out.println( "categ" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println( "<input type=\"text\" name=\"categ\">" );
    out.println( "</td>" );

    out.println("</tr>");


    out.print( "<tr>" );

    out.println( "<td>" );
    out.println( "datep" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println( "<input type=\"text\" name=\"datep\">" );
    out.println( "</td>" );

    out.println("</tr>");


    out.println( "<tr>" );

    out.println( "<td>" );
    out.println( "tarifclub" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println( "<input type=\"text\" name=\"tarifclub\">" );
    out.println( "</td>" );

    out.println("</tr>");

    out.println( "<tr>" );

    out.println( "<td>" );
    out.println( "tarifnonclub" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println( "<input type=\"text\" name=\"tarifnonclub\">" );
    out.println( "</td>" );

    out.println("</tr>");

    out.println("<tr>");
	out.println("<td>");
	out.println("<input type = submit>");
	out.println("<td>");
    out.println("</tr>");


    out.println( "</table></form>" );
%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />



