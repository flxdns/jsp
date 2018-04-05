<%@ page import = "beans.Participant,java.util.List" %>

<jsp:include page = "ihm/miseEnPageSPORT1.jsp" >
    <jsp:param name = "titre" value = "LISTE DES PARTICIPANTS" />
</jsp:include >

<%
    List< Participant > participants = ( List< Participant > ) request.getAttribute( "participants" );
    String coul = "lignePaire";
    Integer idp = 0;
    String nom = null;
    int age = 0;
    if( participants != null ) {
        if( ( request.getParameter( "idp" ) ) != null ) idp = Integer.parseInt( request.getParameter( "idp" ) );
        if( ( request.getParameter( "nom" ) ) != null ) nom = request.getParameter( "nom" );

        if( ( request.getParameter( "age" ) ) != null ) age = Integer.parseInt( request.getParameter( "age" ) );

        String href1 = "controleur?cmd=participants";
        String href2 = "controleur?cmd=participantsNom&idp=" + idp;
        String href3 = "controleur?cmd=participantsAge&idp=" + idp;
        out.println( "<table>" );
        out.println( "<tr class=\"enteteTableau\">" );
        out.println( "<th><a href=\"" + href1 + "\">idp</a></th>" );
        out.println( "<th><a href=\"" + href2 + "\">nom</a></th>" );
        out.println( "<th><a href=\"" + href3 + "\">age</a></th>" );
        out.println( "</tr>" );
        for( Participant p : participants ) {
            coul = ( coul.equals( "lignePaire" ) ) ? "ligneImpaire" : "lignePaire";
            if( idp == p.getIdp( ) || p.getNom( ).equals( nom ) && p.getAge( ) == age ) {
                coul = "ligneId";
                out.println( "<tr class=\"" + coul + "\">" );
            }
            else out.println( "<tr class=\"" + coul + "\">" );
            String href = "controleur?cmd=inscription&idp=" + p.getIdp( );
            out.println( "<td><a href=" + href + ">" + p.getIdp( ) + "</a></td>" );
            out.println( "<td>" + p.getNom( ) + "</td>" );
            out.println( "<td>" + p.getAge( ) + "</td>" );
            out.println( "</tr>" );
        }
    }
    out.println( "</table>" );
%>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />
