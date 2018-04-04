package cmd;

import bdd.*;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class CommandeEpreuves implements Commande
{
    private final String next;

    public CommandeEpreuves(String next)
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