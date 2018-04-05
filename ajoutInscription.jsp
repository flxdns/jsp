<%@ page import = "beans.Epreuve" %><%@ page import = "beans.Participant" %><%@ page import = "java.util.List" %><jsp:include
        page = "ihm/miseEnPageSPORT1.jsp" >
    <jsp:param name = "titre" value = "Ajouter une inscription" />
</jsp:include >


<% out.println( "<form action = 'controleur' method = 'get'>" );
    out.println( "<input type = 'hidden' name = 'cmd' value = 'ajouterInscriptionForm' >" );
    out.println( "<table>" );

    out.print( "<tr>" );

    out.println( "<td>" );
    out.println( "idp" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.print( "<select name=\"idp\">" );

    List< Participant > participants = ( List< Participant > ) request.getAttribute( "participants" );

    for( Participant p : participants )
        out.print( "<option value=\"" + p.getIdp( ) + "\">" + p.getIdp( ) + " - " + p.getNom( ) + "</option>" );

    out.print( "</select>" );
    out.println( "</td>" );

    out.print( "</tr>" );

    out.print( "<tr>" );

    out.println( "<td>" );
    out.println( "ide" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.print( "<select name=\"ide\">" );

    List< Epreuve > epreuves = ( List< Epreuve > ) request.getAttribute( "epreuves" );

    for( Epreuve e : epreuves )
        out.print( "<option value=\"" + e.getIde( ) + "\">" + e.getIde( ) + " - " + e.getNom( ) + "</option>" );

    out.print( "</select>" );
    out.println( "</td>" );

    out.println( "</td>" );
    out.println( "</tr>" );
    out.print( "<tr>" );

    out.println( "<td>" );
    out.println( "categorie de tarif" );
    out.println( "</td>" );

    out.println( "<td>" );
    out.println(
            "<select name=\"categtarif\" required>\n" + "\n" + "           <option value=\"club\">Club</option>\n" +
            "\n" + "           <option value=\"exterieur\">Exterieur</option>\n" + "\n" + "       </select>" );
    out.println( "</td>" );
    out.println( "</td>" );
    out.println( "</tr>" );
    out.println( "<tr>" );
    out.println( "<td>" );
    out.println( "<input type = 'submit'>" );
    out.println( "<td>" );
    out.println( "</tr>" );
    out.println( "</table></form>" );
%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />




