package beans;

import java.sql.Date;

public class Epreuve
{
    private int ide;
    private String nom;
    private String categ;
    private Date datep;
    private double tarifClub;
    private double tarifNonClub;

    public Epreuve(int ide, String nom, String categ, Date date, double tarifClub, double tarifNonClub)
    {
        setIde(ide);
        setNom(nom);
        setCateg(categ);
        setDatep(date);
        setTarifClub(tarifClub);
        setTarifNonClub(tarifNonClub);
    }

    public Epreuve(String nom, String categ, Date date, double tarifClub, double tarifNonClub)
    {
        setIde(-1);
        setNom(nom);
        setCateg(categ);
        setDatep(date);
        setTarifClub(tarifClub);
        setTarifNonClub(tarifNonClub);
    }

    public int getIde()
    {
        return ide;
    }

    public String getNom()
    {
        return nom;
    }

    public String getCateg()
    {
        return categ;
    }

    public Date getDatep()
    {
        return datep;
    }

    public double getTarifClub()
    {
        return tarifClub;
    }

    public double getTarifNonClub()
    {
        return tarifNonClub;
    }

    public void setIde(int ide)
    {
        this.ide = ide;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public void setCateg(String categ)
    {
        this.categ = categ;
    }

    public void setDatep(Date date)
    {
        this.datep = date;
    }

    public void setTarifClub(double tarifClub)
    {
        this.tarifClub = tarifClub;
    }

    public void setTarifNonClub(double tarifNonClub)
    {
        this.tarifNonClub = tarifNonClub;
    }

    public String toString()
    {
        return "[ " + getIde() + " , " + getNom() + " , " + getCateg() + " , " + getDatep() + " , " + getTarifClub() + " , " + getTarifNonClub() + " ]";
    }
}