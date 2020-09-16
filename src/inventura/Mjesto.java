package inventura;

public class Mjesto {
    int id;
    String naziv;
    String lokacija;
    String opis;

    public Mjesto(int id, String naziv, String lokacija, String opis) {
        this.id=id;
        this.naziv=naziv;
        this.lokacija=lokacija;
        this.opis=opis;
    }

    public Mjesto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
