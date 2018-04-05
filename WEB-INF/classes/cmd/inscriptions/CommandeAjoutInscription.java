package cmd.inscriptions;

import bdd.DBS;
import bdd.DB_INSCRIPTION;
import beans.Inscription;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CommandeAjoutInscription implements Commande
{
	private final String next;
	
	public CommandeAjoutInscription( String next )
	{
		this.next = next;
	}
	
	public String execute( HttpServletRequest paramHttpServletRequest ) throws Exception
	{
		DBS            dbs            = DBS.getInstance( );
		DB_INSCRIPTION db_inscription = dbs.getDB_INSCRIPTION( );
		
		db_inscription.insertInscription( new Inscription( Integer.parseInt( paramHttpServletRequest.getParameter( "idp" ) ) ,
		                                                   Integer.parseInt( paramHttpServletRequest.getParameter( "categtarif" ) ) ,
		                                                   paramHttpServletRequest.getParameter( "age" ) ) );
		
		ArrayList inscriptions = db_inscription.getInscriptions( );
		paramHttpServletRequest.setAttribute( "inscriptions" , inscriptions );
		return next;
	}
}