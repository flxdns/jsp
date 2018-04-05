package cmd.epreuves;

import bdd.DBS;
import bdd.DB_EPREUVE;
import bdd.DB_INSCRIPTION;
import beans.Epreuve;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;

public class CommandeAjoutEpreuveForm implements Commande
{
	private final String next;
	
	public CommandeAjoutEpreuveForm( String next )
	{
		this.next = next;
	}
	
	public String execute( HttpServletRequest paramHttpServletRequest ) throws Exception
	{
		DBS            dbs            = DBS.getInstance( );
		DB_EPREUVE     db_epreuve     = dbs.getDB_EPREUVE( );
		
		//		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
		//		Date             parsed = ( Date ) format.parse( paramHttpServletRequest.getParameter( "datep" ) );
		
		int i = db_epreuve.insertEpreuve(
				new Epreuve( 0 , paramHttpServletRequest.getParameter( "nom" ) , paramHttpServletRequest.getParameter( "categ" ) ,
				             Date.valueOf( paramHttpServletRequest.getParameter( "datep" ) ) ,
				             Integer.parseInt( paramHttpServletRequest.getParameter( "tarifclub" ) ) , Integer.parseInt( "tarifnonclub" ) ) );
		paramHttpServletRequest.setAttribute( "ide" , i );
		
		
		ArrayList epreuveArrayList = db_epreuve.getEpreuves( );
		paramHttpServletRequest.setAttribute( "epreuves" , epreuveArrayList );
		return next;
	}
}
