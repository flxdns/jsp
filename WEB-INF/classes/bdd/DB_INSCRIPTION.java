// sous module specfique de gestion des acces a la table INSCRIPTION
// Table : Inscription(idp, nom, age)
package bdd;

import beans.Inscription;

import java.sql.*;
import java.util.ArrayList;

/*
 * NB : Pour utiliser cette classe, il faut disposer d'un driver JDBC au minimum en version 4.
   (Ã  cause de l'utilisation de getGeneratedKeys())
 */

public class DB_INSCRIPTION
{
	
	private Connection        conn;
	private PreparedStatement ps_select;
	private PreparedStatement ps_insert;
	private PreparedStatement ps_update;
	private PreparedStatement ps_delete;
	
	public DB_INSCRIPTION( Connection conn )
	{
		this.conn = conn;
		try {
			ps_select = conn.prepareStatement( "select categtarif from inscription where idp=? && ide=?" );
			ps_insert = conn.prepareStatement( "insert into inscription values(?,?,?)" , ps_insert.RETURN_GENERATED_KEYS );
			ps_update = conn.prepareStatement( "update inscription set categtarif=? where idp=? && ide=?" );
			ps_delete = conn.prepareStatement( "delete from inscription where idp=? && ide=?" );
		} catch( SQLException ex ) {ex.printStackTrace( );}
	}
	
	public Inscription getInscription( int idp , int ide )
	{
		Inscription p = null;
		try {
			
			ps_select.setInt( 1 , idp );
			ps_select.setInt( 2 , ide );
			ResultSet rs = ps_select.executeQuery( );
			if( rs.next( ) ) {
				int    idp1       = rs.getInt( "ide" );
				int    ide1       = rs.getInt( "idp" );
				String categTarif = rs.getString( "categtarif" );
				p = new Inscription( idp1 , ide1 , categTarif );
			}
		} catch( SQLException e ) {e.printStackTrace( );}
		return p;
	}
	
	//la mÃ©thode renvoie le numÃ©ro du inscription qui vient d'Ãªtre insÃ©rÃ© et qui a
	//Ã©tÃ© gÃ©nÃ©rÃ© automatiquement grace au type postgres SERIAL
	//Cela siginifie que la valeur de l'attribut idp de l'objet passÃ© en paramÃ¨tre
	//est ignorÃ©e.
	public int insertInscription( Inscription p )
	{
		int clef = - 1;
		try {
			ps_insert.setInt( 1 , p.getIde( ) );
			ps_insert.setInt( 2 , p.getIdp( ) );
			ps_insert.setString( 2 , p.getCategTarif( ) );
			//le paramÃ¨tre passÃ© Ã  executeUpdate permet de rÃ©cupÃ©rer les clefs
			//Ã©ventuellement gÃ©nÃ©rÃ©es automatiquement (via le type serial) au moment
			//de l'exÃ©cution de l'ordre SQL.
			ps_insert.executeUpdate( );
			//on rÃ©cupÃ¨re les clefs gÃ©nÃ©rÃ©es (une seule ici)
			ResultSet clefs = ps_insert.getGeneratedKeys( );
			if( clefs.next( ) ) {
				clef = clefs.getInt( 1 );
			}
		} catch( SQLException e ) {e.printStackTrace( );}
		
		return clef;
	}
	
	public void updateInscription( Inscription p )
	{
		try {
			ps_update.setString( 1 , p.getCategTarif( ) );
			ps_update.setInt( 2 , p.getIde( ) );
			ps_update.setInt( 3 , p.getIdp( ) );
			ps_update.executeUpdate( );
		} catch( SQLException e ) {e.printStackTrace( );}
	}
	
	public void deleteInscription( int idp , int ide )
	{
		try {
			ps_delete.setInt( 1 , idp );
			ps_delete.setInt( 1 , idp );
			ps_delete.executeUpdate( );
		} catch( SQLException e ) {e.printStackTrace( );}
	}
	
	// cette mÃ©thode ne peut Ãªtre utilisÃ©e que dans cette classe
	// elle ne peut pas Ãªtre utilisÃ©e dans d'autres classes
	private ArrayList< Inscription > getInscriptions( String req )
	{
		// pre-condition: req est de la forme "SELECT * FROM Inscription ..."
		// car il s'agit d'extraire un ensemble de inscriptions
		
		Inscription              par;
		ArrayList< Inscription > apar = null;
		try {
			apar = new ArrayList< Inscription >( );
			Statement st = conn.createStatement( );
			ResultSet rs = st.executeQuery( req );
			while( rs.next( ) ) {
				par = new Inscription( rs.getInt( "idp" ) , rs.getInt( "ide" ) , rs.getString( "categtarif" ) );
				apar.add( par );
			}
		} catch( SQLException e ) {e.printStackTrace( );}
		
		return apar;
	}
	
	public ArrayList< Inscription > getInscriptions( )
	{
		return getInscriptions( "select * from inscription" );
	}
	
	public ArrayList< Inscription > getInscriptionsByIde( )
	{
		return getInscriptions( "select * from inscription order by ide" );
	}
	
	public ArrayList< Inscription > getInscriptionsByCategTarif( )
	{
		return getInscriptions( "select * from inscription order by categtarif" );
	}
}