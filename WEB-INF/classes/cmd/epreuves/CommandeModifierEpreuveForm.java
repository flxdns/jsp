package cmd.epreuves;

import bdd.DBS;
import bdd.DB_EPREUVE;
import beans.Epreuve;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;

public class CommandeModifierEpreuveForm implements Commande
{
	private final String next;

	public CommandeModifierEpreuveForm(String next )
	{
		this.next = next;
	}
	
	public String execute( HttpServletRequest paramHttpServletRequest ) throws Exception
	{
		DBS        dbs            = DBS.getInstance( );
		DB_EPREUVE db_epreuve = dbs.getDB_EPREUVE( );

		db_epreuve.updateEpreuve(
				new Epreuve( Integer.parseInt(paramHttpServletRequest.getParameter( "ide" )) , paramHttpServletRequest.getParameter( "nom" ) , paramHttpServletRequest.getParameter( "categ" ) ,
						Date.valueOf( paramHttpServletRequest.getParameter( "datep" ) ) ,
						Double.parseDouble( paramHttpServletRequest.getParameter( "tarifclub" ) ) ,
						Double.parseDouble( paramHttpServletRequest.getParameter( "tarifnonclub" ) ) ) );

		ArrayList epreuves = db_epreuve.getEpreuves( );
		paramHttpServletRequest.setAttribute( "epreuves" , epreuves );
		return next;
	}
}