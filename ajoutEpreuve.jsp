<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
	<jsp:param name = "titre" value = "Ajout d'une epreuve" />
</jsp:include >


<% out.println( "<form action = 'controleur' method = \"get\">" );
    out.println( "<input type = \"hidden\" name = \"cmd\" value = \"ajoutEpreuveForm\" >" );
    out.println( "<table>" );
    out.print( "<tr>" );

    out.println( "<td>" );
    out.println( "nom" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println( "<input type=\"text\" name=\"nom\" requierd>" );
    out.println( "</td>" );

    out.print( "</tr>" );

    out.print( "<tr>" );

    out.println( "<td>" );
    out.println( "categ" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println(
            "<select name=\"categ\" requierd>\n" + "\n" + "           <option value=\"enfant\">enfant</option>\n" + "\n" +
            "           <option value=\"ado\">ado</option>\n" + " <option value=\"adulte\">adulte</option>\n" + "\n" + "       </select>" );
    out.println( "</td>" );

    out.println( "</tr>" );

    out.print( "<tr>" );

    out.println( "<td>" );
    out.println( "datep" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println( "<input type=\"date\" name=\"datep\" requierd>" );
    out.println( "</td>" );

    out.println( "</tr>" );

    out.println( "<tr>" );

    out.println( "<td>" );
    out.println( "tarifclub" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println( "<input type=\"number\" name=\"tarifclub\" min='0' requierd>" );
    out.println( "</td>" );

    out.println( "</tr>" );

    out.println( "<tr>" );

    out.println( "<td>" );
    out.println( "tarifnonclub" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println( "<input type=\"number\" name=\"tarifnonclub\" requierd>" );
    out.println( "</td>" );

    out.println( "</tr>" );

    out.println( "<tr>" );
    out.println( "<td>" );
    out.println( "<input type = submit>" );
    out.println( "<td>" );
    out.println( "</tr>" );

    out.println( "</table></form>" );
%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />




