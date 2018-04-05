package cmd.epreuves;

import bdd.DBS;
import bdd.DB_EPREUVE;
import beans.Epreuve;
import cmd.Commande;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class CommandeAjoutEpreuveForm implements Commande {
    private final String next;

    public CommandeAjoutEpreuveForm(String next) {
        this.next = next;
    }

    public String execute(HttpServletRequest paramHttpServletRequest) throws Exception {
        DBS dbs = DBS.getInstance();
        DB_EPREUVE db_epreuve = dbs.getDB_EPREUVE();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = (Date) format.parse(paramHttpServletRequest.getParameter("date"));

        db_epreuve.insertEpreuve(new Epreuve(0, paramHttpServletRequest.getParameter("nom"), paramHttpServletRequest.getParameter("categ"), parsed, Integer.parseInt(paramHttpServletRequest.getParameter("tarifclub")), Integer.parseInt(paramHttpServletRequest.getParameter("categ"))));
        return next;
    }
}
