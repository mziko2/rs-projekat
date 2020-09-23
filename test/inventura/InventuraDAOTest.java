package inventura;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventuraDAOTest {
    private InventuraDAO dao = InventuraDAO.getInstance();
    @BeforeEach
    public void restartBaze() throws SQLException {
        //Vraca bazu na pocetno stanje
        dao.vratiBazuNaDefault();
    }
    @Test
    void obrisiMjesto() {
        //testiramo brisanje nepostojećeg mjesta, ne bi se ništa trebalo desit
        dao.obrisiMjesto(6);
        ArrayList<Mjesto> mjesta=dao.mjesta();
        assertEquals("Spajiz",mjesta.get(0).getNaziv());
    }
    @Test
    void obrisiMjestoDrugiSlucaj(){
        //ako obrisemo mjesto na kojem se nalazi neki proizvod, samim time se mora obrisati i taj proizvod
        dao.obrisiMjesto(1);
        ArrayList<Proizvod> proizvodi = dao.proizvodi();
        assertEquals("Sporet",proizvodi.get(0).getNaziv());
        assertEquals("Auto",proizvodi.get(1).getNaziv());
        assertEquals("Kompjuter",proizvodi.get(2).getNaziv());
    }

    @Test
    void dodajMjesto() {
        Mjesto mjesto = new Mjesto();
        mjesto.setNaziv("Terasa");
        mjesto.setLokacija("Iza kuce1");
        mjesto.setOpis("Terasa za odmaranje");
        dao.dodajMjesto(mjesto);
        Mjesto novomjesto=dao.dajMjesto("Terasa");
        //ako je pravilno dodao mjesto "Terasa" sad je treba pronaci i smjestit u novoMjesto
        assertEquals("Terasa", novomjesto.getNaziv());
        assertEquals("Iza kuce1", novomjesto.getLokacija());
        assertEquals("Terasa za odmaranje", novomjesto.getOpis());
    }


    @Test
    void izmjeniProizvod() {
        //testiramo izmjenu proizvoda
        Proizvod proizvod = dao.dajProizvod("Kompjuter");
        proizvod.setNaziv("Monitor");
        proizvod.setKolicina_proizvoda(12);
        dao.izmjeniProizvod(proizvod);

        ArrayList<Proizvod> proizvodi = dao.proizvodi();
        assertEquals("Monitor", proizvodi.get(3).getNaziv());
    }
    @Test
    void obrisiSve() {
        dao.obrisiSve();
        ArrayList<Mjesto> mjesta = dao.mjesta();
        ArrayList<Proizvod> proizvodi = dao.proizvodi();
        ArrayList<Narudzba> narudzbe = dao.narudzbe();
        assertEquals(0,mjesta.size());
        assertEquals(0,proizvodi.size());
        assertEquals(0,narudzbe.size());

    }
}