package cmd.participant;

import bdd.DBS;
import bdd.DB_PARTICIPANT;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CommandeParticipantsNom implements Commande {
    private final String next;

    public CommandeParticipantsNom(String next) {
        this.next = next;
    }

    public String execute(HttpServletRequest httpServletRequest) throws Exception {
        DBS dbs = DBS.getInstance();
        DB_PARTICIPANT db_epreuve = dbs.getDB_PARTICIPANT();
        ArrayList participantsByNom = db_epreuve.getParticipantsByNom();
        httpServletRequest.setAttribute("participants", participantsByNom);
        return this.next;
    }
}
