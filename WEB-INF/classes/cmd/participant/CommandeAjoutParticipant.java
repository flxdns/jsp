package cmd.participant;

import cmd.Commande;

import javax.servlet.http.HttpServletRequest;

public class CommandeAjoutParticipant implements Commande {
    private final String next;

    public CommandeAjoutParticipant(String next) {
        this.next = next;
    }

    public String execute(HttpServletRequest paramHttpServletRequest) throws Exception {
        return next;
    }
}
