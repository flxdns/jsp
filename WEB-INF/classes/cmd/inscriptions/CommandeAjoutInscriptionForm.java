package cmd.inscriptions;

import bdd.DBS;
import bdd.DB_EPREUVE;
import bdd.DB_PARTICIPANT;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;

public class CommandeAjoutInscriptionForm implements Commande
{
	private final String next;
	
	public CommandeAjoutInscriptionForm( String next )
	{
		this.next = next;
	}
	
	public String execute( HttpServletRequest paramHttpServletRequest ) throws Exception
	{
		DBS            dbs            = DBS.getInstance( );
		DB_PARTICIPANT db_participant = dbs.getDB_PARTICIPANT( );
		DB_EPREUVE     db_epreuve     = dbs.getDB_EPREUVE( );
		
		paramHttpServletRequest.setAttribute( "participants" , db_participant.getParticipants( ) );
		paramHttpServletRequest.setAttribute( "epreuves" , db_epreuve.getEpreuves( ) );
		return next;
	}
}