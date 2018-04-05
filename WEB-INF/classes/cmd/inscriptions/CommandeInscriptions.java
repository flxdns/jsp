package cmd.inscriptions;

import bdd.DBS;
import bdd.DB_EPREUVE;
import bdd.DB_INSCRIPTION;
import bdd.DB_PARTICIPANT;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CommandeInscriptions implements Commande {
    private final String next;

    public CommandeInscriptions(String next) {
        this.next = next;
    }

    public String execute(HttpServletRequest paramHttpServletRequest) throws Exception {
        DBS dbs = DBS.getInstance();
        DB_INSCRIPTION db_inscription = dbs.getDB_INSCRIPTION();
        DB_EPREUVE db_epreuve = dbs.getDB_EPREUVE();
        DB_PARTICIPANT db_participant = dbs.getDB_PARTICIPANT();

        ArrayList alInscriptions = db_inscription.getInscriptions();
        ArrayList alParticipants = db_participant.getParticipants();
        ArrayList alEpreuves = db_epreuve.getEpreuves();

        paramHttpServletRequest.setAttribute("inscriptions", alInscriptions);
        paramHttpServletRequest.setAttribute("participants", alParticipants);
        paramHttpServletRequest.setAttribute("epreuves", alEpreuves);

        return next;
    }
}