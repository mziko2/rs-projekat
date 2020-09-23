package inventura;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class ProizvodControllerTest {
    Stage stage1;
    ProizvodController proizvodController;
    InventuraDAO dao = InventuraDAO.getInstance();
    @Start
    public void start(Stage stage) throws Exception{
        dao.vratiBazuNaDefault();
        Proizvod proizvod = new Proizvod();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/proizvod.fxml"));
        proizvodController = new ProizvodController(dao.proizvodi().get(2), dao.mjesta(), dao.dajKategorijeProizvoda());
        loader.setController(proizvodController);
        Parent root = loader.load();
        stage.setTitle("Proizvod");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
        stage1=stage;
    }
    @Test
    public void IzmjeniProizvod(FxRobot robot){
        robot.clickOn("#tfNazivProizvod");
        robot.write("mobil");

        robot.clickOn("#cbVrstaProizvod");
        robot.clickOn("Elektronika");

        robot.clickOn("#cbMjestoProizvod");
        robot.clickOn("Spajiz");

        DatePicker datePicker = new DatePicker();
        datePicker = robot.lookup("#dpDatumProizvod").queryAs(DatePicker.class);
        datePicker.getEditor().setText("9.9.2020.");
        datePicker.setValue(datePicker.getConverter().fromString(datePicker.getEditor().getText()));


        robot.clickOn("#tfKolicinaProizvoda");
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.A).release(KeyCode.CONTROL);
        robot.write("-10");

        robot.clickOn("#btnPotvrdiProizvod");

        javafx.scene.control.TextField textField1 =  robot.lookup("#tfKolicinaProizvoda").queryAs(javafx.scene.control.TextField.class);
        Background background = textField1.getBackground();
        boolean collor=false;
        for(BackgroundFill backgroundFill : background.getFills()){
            if(backgroundFill.getFill().toString().contains("ffb6c1")) collor = true;
        }
        assertTrue(collor);

        robot.clickOn("#tfKolicinaProizvoda");
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.A).release(KeyCode.CONTROL);
        robot.write("10");

        robot.clickOn("#btnPotvrdiProizvod");

        assertFalse(stage1.isShowing());
    }




}