package cmd.epreuves;

import bdd.DBS;
import bdd.DB_EPREUVE;
import bdd.DB_INSCRIPTION;
import cmd.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;

public class CommandeSupprimerEpreuve implements Commande
{
	private final String next;

	public CommandeSupprimerEpreuve(String next )
	{
		this.next = next;
	}

	public String execute(HttpServletRequest paramHttpServletRequest) throws Exception
	{
		DBS dbs = DBS.getInstance();
		DB_EPREUVE dbe = dbs.getDB_EPREUVE();
		DB_INSCRIPTION dbi = dbs.getDB_INSCRIPTION();

		ArrayList alInscription = dbi.getInscriptions();
		ArrayList alEpreuve = dbe.getEpreuves();

		paramHttpServletRequest.setAttribute("epreuves", alEpreuve);
		paramHttpServletRequest.setAttribute("inscriptions", alInscription);

		return next;
	}
}