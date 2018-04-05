package cmd.epreuves;

import bdd.DBS;
import bdd.DB_EPREUVE;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CommandeEpreuvesTarifNonClub implements Commande
{
	private final String next;
	
	public CommandeEpreuvesTarifNonClub( String next )
	{
		this.next = next;
	}
	
	public String execute( HttpServletRequest httpServletRequest ) throws Exception
	{
		DBS        dbs                    = DBS.getInstance( );
		DB_EPREUVE db_epreuve             = dbs.getDB_EPREUVE( );
		ArrayList  epreuvesByTarifNonClub = db_epreuve.trierEpreuvesByTarifNonClub( );
		httpServletRequest.setAttribute( "epreuves" , epreuvesByTarifNonClub );
		return this.next;
	}
}
