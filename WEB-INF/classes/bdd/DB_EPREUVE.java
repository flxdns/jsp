// sous module specfique de gestion des acces a la table EPREUVE
// Table : Epreuve(idp, nom, age)
package bdd;

import beans.Epreuve;

import java.sql.*;
import java.util.ArrayList;

/*
 * NB : Pour utiliser cette classe, il faut disposer d'un driver JDBC au minimum en version 4.
   (Ã  cause de l'utilisation de getGeneratedKeys())
 */

public class DB_EPREUVE
{
	
	Connection        conn;
	PreparedStatement ps_select;
	PreparedStatement ps_insert;
	PreparedStatement ps_update;
	PreparedStatement ps_delete;
	
	public DB_EPREUVE( Connection conn )
	{
		this.conn = conn;
		try {
			ps_select = conn.prepareStatement( "select nom, categ, datep, tarifclub, tarifclub from epreuve where ide=?" );
			ps_insert = conn.prepareStatement( "insert into epreuve values(default,?,?,?,?,?)" , ps_insert.RETURN_GENERATED_KEYS );
			ps_update = conn.prepareStatement( "update epreuve set nom=?, age=? where ide=?" );
			ps_delete = conn.prepareStatement( "delete from epreuve where idp=?" );
		} catch( SQLException ex ) {System.out.println( ex );}
	}
	
	public Epreuve getEpreuve( int idp ) throws Exception
	{
		Epreuve p = null;
		ps_select.setInt( 1 , idp );
		ResultSet rs = ps_select.executeQuery( );
		if( rs.next( ) ) {
			String nom = rs.getString( "nom" );
			int    age = rs.getInt( "age" );
			p = new Epreuve( idp , nom , age );
		}
		return p;
	}
	
	//la mÃ©thode renvoie le numÃ©ro du epreuve qui vient d'Ãªtre insÃ©rÃ© et qui a
	//Ã©tÃ© gÃ©nÃ©rÃ© automatiquement grace au type postgres SERIAL
	//Cela siginifie que la valeur de l'attribut idp de l'objet passÃ© en paramÃ¨tre
	//est ignorÃ©e.
	public int insertEpreuve( Epreuve p ) throws Exception
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
	
	public void updateEpreuve( Epreuve p ) throws Exception
	{
		ps_update.setString( 1 , p.getNom( ) );
		ps_update.setInt( 2 , p.getAge( ) );
		ps_update.setInt( 3 , p.getIdp( ) );
		ps_update.executeUpdate( );
	}
	
	public void deleteEpreuve( int idp ) throws Exception
	{
		ps_delete.setInt( 1 , idp );
		ps_delete.executeUpdate( );
	}
	
	// cette mÃ©thode ne peut Ãªtre utilisÃ©e que dans cette classe
	// elle ne peut pas Ãªtre utilisÃ©e dans d'autres classes
	private ArrayList< Epreuve > getEpreuves( String req ) throws Exception
	{
		// pre-condition: req est de la forme "SELECT * FROM Epreuve ..."
		// car il s'agit d'extraire un ensemble de epreuves
		
		Epreuve              par;
		ArrayList< Epreuve > apar = null;
		
		apar = new ArrayList< Epreuve >( );
		Statement st = conn.createStatement( );
		ResultSet rs = st.executeQuery( req );
		while( rs.next( ) ) {
			par = new Epreuve( rs.getInt( "idp" ) , rs.getString( "nom" ) , rs.getInt( "age" ) );
			apar.add( par );
		}
		return apar;
	}
	
	public ArrayList< Epreuve > getEpreuves( ) throws Exception
	{
		return getEpreuves( "select * from epreuve" );
	}
}