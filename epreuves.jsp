<%-- ======================================
		epreuves.jsp
========================================= --%>

<%@ page import = "beans.Epreuve,java.sql.Date" %><%@ page import = "java.util.List" %>

<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
    <jsp:param name = "titre" value = "LISTE DES EPREUVES" />
</jsp:include >

<% // ==============  CORPS =================================================
    List< Epreuve > epreuves = ( List< Epreuve > ) request.getAttribute( "epreuves" );
    Integer ide = 0;
    String nom = null;
    String categ = null;
    Date datep = null;
    double tarifclub = 0;
    double tarifnonclub = 0;

    String coul = "lignePaire";

    if( epreuves != null ) {
        if( ( request.getParameter( "ide" ) ) != null ) ide = Integer.parseInt( request.getParameter( "ide" ) );

        if( ( request.getParameter( "nom" ) ) != null ) nom = request.getParameter( "nom" );
        if( ( request.getParameter( "categ" ) ) != null ) categ = request.getParameter( "categ" );
        if( ( request.getParameter( "datep" ) ) != null ) datep = Date.valueOf( request.getParameter( "datep" ) );
        if( ( request.getParameter( "tarifclub" ) ) != null )
            tarifclub = Double.parseDouble( request.getParameter( "tarifclub" ) );
        if( ( request.getParameter( "tarifnonclub" ) ) != null )
            tarifnonclub = Double.parseDouble( request.getParameter( "tarifnonclub" ) );

        out.println( "<table>" );
        String href1 = "controleur?cmd=epreuves&ide=" + ide;
        String href2 = "controleur?cmd=epreuvesNom&ide=" + ide;
        String href3 = "controleur?cmd=epreuvesCateg&ide=" + ide;
        String href4 = "controleur?cmd=epreuvesDateP&ide=" + ide;
        String href5 = "controleur?cmd=epreuvesTarifClub&ide=" + ide;
        String href6 = "controleur?cmd=epreuvesTarifNonClub&ide=" + ide;

        out.println( "<tr class=\"enteteTableau\">" );
        out.println( "<th><a href=\"" + href1 + "\">ide</a></th>" );
        out.println( "<th><a href=\"" + href2 + "\">nom</a></th>" );
        out.println( "<th><a href=\"" + href3 + "\">categ</a></th>" );
        out.println( "<th><a href=\"" + href4 + "\">datep</a></th>" );
        out.println( "<th><a href=\"" + href5 + "\">tarifclub</a></th>" );
        out.println( "<th><a href=\"" + href6 + "\">tarifnonclub</a></th>" );
        out.println( "</tr>" );

        for( Epreuve e : epreuves ) {
            coul = ( coul.equals( "lignePaire" ) ) ? "ligneImpaire" : "lignePaire";

            if( ide == e.getIde( ) || (
                e.getNom( ).equals( nom ) && e.getCateg( ).equals( categ ) && e.getDatep( ).equals( datep ) &&
                e.getTarifClub( ) == tarifclub && e.getTarifNonClub( ) == tarifnonclub ))
            {
                coul = "ligneId";
                out.println( "<tr class=\"" + coul + "\">" );
            }
            else out.println( "<tr class=\"" + coul + "\">" );

            String href = "controleur?cmd=inscriptionEpreuves&ide=" + e.getIde( );
            out.println( "<td><a href=" + href + ">" + e.getIde( ) + "</a></td>" );
            out.println( "<td>" + e.getNom( ) + "</td>" );
            out.println( "<td>" + e.getCateg( ) + "</td>" );
            out.println( "<td>" + e.getDatep( ) + "</td>" );
            out.println( "<td>" + e.getTarifClub( ) + "</td>" );
            out.println( "<td>" + e.getTarifNonClub( ) + "</td>" );
            out.println( "</tr>" );
        }
    }
    out.println( "</table>" );
    // ==============  CORPS =================================================
%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />
