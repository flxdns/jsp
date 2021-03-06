<%-- ======================================
		participants.jsp
========================================= --%>

<%@ page import = "beans.Inscription,java.util.List" %>

<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
    <jsp:param name = "titre" value = "LISTE DES INSCRIPTIONS" />
</jsp:include >

<% // ==============  CORPS =================================================
    List< Inscription > inscriptions = ( List< Inscription > ) request.getAttribute( "inscriptions" );
    String coul = "lignePaire";
    int idp = 0;
    int ide = 0;
    String categTarif = null;

    if( inscriptions != null ) {
        if( ( request.getParameter( "idp" ) ) != null ) idp = Integer.parseInt( request.getParameter( "idp" ) );
        if( ( request.getParameter( "ide" ) ) != null ) ide = Integer.parseInt( request.getParameter( "ide" ) );
        if( ( request.getParameter( "categtarif" ) ) != null ) categTarif = request.getParameter( "categtarif" );

        String href1 = "controleur?cmd=inscriptions&ide=" + idp;
        String href2 = "controleur?cmd=inscriptionsIde&ide=" + idp;
        String href3 = "controleur?cmd=inscriptionsCategTarif&idp=" + idp;
        out.println( "<table>" );
        out.println( "<tr class=\"enteteTableau\">" );
        out.println( "<th><a href=\"" + href1 + "\">idp</a></th>" );
        out.println( "<th><a href=\"" + href2 + "\">ide</a></th>" );
        out.println( "<th><a href=\"" + href3 + "\">Categorie Tarif</a></th>" );
        out.println( "</tr>" );
        for( Inscription i : inscriptions ) {
            coul = ( coul.equals( "lignePaire" ) ) ? "ligneImpaire" : "lignePaire";
            if( idp == i.getIdp( ) && ide == 0 ||
                idp == i.getIdp( ) && ide == i.getIde( ) && i.getCategTarif( ).equals( categTarif ) )
            {
                coul = "ligneId";
                out.println( "<tr class=\"" + coul + "\">" );
            }
            else out.println( "<tr class=\"" + coul + "\">" );
            String hrefIdp = "controleur?cmd=participants&idp=" + i.getIdp( );
            String hrefIde = "controleur?cmd=epreuves&ide=" + i.getIde( );
            out.println( "<td><a href=" + hrefIdp + ">" + i.getIdp( ) + "</a></td>" );
            out.println( "<td><a href=" + hrefIde + ">" + i.getIde( ) + "</a></td>" );
            out.println( "<td>" + i.getCategTarif( ) + "</td>" );
            out.println( "</tr>" );
        }
    }
    out.println( "</table>" );
    // ==============  CORPS =================================================
%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />
