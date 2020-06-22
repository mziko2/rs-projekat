package inventura;

import java.util.Date;

public class narudzba {
    int id;
    String kategorija;
    String vrsta;
    String opis;
    Date datum;
    int proizvod_id;
    int mjesto_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getProizvod_id() {
        return proizvod_id;
    }

    public void setProizvod_id(int proizvod_id) {
        this.proizvod_id = proizvod_id;
    }

    public int getMjesto_id() {
        return mjesto_id;
    }

    public void setMjesto_id(int mjesto_id) {
        this.mjesto_id = mjesto_id;
    }
}
