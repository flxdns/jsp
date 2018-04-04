package cmd;

import bdd.DBS;
import bdd.DB_PARTICIPANT;
import beans.Participant;

import javax.servlet.http.HttpServletRequest;

public class CommandeParticipant implements Commande
{
    private final String next;

    public CommandeParticipant(String next)
    {
        this.next = next;
    }

    public String execute(HttpServletRequest paramHttpServletRequest) throws Exception
    {
        DBS dbs = DBS.getInstance();
        int idp = Integer.parseInt(paramHttpServletRequest.getParameter("idp"));

        DB_PARTICIPANT dbp = dbs.getDB_PARTICIPANT();
        Participant participant = dbp.getParticipant(idp);
        paramHttpServletRequest.setAttribute("participant", participant);

        return next;
    }
}