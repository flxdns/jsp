package cmd;

import bdd.*;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class CommandeInscriptions implements Commande
{
    private final String next;

    public CommandeInscriptions(String next)
    {
        this.next = next;
    }

    public String execute(HttpServletRequest paramHttpServletRequest) throws Exception
    {
        DBS dbs = DBS.getInstance();
        DB_INSCRIPTION dbi = dbs.getDB_INSCRIPTION();
        DB_EPREUVE dbe = dbs.getDB_EPREUVE();
        DB_PARTICIPANT dbp = dbs.getDB_PARTICIPANT();

        ArrayList alInscriptions = dbi.getInscriptions();
        ArrayList alParticipants = dbp.getParticipants();
        ArrayList alEpreuves = dbe.getEpreuves();

        paramHttpServletRequest.setAttribute("inscriptions", alInscriptions);
        paramHttpServletRequest.setAttribute("participants", alParticipants);
        paramHttpServletRequest.setAttribute("epreuves", alEpreuves);

        return next;
    }
}