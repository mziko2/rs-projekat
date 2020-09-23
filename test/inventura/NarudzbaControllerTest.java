package inventura;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.awt.*;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class NarudzbaControllerTest {
    Stage stage1;
    NarudzbaController narudzbaController;
    InventuraDAO dao = InventuraDAO.getInstance();

    @Start
    public void start(Stage stage) throws Exception{
        dao.vratiBazuNaDefault();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/narudzba.fxml"));
        narudzbaController=new NarudzbaController(null,dao.mjesta(), dao.proizvodi());
        loader.setController(narudzbaController);
        Parent root = loader.load();
        stage.setTitle("Narudzba");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
        stage1=stage;
    }
    @Test
    public void testDodavanjaNarudzbe(FxRobot robot){
        robot.clickOn("#btnPotvrdiNarudzba");

        javafx.scene.control.TextField textField=  robot.lookup("#tfProizvodNarudzba").queryAs(javafx.scene.control.TextField.class);
        Background background = textField.getBackground();
        boolean collor=false;
        for(BackgroundFill backgroundFill : background.getFills()){
            if(backgroundFill.getFill().toString().contains("ffb6c1")) collor = true;
        }
        assertTrue(collor);

        robot.clickOn("#tfProizvodNarudzba");
        robot.write("Monitor");

        robot.clickOn("#tfOpisNarudzba");
        robot.write("Narudzba monitora");

        robot.clickOn("#cbVrstaNarudzba");
        robot.clickOn("Kupovina");

        DatePicker datePicker = new DatePicker();
        datePicker = robot.lookup("#dpDatumNarudzba").queryAs(DatePicker.class);
        datePicker.getEditor().setText("9.9.2020.");
        datePicker.setValue(datePicker.getConverter().fromString(datePicker.getEditor().getText()));

        robot.clickOn("#tfNarudzbaKategorija");
        robot.write("Elektornika");

        robot.clickOn("#cbNarudzbaMjesto");
        robot.clickOn("Kuhinja");

        robot.clickOn("#btnPotvrdiNarudzba");

        assertFalse(stage1.isShowing());

    }
    @Test
    public void testNarudzba(FxRobot robot){

        robot.clickOn("#tfProizvodNarudzba");
        robot.write("Monitor");

        robot.clickOn("#tfOpisNarudzba");
        robot.write("Narudzba monitora");

        robot.clickOn("#cbVrstaNarudzba");
        robot.clickOn("Kupovina");

        DatePicker datePicker = new DatePicker();
        datePicker = robot.lookup("#dpDatumNarudzba").queryAs(DatePicker.class);
        datePicker.getEditor().setText("9.9.2020.");
        datePicker.setValue(datePicker.getConverter().fromString(datePicker.getEditor().getText()));

        robot.clickOn("#tfNarudzbaKategorija");
        robot.write("Elektornika");

        robot.clickOn("#cbNarudzbaMjesto");
        robot.clickOn("Kuhinja");

        robot.clickOn("#btnPotvrdiNarudzba");

        Narudzba narudzba = narudzbaController.getNarudzba();
        assertEquals("Monitor",narudzba.getProizvod());

    }
}