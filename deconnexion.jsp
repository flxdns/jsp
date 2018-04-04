<%-- ======================================
		deconnexion.jsp
========================================= --%>

<%

    String titre = "DECONNEXION";

    String idSession = session.getId( );
    out.println( "Id session:" + idSession + "</br>" );
    session.invalidate( );

%>

<%@include file = "ihm/miseEnPageSPORT1.jsp" %>

<jsp:include page = "ihm/miseEnPageSPORT2.jsp" />
