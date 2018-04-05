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
		return next;
	}
}