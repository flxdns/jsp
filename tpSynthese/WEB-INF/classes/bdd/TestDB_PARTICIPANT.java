package bdd;

import java.util.ArrayList;
import java.sql.SQLException;
import beans.*;

public class TestDB_PARTICIPANT {	

	public static void afficherParticipants(ArrayList<Participant> apa) {
		for (Participant p : apa) {
			System.out.println(p);
		}
	}

	public static void main (String args [])
	{
		DBS dbs = DBS.getInstance();
		DB_PARTICIPANT mondb = dbs.getDB_PARTICIPANT();
		Participant p;
		ArrayList<Participant> apa;
		int nb;

		try {
			p = mondb.getParticipant(3);
			System.out.println("Participant 3 : "+p);
		}
		catch (Exception e) { 
			System.out.println(e);
		}
		

		try {
			apa = mondb.getParticipants();
			System.out.println("----- Liste des participants -----");
			afficherParticipants(apa);
		}
		catch (Exception e) { 
			System.out.println(e);
		}
		

		int clef = -1;
		try {
		    	Participant unPar = new Participant("Martin Fourcade",28);
			clef = mondb.insertParticipant(unPar);
			apa = mondb.getParticipants();						
			System.out.println("----- Liste des participants apres insertion -----");
			afficherParticipants(apa);
		}
		catch (Exception e) { 
			System.out.println(e);
		}

		try {
		    Participant unPar = new Participant(clef,"Tessa Worley",24);
		    mondb.updateParticipant(unPar);
			apa = mondb.getParticipants();						
			System.out.println("----- Liste des participants apres modification -----");
			afficherParticipants(apa);
		}
		catch (Exception e) { 
			System.out.println(e);
		}

		try {
			mondb.deleteParticipant(clef);
			apa = mondb.getParticipants();						
			System.out.println("----- Liste des participants apres suppression -----");
			afficherParticipants(apa);
		}
		catch (Exception e) { 
			System.out.println(e);
		}


	} //fin du main

}