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
	
	private Connection        conn;
	private PreparedStatement ps_select;
	private PreparedStatement ps_insert;
	private PreparedStatement ps_update;
	private PreparedStatement ps_delete;
	
	public DB_EPREUVE( Connection conn )
	{
		this.conn = conn;
		try {
			ps_select = conn.prepareStatement( "select nom, categ, datep, tarifclub, tarifclub from epreuve where ide=?" );
			ps_insert = conn.prepareStatement( "insert into epreuve values(default,?,?,?,?,?)" , ps_insert.RETURN_GENERATED_KEYS );
			ps_update = conn.prepareStatement( "update epreuve set nom=?, categ=?, datep=?,tarifclub=?, tarifnonclub=? where ide=?" );
			ps_delete = conn.prepareStatement( "delete from epreuve cascade where ide=?" );
		} catch( SQLException e ) {
			e.printStackTrace( );
		}
	}
	
	public Epreuve getEpreuve( int ide )
	{
		Epreuve p = null;
		try {
			ps_select.setInt( 1 , ide );
			
			ResultSet rs;
			
			rs = ps_select.executeQuery( );
			
			if( rs.next( ) ) {
				String nom          = rs.getString( "nom" );
				String categ        = rs.getString( "categ" );
				Date   datep        = rs.getDate( "datep" );
				double tarifClub    = rs.getDouble( "tarifClub" );
				double tarifNonClub = rs.getDouble( "tarifNonClub" );
				p = new Epreuve( ide , nom , categ , datep , tarifClub , tarifNonClub );
			}
		} catch( SQLException e ) {
			e.printStackTrace( );
		}
		return p;
	}
	
	//la mÃ©thode renvoie le numÃ©ro du epreuve qui vient d'Ãªtre insÃ©rÃ© et qui a
	//Ã©tÃ© gÃ©nÃ©rÃ© automatiquement grace au type postgres SERIAL
	//Cela siginifie que la valeur de l'attribut idp de l'objet passÃ© en paramÃ¨tre
	//est ignorÃ©e.
	public int insertEpreuve( Epreuve p )
	{
		int clef = - 1;
		try {
			ps_insert.setString( 1 , p.getNom( ) );
			ps_insert.setString( 2 , p.getCateg( ) );
			ps_insert.setDate( 3 , p.getDatep( ) );
			ps_insert.setDouble( 4 , p.getTarifClub( ) );
			ps_insert.setDouble( 5 , p.getTarifNonClub( ) );
			//le paramÃ¨tre passÃ© Ã  executeUpdate permet de rÃ©cupÃ©rer les clefs
			//Ã©ventuellement gÃ©nÃ©rÃ©es automatiquement (via le type serial) au moment
			//de l'exÃ©cution de l'ordre SQL.
			ps_insert.executeUpdate( );
			//on rÃ©cupÃ¨re les clefs gÃ©nÃ©rÃ©es (une seule ici)
			ResultSet clefs = ps_insert.getGeneratedKeys( );
			if( clefs.next( ) ) {
				clef = clefs.getInt( 1 );
			}
		} catch( SQLException e ) {
			e.printStackTrace( );
		}
		
		return clef;
	}
	
	public void updateEpreuve( Epreuve p )
	{
		try {
			ps_update.setString( 1 , p.getNom( ) );
			ps_update.setString( 2 , p.getCateg( ) );
			ps_update.setDate( 3 , p.getDatep( ) );
			ps_update.setDouble( 4 , p.getTarifClub( ) );
			ps_update.setDouble( 5 , p.getTarifNonClub( ) );
			ps_update.setInt( 6 , p.getIde( ) );
			
			ps_update.executeUpdate( );
		} catch( SQLException e ) {
			e.printStackTrace( );
		}
	}
	
	public void deleteEpreuve( int ide )
	{
		try {
			ps_delete.setInt( 1 , ide );
			ps_delete.executeUpdate( );
		} catch( SQLException e ) {
			e.printStackTrace( );
		}
	}
	
	// cette mÃ©thode ne peut Ãªtre utilisÃ©e que dans cette classe
	// elle ne peut pas Ãªtre utilisÃ©e dans d'autres classes
	private ArrayList< Epreuve > getEpreuves( String req )
	{
		// pre-condition: req est de la forme "SELECT * FROM Epreuve ..."
		// car il s'agit d'extraire un ensemble de epreuves
		
		Epreuve              par;
		ArrayList< Epreuve > apar;
		
		apar = new ArrayList<>( );
		try {
			Statement st = conn.createStatement( );
			ResultSet rs = st.executeQuery( req );
			while( rs.next( ) ) {
				par = new Epreuve( rs.getInt( "ide" ) , rs.getString( "nom" ) , rs.getString( "categ" ) , rs.getDate( "datep" ) ,
				                   rs.getInt( "tarifClub" ) , rs.getInt( "tarifNonClub" ) );
				apar.add( par );
			}
		} catch( SQLException e ) {
			e.printStackTrace( );
		}
		return apar;
	}
	
	public ArrayList< Epreuve > getEpreuves( )
	{
		try {
			return getEpreuves( "select * from epreuve order by ide" );
		} catch( Exception e ) {
			e.printStackTrace( );
		}
		return null;
	}
	
	public ArrayList< Epreuve > trierEpreuvesByNom( ) throws Exception
	{
		return this.getEpreuves( "select * from epreuve order by nom" );
	}
	
	public ArrayList< Epreuve > trierEpreuvesByIdE( ) throws Exception
	{
		return this.getEpreuves( "select * from epreuve order by ide" );
	}
	
	public ArrayList< Epreuve > trierEpreuvesByCateg( ) throws Exception
	{
		return this.getEpreuves( "select * from epreuve order by categ" );
	}
	
	public ArrayList< Epreuve > trierEpreuvesByDateP( ) throws Exception
	{
		return this.getEpreuves( "select * from epreuve order by datep" );
	}
	
	public ArrayList< Epreuve > trierEpreuvesByTarifClub( ) throws Exception
	{
		return this.getEpreuves( "select * from epreuve order by tarifclub" );
	}
	
	public ArrayList< Epreuve > trierEpreuvesByTarifNonClub( ) throws Exception
	{
		return this.getEpreuves( "select * from epreuve order by tarifnonclub" );
	}
}