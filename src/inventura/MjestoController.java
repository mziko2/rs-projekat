package inventura;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MjestoController implements Initializable {
    public TextField tfMjestoNaziv;
    public TextField tfMjestoLokacija;
    public TextField tfMjestoOpis;
    private Mjesto mjesto;

    public MjestoController(){

    }
    public MjestoController(Mjesto mjesto){
        this.mjesto=mjesto;
    }

    public Mjesto getMjesto() {
        return mjesto;
    }

    public void potrdiMjesto(ActionEvent actionEvent) {
        if(mjesto==null) mjesto = new Mjesto();
        mjesto.setNaziv(tfMjestoNaziv.getText());
        mjesto.setOpis(tfMjestoOpis.getText());
        mjesto.setLokacija(tfMjestoLokacija.getText());
        Stage stage = (Stage) tfMjestoOpis.getScene().getWindow();
        stage.close();
    }

    public void odbaciMjesto(ActionEvent actionEvent) {
        mjesto = null;
        Stage stage = (Stage) tfMjestoOpis.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(mjesto!=null){
            tfMjestoNaziv.setText(mjesto.getNaziv());
            tfMjestoLokacija.setText(mjesto.getLokacija());
            tfMjestoOpis.setText(mjesto.getOpis());
        }
    }
}
