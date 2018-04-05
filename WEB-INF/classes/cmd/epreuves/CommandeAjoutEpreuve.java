package cmd.epreuves;

import bdd.DBS;
import bdd.DB_EPREUVE;
import beans.Epreuve;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class CommandeAjoutEpreuve implements Commande
{
    private final String next;
    
    public CommandeAjoutEpreuve( String next )
    {
        this.next = next;
    }
    
    public String execute( HttpServletRequest paramHttpServletRequest ) throws Exception
    {
        DBS        dbs            = DBS.getInstance( );
        DB_EPREUVE db_participant = dbs.getDB_EPREUVE( );
        
        db_participant.insertEpreuve(
                new Epreuve( 0 , paramHttpServletRequest.getParameter( "nom" ) , paramHttpServletRequest.getParameter( "categ" ) ,
                             Date.valueOf( paramHttpServletRequest.getParameter( "datep" ) ) ,
                             Integer.parseInt( paramHttpServletRequest.getParameter( "tarifclub" ) ) , Integer.parseInt( "tarifnonclub" ) ) );
        return next;
    }
}
