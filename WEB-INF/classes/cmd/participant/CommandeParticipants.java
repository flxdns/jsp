package cmd.participant;

import bdd.DBS;
import bdd.DB_INSCRIPTION;
import bdd.DB_PARTICIPANT;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CommandeParticipants implements Commande {
    private final String next;

    public CommandeParticipants(String next) {
        this.next = next;
    }

    public String execute(HttpServletRequest paramHttpServletRequest) throws Exception {
        DBS dbs = DBS.getInstance();
        DB_PARTICIPANT db_participant = dbs.getDB_PARTICIPANT();
        DB_INSCRIPTION db_inscription = dbs.getDB_INSCRIPTION();
        try {
            System.out.println("Suppression de l'indice" + Integer.parseInt(paramHttpServletRequest.getParameter("numparticipant") ));
            paramHttpServletRequest.getParameter("numparticipant");
            db_participant.deleteParticipant(Integer.parseInt(paramHttpServletRequest.getParameter("numparticipant")));
        }
        catch(Exception ignored){ }
        ArrayList alInscription = db_inscription.getInscriptions();
        ArrayList alParticipant = db_participant.getParticipants();

        paramHttpServletRequest.setAttribute("participants", alParticipant);
        paramHttpServletRequest.setAttribute("inscriptions", alInscription);

        return next;
    }
}