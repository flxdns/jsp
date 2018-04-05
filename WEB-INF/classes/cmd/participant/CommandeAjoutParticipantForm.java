package cmd.participant;

import bdd.DBS;
import bdd.DB_PARTICIPANT;
import beans.Participant;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;

public class CommandeAjoutParticipantForm implements Commande
{
	private final String next;
	
	public CommandeAjoutParticipantForm( String next )
	{
		this.next = next;
	}
	
	public String execute( HttpServletRequest paramHttpServletRequest ) throws Exception
	{
		DBS            dbs            = DBS.getInstance( );
		DB_PARTICIPANT db_participant = dbs.getDB_PARTICIPANT( );
		
		db_participant.insertParticipant( new Participant( 0 , paramHttpServletRequest.getParameter( "nom" ) ,
		                                                   Integer.parseInt( paramHttpServletRequest.getParameter( "age" ) ) ) );
		return next;
	}
}