package cmd.epreuves;
import bdd.DBS;
import bdd.DB_EPREUVE;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CommandeEpreuvesCateg
{
	private final String next;
	
	public CommandeEpreuvesCateg( String next )
	{
		this.next = next;
	}
	
	public String execute( HttpServletRequest httpServletRequest ) throws Exception
	{
		DBS        dbs           = DBS.getInstance( );
		DB_EPREUVE db_epreuve    = dbs.getDB_EPREUVE( );
		ArrayList  epreuvesByCateg = db_epreuve.trierEpreuvesByCateg( );
		httpServletRequest.setAttribute( "epreuves" , epreuvesByCateg );
		return this.next;
	}
}
