package inventura;

public class Proizvod {
    int id;
    String naziv;
    String kategorija;
    String mjesto;
    String datum;
    int mjesto_id;
    int kolicina_proizvoda;

    public Proizvod(int id, String naziv, String kategorija, String datum, String mjesto, int mjesto_id, int kolicina_proizvoda) {
        this.id=id;
        this.naziv=naziv;
        this.kategorija=kategorija;
        this.datum=datum;
        this.mjesto=mjesto;
        this.mjesto_id=mjesto_id;
        this.kolicina_proizvoda=kolicina_proizvoda;
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


    public int getKolicina_proizvoda() {
        return kolicina_proizvoda;
    }

    public void setKolicina_proizvoda(int kolicina_proizvoda) {
        this.kolicina_proizvoda = kolicina_proizvoda;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
