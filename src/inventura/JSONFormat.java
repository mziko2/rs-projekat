package inventura;

import com.sun.javafx.collections.ArrayListenerHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class JSONFormat {
    private ArrayList<Proizvod> proizvodi = new ArrayList<Proizvod>();
    private ArrayList<Mjesto>  mjesta = new ArrayList<Mjesto>();
    private ArrayList<Narudzba> narudzbe = new ArrayList<Narudzba>();

    public void zapisi(File file)  {
        JSONArray jInventura = new JSONArray();
        for(Proizvod proizvod : proizvodi) {
            JSONObject jproizvod = new JSONObject();
            jproizvod.put("naziv", proizvod.getNaziv());
            jproizvod.put("kategorija", proizvod.getKategorija());
            jproizvod.put("datum",proizvod.getDatum());
            jproizvod.put("mjesto", proizvod.getMjesto());
            jproizvod.put("kolicina", proizvod.getKolicina_proizvoda());
            jInventura.put(jproizvod);
        }
        for(Mjesto mjesto:mjesta){
            JSONObject jmjesto = new JSONObject();
            jmjesto.put("naziv",mjesto.getNaziv());
            jmjesto.put("lokacija", mjesto.getLokacija());
            jmjesto.put("opis",mjesto.getOpis());
            jInventura.put(jmjesto);
        }
        for(Narudzba narudzba : narudzbe){
            JSONObject jnarudzba = new JSONObject();
            jnarudzba.put("proizvod", narudzba.getProizvod());
            jnarudzba.put("vrsta",narudzba.getVrsta());
            jnarudzba.put("opis",narudzba.getOpis());
            jnarudzba.put("datum",narudzba.getDatum());
            jInventura.put(jnarudzba);
        }
        try {
            Files.writeString(file.toPath(), jInventura.toString());

        } catch (IOException e) {
            return;
        }
    }

    public ArrayList<Proizvod> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(ArrayList<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }

    public ArrayList<Mjesto> getMjesta() {
        return mjesta;
    }

    public void setMjesta(ArrayList<Mjesto> mjesta) {
        this.mjesta = mjesta;
    }

    public ArrayList<Narudzba> getNarudzbe() {
        return narudzbe;
    }

    public void setNarudzbe(ArrayList<Narudzba> narudzbe) {
        this.narudzbe = narudzbe;
    }
}
