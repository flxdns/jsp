package cmd.participant;

import bdd.DBS;
import bdd.DB_PARTICIPANT;
import beans.Participant;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CommandeModifierParticipantForm implements Commande
{
	private final String next;

	public CommandeModifierParticipantForm(String next )
	{
		this.next = next;
	}
	
	public String execute( HttpServletRequest paramHttpServletRequest ) throws Exception
	{
		DBS            dbs            = DBS.getInstance( );
		DB_PARTICIPANT db_participant = dbs.getDB_PARTICIPANT( );
		
		db_participant.updateParticipant( new Participant( Integer.parseInt(paramHttpServletRequest.getParameter( "idp" )) , paramHttpServletRequest.getParameter( "nom" ) ,
		                                                           Integer.parseInt( paramHttpServletRequest.getParameter( "age" ) ) ) );
        System.out.println("Modification du participant");
		ArrayList participants = db_participant.getParticipants( );
		paramHttpServletRequest.setAttribute( "participants" , participants );
		return next;
	}
}