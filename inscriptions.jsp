<%-- ======================================
		participants.jsp
========================================= --%>

<%@ page import = "beans.Participant,java.util.List" %><%@ page import = "beans.Inscription" %>

<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
	<jsp:param name = "titre" value = "LISTE DES INSCRIPTIONS" />
</jsp:include >

<% // ==============  CORPS =================================================
    List< Inscription > inscriptions = ( List< Inscription> ) request.getAttribute( "inscriptions" );
    String coul = "lignePaire";
    Integer idp = 0;
    if( inscriptions != null ) {
        if( ( request.getParameter( "idp" ) ) != null ) idp = Integer.parseInt( request.getParameter( "idp" ) );

        String href1 = "controleur?cmd=inscriptions";
        String href2 = "controleur?cmd=inscriptionsIde&idp=" + idp;
        String href3 = "controleur?cmd=inscriptionsCategTarif&idp=" + idp;
        out.println( "<table>" );
        out.println( "<tr class=\"enteteTableau\">" );
        out.println( "<th><a href=\"" + href1 + "\">idp</a></th>" );
        out.println( "<th><a href=\"" + href2 + "\">ide</a></th>" );
        out.println( "<th><a href=\"" + href3 + "\">Categorie Tarif</a></th>" );
        out.println( "</tr>" );
        for( Inscription i : inscriptions ) {
            coul = ( coul.equals( "lignePaire" ) ) ? "ligneImpaire" : "lignePaire";
            if( idp == i.getIdp( ) ) {
                coul = "ligneId";
                out.println( "<tr class=\"" + coul + "\">" );
            }
            else out.println( "<tr class=\"" + coul + "\">" );
            String hrefIdp = "controleur?cmd=participants&idp=" + i.getIdp( );
            String hrefIde = "controleur?cmd=epreuve&ide=" + i.getIde( );
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
