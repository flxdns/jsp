<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
	<jsp:param name = "titre" value = "Ajout d'un participant" />
</jsp:include >


<% out.println( "<form>" );
    out.println( "<table>" );
    out.println( "<tr>" );
    out.println( "<td>" );
    out.println( "idp" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println( "<input type=\"text\" name=\"idp\">" );
    out.println( "</td>" );
    out.print( "</tr>" );

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
    out.println( "age" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println( "<input type=\"text\" name=\"age\">" );
    out.println( "</td>" );

    out.println( "</td>" );
    out.println( "</table></form>" );
%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />




