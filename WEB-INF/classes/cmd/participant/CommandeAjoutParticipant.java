package cmd.participant;

import bdd.DBS;
import bdd.DB_INSCRIPTION;
import bdd.DB_PARTICIPANT;
import beans.Participant;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CommandeAjoutParticipant {
    private final String next;

    public CommandeAjoutParticipant( String next )
    {
        this.next = next;
    }

    public String execute( HttpServletRequest paramHttpServletRequest ) throws Exception
    {
        DBS dbs            = DBS.getInstance( );
        DB_PARTICIPANT db_participant = dbs.getDB_PARTICIPANT( );

//        int idp paramHttpServletRequest.getAttribute("idp");
        db_participant.insertParticipant(new Participant(12,"michel",22));
        return next;
    }
}
