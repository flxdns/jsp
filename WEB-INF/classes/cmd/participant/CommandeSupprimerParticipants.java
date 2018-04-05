package cmd.participant;

import javax.servlet.http.*;
import java.util.List;

import bdd.DBS;
import bdd.DB_PARTICIPANT;
import beans.*;
import cmd.Commande;

class CommandeSupprimerParticipants implements Commande {

    private final String next;

    public CommandeSupprimerParticipants(String next) {
        this.next = next;
    }

    public String execute(HttpServletRequest req) throws Exception {
        DBS db = DBS.getInstance();
        DB_PARTICIPANT db_participant = db.getDB_PARTICIPANT();

        db_participant.deleteParticipant(Integer.parseInt(req.getParameter("idp")));
        List<Participant> participants = db_participant.getParticipants();

        req.setAttribute("participants", participants);

        return next;
    }
}
