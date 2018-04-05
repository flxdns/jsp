package cmd.participant;
import bdd.DBS;
import bdd.DB_PARTICIPANT;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CommandeParticipantsAge implements Commande
{
	private final String next;
	
	public CommandeParticipantsAge( String next )
	{
		this.next = next;
	}
	
	public String execute( HttpServletRequest httpServletRequest ) throws Exception
	{
		DBS            dbs               = DBS.getInstance( );
		DB_PARTICIPANT db_participant    = dbs.getDB_PARTICIPANT( );
		ArrayList      participantsByAge = db_participant.getParticipantsByAge( );
		httpServletRequest.setAttribute( "participants" , participantsByAge );
		return this.next;
	}
}
