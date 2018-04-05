package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBS
{
	
	private static DBS            instance;
	private        Connection     cnx;
	private        DB_PARTICIPANT db_participant;
	private        DB_EPREUVE     db_epreuve;
	private        DB_INSCRIPTION db_inscription;
	
	private DBS( )
	{
		try {
			Class.forName( "org.postgresql.Driver" );
			
			cnx = DriverManager.getConnection( "jdbc:postgresql://postgresql-letracnar.alwaysdata.net:5432/letracnar_sport" , "letracnar_tomcat" ,
			                                   "172839" );
			//cnx = DriverManager.getConnection("jdbc:postgresql://woody:5432/df161529", "df161529", "toto");
			db_participant = new DB_PARTICIPANT( cnx );
			db_epreuve = new DB_EPREUVE( cnx );
			db_inscription = new DB_INSCRIPTION( cnx );
		} catch( ClassNotFoundException | SQLException e ) {
			e.printStackTrace( );
		}
	}
	
	public static DBS getInstance( )
	{
		if( null == instance ) {
			instance = new DBS( );
		}
		return instance;
	}
	
	public DB_PARTICIPANT getDB_PARTICIPANT( )
	{
		return db_participant;
	}
	
	public DB_EPREUVE getDB_EPREUVE( )
	{
		return db_epreuve;
	}
	
	public DB_INSCRIPTION getDB_INSCRIPTION( )
	{
		return db_inscription;
	}
}
