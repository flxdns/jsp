package cmd.epreuves;

import bdd.DBS;
import bdd.DB_EPREUVE;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CommandeEpreuvesNom implements Commande
{
	private final String next;
	
	public CommandeEpreuvesNom( String next) {
		this.next = next;
	}
	
	public String execute(HttpServletRequest httpServletRequest) throws Exception {
		DBS        dbs = DBS.getInstance();
		DB_EPREUVE db_epreuve = dbs.getDB_EPREUVE();
		ArrayList  epreuvesByNom = db_epreuve.trierEpreuvesByNom();
		httpServletRequest.setAttribute("epreuves", epreuvesByNom);
		return this.next;
	}
}
