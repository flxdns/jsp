package cmd.epreuves;
import bdd.DBS;
import bdd.DB_EPREUVE;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CommandeEpreuvesTarifClub
{
	private final String next;
	
	public CommandeEpreuvesTarifClub( String next )
	{
		this.next = next;
	}
	
	public String execute( HttpServletRequest httpServletRequest ) throws Exception
	{
		DBS        dbs                 = DBS.getInstance( );
		DB_EPREUVE db_epreuve          = dbs.getDB_EPREUVE( );
		ArrayList  epreuvesByTarifClub = db_epreuve.trierEpreuvesByTarifClub( );
		httpServletRequest.setAttribute( "epreuves" , epreuvesByTarifClub );
		return this.next;
	}
}
