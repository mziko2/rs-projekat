package inventura;

public class Narudzba {
    int id;
    String proizvod;
    String vrsta;
    String opis;
    String datum;
    int proizvod_id;
    int mjesto_id;

    public Narudzba(int id, String proizvod, String vrsta, String opis, String datum, int proizvod_id, int mjesto_id) {
        this.id=id;
        this.proizvod=proizvod;
        this.vrsta=vrsta;
        this.opis=opis;
        this.datum=datum;
        this.proizvod_id=proizvod_id;
        this.mjesto_id=mjesto_id;
    }

    public Narudzba() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProizvod() {
        return proizvod;
    }

    public void setProizvod(String proizvod) {
        this.proizvod = proizvod;
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

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
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
