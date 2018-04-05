package beans;

public class Inscription {
    private int idp;
    private int ide;
    private String categTarif;

    public Inscription(int idp, int ide, String categTarif) {
        setIdp(idp);
        setIde(ide);
        setCategTarif(categTarif);
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public String getCategTarif() {
        return categTarif;
    }

    public void setCategTarif(String categTarif) {
        this.categTarif = categTarif;
    }

    public String toString() {
        return "[ " + getIdp() + " , " + getIde() + " , " + getCategTarif() + " ]";
    }
}