package inventura;

import java.util.Date;

public class Proizvod {
    int id;
    String naziv;
    String kategorija;
    String mjesto;
    String datum;
    int mjesto_id;

    public Proizvod(int id, String naziv, String kategorija, String datum, String mjesto, int mjesto_id) {
        this.id=id;
        this.naziv=naziv;
        this.kategorija=kategorija;
        this.datum=datum;
        this.mjesto=mjesto;
        this.mjesto_id=mjesto_id;
    }
    public Proizvod() {

    }
    public String getMjesto() {
        return mjesto;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
    }

    public int getMjesto_id() {
        return mjesto_id;
    }

    public void setMjesto_id(int mjesto_id) {
        this.mjesto_id = mjesto_id;
    }

    public int getProstor_id() {
        return prostor_id;
    }

    public void setProstor_id(int prostor_id) {
        this.prostor_id = prostor_id;
    }

    int prostor_id;

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

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

   

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
