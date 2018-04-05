// sous module specfique de gestion des acces a la table PARTICIPANT
// Table : Participant(idp, nom, age)
package bdd;

import beans.Participant;

import java.sql.*;
import java.util.ArrayList;

/*
 * NB : Pour utiliser cette classe, il faut disposer d'un driver JDBC au minimum en version 4.
   (Ã  cause de l'utilisation de getGeneratedKeys())
 */

public class DB_PARTICIPANT
{
	
	Connection        conn;
	PreparedStatement ps_select;
	PreparedStatement ps_insert;
	PreparedStatement ps_update;
	PreparedStatement ps_delete;
	
	public DB_PARTICIPANT( Connection conn )
	{
		this.conn = conn;
		try {
			ps_select = conn.prepareStatement( "select nom,age from participant where idp=?" );
			ps_insert = conn.prepareStatement( "insert into participant values(default,?,?)" , ps_insert.RETURN_GENERATED_KEYS );
			ps_update = conn.prepareStatement( "update participant set nom=?, age=? where idp=? " );
			ps_delete = conn.prepareStatement( "delete from participant where idp=?" );
		} catch( SQLException ex ) {
			ex.printStackTrace( );
		}
	}
	
	public Participant getParticipant( int idp ) throws Exception
	{
		Participant p = null;
		ps_select.setInt( 1 , idp );
		ResultSet rs = ps_select.executeQuery( );
		if( rs.next( ) ) {
			String nom = rs.getString( "nom" );
			int    age = rs.getInt( "age" );
			p = new Participant( idp , nom , age );
		}
		return p;
	}
	
	//la mÃ©thode renvoie le numÃ©ro du participant qui vient d'Ãªtre insÃ©rÃ© et qui a
	//Ã©tÃ© gÃ©nÃ©rÃ© automatiquement grace au type postgres SERIAL
	//Cela siginifie que la valeur de l'attribut idp de l'objet passÃ© en paramÃ¨tre
	//est ignorÃ©e.
	public int insertParticipant( Participant p ) throws Exception
	{
		int clef = - 1;
		
		ps_insert.setString( 1 , p.getNom( ) );
		ps_insert.setInt( 2 , p.getAge( ) );
		//le paramÃ¨tre passÃ© Ã  executeUpdate permet de rÃ©cupÃ©rer les clefs
		//Ã©ventuellement gÃ©nÃ©rÃ©es automatiquement (via le type serial) au moment
		//de l'exÃ©cution de l'ordre SQL.
		ps_insert.executeUpdate( );
		//on rÃ©cupÃ¨re les clefs gÃ©nÃ©rÃ©es (une seule ici)
		ResultSet clefs = ps_insert.getGeneratedKeys( );
		if( clefs.next( ) ) {
			clef = clefs.getInt( 1 );
		}
		
		return clef;
	}
	
	public void updateParticipant( Participant p ) throws Exception
	{
		ps_update.setString( 1 , p.getNom( ) );
		ps_update.setInt( 2 , p.getAge( ) );
		ps_update.setInt( 3 , p.getIdp( ) );
		ps_update.executeUpdate( );
	}
	
	public void deleteParticipant( int idp )
	{
		try {
			ps_delete.setInt( 1 , idp );
			ps_delete.executeUpdate( );
		} catch( SQLException e ) {
			e.printStackTrace( );
		}
	}
	
	// cette mÃ©thode ne peut Ãªtre utilisÃ©e que dans cette classe
	// elle ne peut pas Ãªtre utilisÃ©e dans d'autres classes
	private ArrayList< Participant > getParticipants( String req )
	{
		// pre-condition: req est de la forme "SELECT * FROM Participant ..."
		// car il s'agit d'extraire un ensemble de participants
		
		Participant              par;
		ArrayList< Participant > apar;
		apar = new ArrayList<>( );
		
		try {
			
			Statement st = conn.createStatement( );
			ResultSet rs = st.executeQuery( req );
			while( rs.next( ) ) {
				par = new Participant( rs.getInt( "idp" ) , rs.getString( "nom" ) , rs.getInt( "age" ) );
				apar.add( par );
			}
		} catch( SQLException e ) {
			e.printStackTrace( );
		}
		return apar;
	}
	
	public ArrayList< Participant > getParticipants( ) throws Exception
	{
		return getParticipants( "select * from participant order by idp" );
	}
	
	public ArrayList< Participant > getParticipantsByNom( ) throws Exception
	{
		return getParticipants( "select * from participant order by nom" );
	}
	
	public ArrayList< Participant > getParticipantsByAge( ) throws Exception
	{
		return getParticipants( "select * from participant order by age" );
	}
}