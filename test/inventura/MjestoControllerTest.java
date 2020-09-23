package inventura;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
class MjestoControllerTest {
    Stage stage1;
    MjestoController mjestoController;
    @Start
    public void start(Stage stage) throws Exception{
        InventuraDAO dao = InventuraDAO.getInstance();
        dao.vratiBazuNaDefault();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mjesto.fxml"));
        mjestoController = new MjestoController(null);
        loader.setController(mjestoController);
        Parent root = loader.load();
        stage.setTitle("Mjesto");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
        stage1=stage;
    }
    //testiramo da li postoje boje na text field-ovima kada se pokusa unijeti prazno polje
    @Test
  public void testBojeNaPoljima(FxRobot robot){
      robot.clickOn("#btnMjestoPotvrdi");


      javafx.scene.control.TextField ime = robot.lookup("#tfMjestoNaziv").queryAs(TextField.class);
      Background bg = ime.getBackground();
      boolean colorFound = false;
      for (BackgroundFill bf : bg.getFills())
          if (bf.getFill().toString().contains("ffb6c1"))
              colorFound = true;
      assertTrue(colorFound);

      robot.clickOn("#tfMjestoNaziv");
      robot.write("Kuhinja");

      robot.clickOn("#tfMjestoLokacija");
      robot.write("Kuca2");

      robot.clickOn("#tfMjestoOpis");
      robot.write("Kuhinja u drugoj kuci");


      robot.clickOn("#btnMjestoPotvrdi");

      assertFalse(stage1.isShowing());

  }

}