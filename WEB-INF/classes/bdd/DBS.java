package bdd;

import java.sql.*;

/*
 * Les lignes commentÃ©es pourront Ãªtre dÃ©commentÃ©es lorsque vous aurez Ã©crit les classes
 * DB... et les classes de beans manquantes.
 */
public class DBS {

  private Connection cnx;
  private DB_PARTICIPANT db_participant; 
  private DB_EPREUVE db_epreuve;
  private DB_INSCRIPTION db_inscription;
  
  private static DBS instance;

  public static DBS getInstance(){
	if(null==instance){instance=new DBS();}
	return instance;
  }

  
  private DBS() {
     try{
        	Class.forName ("org.postgresql.Driver");
        	cnx = DriverManager.getConnection ("jdbc:postgresql:postgresql-letracnar.alwaysdata.net", "letracnar_tomcat", "172839"); // A MODIFIER !
            db_participant = new DB_PARTICIPANT(cnx);
		    db_epreuve = new DB_EPREUVE(cnx);
		    db_inscription = new DB_INSCRIPTION(cnx);
     } catch(ClassNotFoundException | SQLException e){System.out.println(e);}
  }

  public DB_PARTICIPANT getDB_PARTICIPANT(){return db_participant;}
  public DB_EPREUVE getDB_EPREUVE(){return db_epreuve;}
  public DB_INSCRIPTION getDB_INSCRIPTION(){return db_inscription;}
      
}
