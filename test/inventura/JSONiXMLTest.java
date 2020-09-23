package inventura;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;
public class JSONiXMLTest {
    InventuraDAO dao = InventuraDAO.getInstance();

    @Test
    void ZapisiPraznoJson(){
        JSONFormat json = new JSONFormat();
        File file = new File("proba.json");
        json.zapisi(file);
        try{
            String ulaz= Files.readString(file.toPath());
            ulaz = Arrays.stream(ulaz.split("\n")).map(String::trim).collect(Collectors.joining(""));
            assertEquals("[]", ulaz);
        } catch (IOException e) {
            fail("Nije uspjelo čitanje datoteke!");
        }
    }
    @Test
    void testZapisiSve() throws SQLException {
        InventuraDAO dao = InventuraDAO.getInstance();
        dao.vratiBazuNaDefault();
        XMLFormat xml = new XMLFormat();
        File file = new File("proba");

        ArrayList<Proizvod> proizvodi = new ArrayList<>();
        ArrayList<Mjesto> mjesta = new ArrayList<>();
        ArrayList<Narudzba> narudzbe = new ArrayList<>();

        proizvodi=dao.proizvodi();
        mjesta=dao.mjesta();
        narudzbe=dao.narudzbe();

        xml.setProizvodi(proizvodi);
        xml.setMjesta(mjesta);
        xml.setNarudzbe(narudzbe);

        xml.zapisi(file);
        try{
            String ulaz = Files.readString(file.toPath());
            ulaz = Arrays.stream(ulaz.split("\n")).map(String::trim).collect(Collectors.joining(""));
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><inventura><proizvod><id>1</id><naziv>Frizider</naziv><kategorija>Bijela tehnika</kategorija><datum>2020-12-03</datum><mjesto>Spajiz</mjesto><mjesto_id>1</mjesto_id><kolicina_proizvoda>1</kolicina_proizvoda></proizvod><proizvod><id>2</id><naziv>Sporet</naziv><kategorija>Bijela tehnika</kategorija><datum>2020-12-03</datum><mjesto>Kuhinja</mjesto><mjesto_id>2</mjesto_id><kolicina_proizvoda>2</kolicina_proizvoda></proizvod><proizvod><id>3</id><naziv>Auto</naziv><kategorija>Vozilo</kategorija><datum>2020-12-03</datum><mjesto>Garaza</mjesto><mjesto_id>3</mjesto_id><kolicina_proizvoda>1</kolicina_proizvoda></proizvod><proizvod><id>4</id><naziv>Kompjuter</naziv><kategorija>Elektronika</kategorija><datum>2020-12-03</datum><mjesto>Djecija soba</mjesto><mjesto_id>4</mjesto_id><kolicina_proizvoda>5</kolicina_proizvoda></proizvod><mjesto><id>1</id><naziv>Spajiz</naziv><lokacija>Kuca1</lokacija><opis>Ostava</opis></mjesto><mjesto><id>2</id><naziv>Kuhinja</naziv><lokacija>Kuca1</lokacija><opis>Mjesto gdje se kuha</opis></mjesto><mjesto><id>3</id><naziv>Garaza</naziv><lokacija>Kuca1</lokacija><opis>Parking za auto</opis></mjesto><mjesto><id>4</id><naziv>Djecija soba</naziv><lokacija>Kuca1</lokacija><opis>mjesto gdje djeca provode vrijeme</opis></mjesto><narudzba><id>1</id><proizvod>Frizider</proizvod><vrsta>Kupovina</vrsta><opis>Kupovina fizidera</opis><datum>2020-09-09</datum><proizvod_id>1</proizvod_id><mjesto_id>1</mjesto_id></narudzba><narudzba><id>2</id><proizvod>Sporet</proizvod><vrsta>Kupovina</vrsta><opis>Kupovina sporeta</opis><datum>2020-09-09</datum><proizvod_id>2</proizvod_id><mjesto_id>2</mjesto_id></narudzba><narudzba><id>3</id><proizvod>Racunar</proizvod><vrsta>Kupovina</vrsta><opis>Kupovina racunara</opis><datum>2020-09-09</datum><proizvod_id>4</proizvod_id><mjesto_id>4</mjesto_id></narudzba><narudzba><id>4</id><proizvod>Auto</proizvod><vrsta>Kupovina</vrsta><opis>Kupovina automobila</opis><datum>2020-09-09</datum><proizvod_id>3</proizvod_id><mjesto_id>3</mjesto_id></narudzba></inventura>",ulaz);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Nije uspjelo čitanje datoteke!!!");
        }
    }
}
