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
        //nisam mogao napraviti da radi preko .getStyleClass() sa add i removeAll tako da sam morao napraviti direktno preko .setStyle(), nadam se da nije problem;
        boolean collor =true;
        if(tfMjestoOpis.getText().trim().isEmpty()){
            tfMjestoOpis.getStyleClass().removeAll("poljeIspravno");
            tfMjestoOpis.getStyleClass().add("PoljeNijeIspravno");
            tfMjestoOpis.setStyle("-fx-control-inner-background: lightpink;");
            collor=false;
        }else{
            tfMjestoOpis.getStyleClass().removeAll("poljeNijeIspravno");
            tfMjestoOpis.getStyleClass().add("poljeIspravno");
            tfMjestoOpis.setStyle("-fx-control-inner-background: greenyellow;");
        }

        if(tfMjestoLokacija.getText().trim().isEmpty()){
            tfMjestoLokacija.getStyleClass().removeAll("poljeIspravno");
            tfMjestoLokacija.getStyleClass().add("PoljeNijeIspravno");
            tfMjestoLokacija.setStyle("-fx-control-inner-background: lightpink;");
            collor=false;
        }else{
            tfMjestoLokacija.getStyleClass().removeAll("poljeNijeIspravno");
            tfMjestoLokacija.getStyleClass().add("poljeIspravno");
            tfMjestoLokacija.setStyle("-fx-control-inner-background: greenyellow;");
        }
        if(tfMjestoNaziv.getText().trim().isEmpty()){
            tfMjestoNaziv.getStyleClass().removeAll("poljeIspravno");
            tfMjestoNaziv.getStyleClass().add("PoljeNijeIspravno");
            tfMjestoNaziv.setStyle("-fx-control-inner-background: lightpink;");
            collor=false;
        }else{
            tfMjestoNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            tfMjestoNaziv.getStyleClass().add("poljeIspravno");
            tfMjestoNaziv.setStyle("-fx-control-inner-background: greenyellow;");
        }
        if(!collor) return;
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
