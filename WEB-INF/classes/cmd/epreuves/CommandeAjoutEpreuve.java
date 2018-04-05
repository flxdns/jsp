package cmd.epreuves;

import bdd.DBS;
import bdd.DB_EPREUVE;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;

public class CommandeAjoutEpreuve implements Commande {
    private final String next;

    public CommandeAjoutEpreuve(String next) {
        this.next = next;
    }

    public String execute(HttpServletRequest paramHttpServletRequest) throws Exception {
        DBS dbs = DBS.getInstance();
        DB_EPREUVE db_participant = dbs.getDB_EPREUVE();

        //        int idp paramHttpServletRequest.getAttribute("idp");
//		db_participant.insertEpreuve( new Participant( paramHttpServletRequest.getParameterValues(  ) ) );
        return next;
    }
}
