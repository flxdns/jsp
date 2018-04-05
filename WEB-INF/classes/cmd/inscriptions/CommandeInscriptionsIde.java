package cmd.inscriptions;

import bdd.DBS;
import bdd.DB_INSCRIPTION;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CommandeInscriptionsIde implements Commande {
    private final String next;

    public CommandeInscriptionsIde(String next) {
        this.next = next;
    }

    public String execute(HttpServletRequest httpServletRequest) throws Exception {
        DBS dbs = DBS.getInstance();
        DB_INSCRIPTION db_inscription = dbs.getDB_INSCRIPTION();
        ArrayList inscriptionsByIde = db_inscription.getInscriptionsByIde();
        httpServletRequest.setAttribute("inscriptions", inscriptionsByIde);
        return this.next;
    }
}
