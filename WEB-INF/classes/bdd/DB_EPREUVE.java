package bdd;

import beans.Epreuve;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB_EPREUVE
{
    Connection conn;
    PreparedStatement ps_select;
    PreparedStatement ps_insert;
    PreparedStatement ps_update;
    PreparedStatement ps_delete;

    public DB_EPREUVE(Connection paramConnection)
    {
        conn = paramConnection;
        try {
            ps_select = paramConnection.prepareStatement("select nom,categ,datep,tarifClub,tarifNonClub from epreuve where ide=?");
            ps_insert = paramConnection.prepareStatement("insert into epreuve values(default,?,?,?,?,?)", 1);
            ps_update = paramConnection.prepareStatement("update epreuve set nom=?, categ=?, datep=?, tarifClub=?, tarifNonClub=? where ide=?");
            ps_delete = paramConnection.prepareStatement("delete from epreuve where ide=?");
        } catch (SQLException SQLException) { System.out.println(SQLException); }
    }

    public Epreuve getEpreuve(int id) throws Exception
    {
        Epreuve epreuve = null;
        ps_select.setInt(1, id);
        ResultSet rs = ps_select.executeQuery();
        if (rs.next()) {
            String nom = rs.getString("nom");
            String categ = rs.getString("categ");
            Date date = rs.getDate("datep");
            int tarifClub = rs.getInt("tarifClub");
            int tarifNonClub = rs.getInt("tarifNonClub");
            epreuve = new Epreuve(id, nom, categ, date, tarifClub, tarifNonClub);
        }
        return epreuve;
    }

    public int insertEpreuve(Epreuve epreuve) throws Exception
    {
        int i = -1;

        ps_insert.setString(1, epreuve.getNom());
        ps_insert.setString(2, epreuve.getCateg());
        ps_insert.setDate(3, epreuve.getDatep());
        ps_insert.setInt(4, epreuve.getTarifClub());
        ps_insert.setInt(5, epreuve.getTarifNonClub());

        ps_insert.executeUpdate();

        ResultSet rs = ps_insert.getGeneratedKeys();
        if (rs.next()) {
            i = rs.getInt(1);
        }
        return i;
    }

    public void updateEpreuve(Epreuve epreuve) throws Exception
    {
        ps_update.setString(1, epreuve.getNom());
        ps_update.setString(2, epreuve.getCateg());
        ps_update.setDate(3, epreuve.getDatep());
        ps_update.setInt(4, epreuve.getTarifClub());
        ps_update.setInt(5, epreuve.getTarifNonClub());
        ps_update.setInt(6, epreuve.getIde());
        ps_update.executeUpdate();
    }

    public void deleteEpreuve(int id) throws Exception
    {
        ps_delete.setInt(1, id);
        ps_delete.executeUpdate();
    }

    private ArrayList<Epreuve> getEpreuves(String msg) throws Exception
    {
        ArrayList al = null;

        al = new ArrayList();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(msg);
        while (rs.next())
        {

            Epreuve epreuve = new Epreuve(rs.getInt("ide"), rs.getString("nom"), rs.getString("categ"), rs.getDate("datep"), rs.getInt("tarifClub"), rs.getInt("tarifNonClub"));

            al.add(epreuve);
        }
        return al;
    }

    public ArrayList<Epreuve> getEpreuves() throws Exception
    {
        return getEpreuves("SELECT * FROM epreuve");
    }

    public ArrayList<Epreuve> trierEpreuvesByNom() throws Exception
    {
        return getEpreuves("SELECT * FROM epreuve order by nom");
    }

    public ArrayList<Epreuve> trierEpreuvesByIdE() throws Exception
    {
        return getEpreuves("SELECT * FROM epreuve order by ide");
    }

    public ArrayList<Epreuve> trierEpreuvesByCateg() throws Exception
    {
        return getEpreuves("SELECT * FROM epreuve order by categ");
    }

    public ArrayList<Epreuve> trierEpreuvesByDateP() throws Exception
    {
        return getEpreuves("SELECT * FROM epreuve order by datep");
    }

    public ArrayList<Epreuve> trierEpreuvesByTarifClub() throws Exception
    {
        return getEpreuves("SELECT * FROM epreuve order by tarifclub");
    }

    public ArrayList<Epreuve> trierEpreuvesByTarifNonClub() throws Exception
    {
        return getEpreuves("SELECT * FROM epreuve order by tarifnonclub");
    }
}