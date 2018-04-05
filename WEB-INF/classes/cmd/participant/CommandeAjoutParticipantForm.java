package cmd.participant;

import bdd.DBS;
import bdd.DB_PARTICIPANT;
import beans.Participant;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

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
		
		int i = db_participant.insertParticipant( new Participant( 0 , paramHttpServletRequest.getParameter( "nom" ) ,
		                                                           Integer.parseInt( paramHttpServletRequest.getParameter( "age" ) ) ) );
		
		ArrayList participants = db_participant.getParticipants( );
		paramHttpServletRequest.setAttribute( "participants" , participants );
		paramHttpServletRequest.removeAttribute( "nom"  );
		paramHttpServletRequest.removeAttribute( "age"  );
		paramHttpServletRequest.setAttribute( "ide", i );
		return next;
	}
}