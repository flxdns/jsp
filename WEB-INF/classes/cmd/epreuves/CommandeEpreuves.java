package cmd.epreuves;

import bdd.*;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CommandeEpreuves implements Commande
{
	private final String next;
	
	public CommandeEpreuves( String next )
	{
		this.next = next;
	}
	
	public String execute( HttpServletRequest paramHttpServletRequest ) throws Exception
	{
		DBS            dbs = DBS.getInstance( );
		DB_EPREUVE     dbe = dbs.getDB_EPREUVE( );

		try {
			System.out.println("Suppression de l'indice" + Integer.parseInt(paramHttpServletRequest.getParameter("numepreuve") ));
			paramHttpServletRequest.getParameter("numepreuve");
			dbe.deleteEpreuve(Integer.parseInt(paramHttpServletRequest.getParameter("numepreuve")));
		}
		catch(Exception ignored){ }

		ArrayList alEpreuve     = dbe.getEpreuves( );
		
		paramHttpServletRequest.setAttribute( "epreuves" , alEpreuve );
		
		return next;
	}
}