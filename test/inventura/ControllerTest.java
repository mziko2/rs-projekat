package inventura;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testng.annotations.BeforeTest;

import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class ControllerTest {
    Stage stage1;
    Controller controller;
    InventuraDAO dao = InventuraDAO.getInstance();
    @Start
    public void start (Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        controller = new Controller();
        loader.setController(controller);
        Parent root = loader.load();
        stage.setTitle("Inventura");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();

        stage.toFront();

        stage1 = stage;
    }
    @BeforeEach
    public void resetujBazu() throws SQLException {
        dao.vratiBazuNaDefault();
    }


    @Test
    void dodajProstor(FxRobot robot){
        robot.clickOn("#tabMjesto");
        robot.clickOn("#btnDodajProstor");
        robot.lookup("#tfMjestoNaziv").tryQuery().isPresent();
        robot.clickOn("#tfMjestoNaziv");
        robot.write("Spavaca soba");
        robot.clickOn("#tfMjestoOpis");
        robot.write("Mjesto gdje se spava");
        robot.clickOn("#tfMjestoLokacija");
        robot.write("Kuca1");
        robot.clickOn("#btnMjestoPotvrdi");
        InventuraDAO dao = InventuraDAO.getInstance();
        assertEquals(5,dao.mjesta().size());
        boolean pronadjeno = false;
        for(Mjesto mjesto: dao.mjesta())
            if (mjesto.getNaziv().equals("Spavaca soba") && mjesto.getLokacija().equals("Kuca1") && mjesto.getOpis().equals("Mjesto gdje se spava")) {
                pronadjeno = true;
                break;
            }
        assertTrue(pronadjeno);
    }
    @Test
    void obrisiProstor(FxRobot robot) {
        robot.clickOn("#tabMjesto");
        robot.clickOn("Spajiz");
        robot.clickOn("#btnObrisiProstor");


        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        javafx.scene.control.Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);


        TableView tableViewProizvodi = robot.lookup("#tvProstor").queryAs(TableView.class);
        assertEquals(3, tableViewProizvodi.getItems().size());


        InventuraDAO dao = InventuraDAO.getInstance();
        assertEquals(3, dao.mjesta().size());
    }

    @Test
    void obrisiNarudzba(FxRobot robot) {
        robot.clickOn("#tabNarudzba");
        robot.clickOn("Frizider");
        robot.clickOn("#btnObrisiNarudzbu");


        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        javafx.scene.control.Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);


        TableView tableViewProizvodi = robot.lookup("#tvNarudzba").queryAs(TableView.class);
        assertEquals(3, tableViewProizvodi.getItems().size());


        InventuraDAO dao = InventuraDAO.getInstance();
        assertEquals(3, dao.narudzbe().size());

    }

    @Test
    void setPocetnu(FxRobot robot) {
        robot.lookup("#lPocetnaTekst");
        Label label = robot.lookup("#lPocetnaTekst").queryAs(Label.class);
        assertEquals("Trenutno imate 4 proizvoda na 4 lokacije.",label.getText());
        Label label1 = robot.lookup("#lPocetnaTekst1").queryAs(Label.class);
        assertEquals("Do sada ste naručili 4 proizvoda.",label1.getText());
    }

    @Test
    void obrisiProizvod(FxRobot robot) {
        robot.clickOn("#tabProizvod");
        robot.clickOn("Auto");
        robot.clickOn("#btnObrisiProizvod");


        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        javafx.scene.control.Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);


        TableView tableViewProizvodi = robot.lookup("#tbProizvod").queryAs(TableView.class);
        assertEquals(3, tableViewProizvodi.getItems().size());


        InventuraDAO dao = InventuraDAO.getInstance();
        assertEquals(3, dao.proizvodi().size());

    }
}