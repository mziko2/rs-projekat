package inventura;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NarudzbaController implements Initializable {
    public TextField tfProizvodNarudzba;
    public TextField tfOpisNarudzba;
    public DatePicker dpDatumNarudzba;
    public ChoiceBox<String> cbVrstaNarudzba;
    public ObservableList<String> vrste = FXCollections.observableArrayList();
    public ObservableList<String> listMjesto=FXCollections.observableArrayList();
    public TextField tfNarudzbaKategorija;
    public ChoiceBox<String> cbNarudzbaMjesto;
    private Narudzba narudzba;
    private ArrayList<Mjesto> novaMjesta;
    private ArrayList<Proizvod> noviProizvodi;
    public Proizvod proizvod = new Proizvod();
    public NarudzbaController(){

    }
    public NarudzbaController(Narudzba narudzba, ArrayList<Mjesto> mjesta, ArrayList<Proizvod> proizvodi){
        this.narudzba=narudzba;
        for(Mjesto mjesto:mjesta){
            listMjesto.add(mjesto.getNaziv());
        }
        this.novaMjesta=mjesta;
        this.noviProizvodi=proizvodi;
    }

    public void potvrdiNarudzba(ActionEvent actionEvent) {
        boolean collor=true;
//nisam mogao napraviti da radi preko .getStyleClass() sa add i removeAll tako da sam morao napraviti direktno preko .setStyle(), nadam se da nije problem;

        if(tfNarudzbaKategorija.getText().trim().isEmpty()){
            tfNarudzbaKategorija.getStyleClass().removeAll("poljeIspravno");
            tfNarudzbaKategorija.getStyleClass().add("PoljeNijeIspravno");
            tfNarudzbaKategorija.setStyle("-fx-control-inner-background: lightpink;");
            collor=false;
        }else{
            tfNarudzbaKategorija.getStyleClass().removeAll("poljeNijeIspravno");
            tfNarudzbaKategorija.getStyleClass().add("poljeIspravno");
            tfNarudzbaKategorija.setStyle("-fx-control-inner-background: greenyellow;");
        }
        if(tfOpisNarudzba.getText().trim().isEmpty()){
            tfOpisNarudzba.getStyleClass().removeAll("poljeIspravno");
            tfOpisNarudzba.getStyleClass().add("PoljeNijeIspravno");
            tfOpisNarudzba.setStyle("-fx-control-inner-background: lightpink;");
            collor=false;
        }else{
            tfOpisNarudzba.getStyleClass().removeAll("poljeIspravno");
            tfOpisNarudzba.getStyleClass().add("PoljeNijeIspravno");
            tfOpisNarudzba.setStyle("-fx-control-inner-background: greenyellow;");
        }
        if(tfProizvodNarudzba.getText().trim().isEmpty()){
            tfProizvodNarudzba.getStyleClass().removeAll("poljeIspravno");
            tfProizvodNarudzba.getStyleClass().add("PoljeNijeIspravno");
            tfProizvodNarudzba.setStyle("-fx-control-inner-background: lightpink;");
            collor=false;
        }else{
            tfProizvodNarudzba.getStyleClass().removeAll("poljeIspravno");
            tfProizvodNarudzba.getStyleClass().add("PoljeNijeIspravno");
            tfProizvodNarudzba.setStyle("-fx-control-inner-background: greenyellow;");
        }
        if(cbVrstaNarudzba.getSelectionModel().isEmpty()){
            collor=false;
        }
        if(cbNarudzbaMjesto.getSelectionModel().isEmpty()){
            collor=false;
        }
        if(!collor) return;


        boolean nasao = false;
        if(narudzba==null) narudzba = new Narudzba();
        narudzba.setProizvod(tfProizvodNarudzba.getText());
        narudzba.setOpis(tfOpisNarudzba.getText());
        narudzba.setVrsta(cbVrstaNarudzba.getSelectionModel().getSelectedItem());
        narudzba.setDatum(dpDatumNarudzba.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-d")));
        for(Mjesto mjesto:novaMjesta){
            if(cbNarudzbaMjesto.getSelectionModel().getSelectedItem().equals(mjesto.getNaziv())) narudzba.setMjesto_id(mjesto.getId());
        }
        for(Proizvod proizvod:noviProizvodi) {
            if (narudzba.getProizvod().equals(proizvod.getNaziv())) {
                nasao = true;
                break;
            }
        }
            if(!nasao){
                proizvod.setNaziv(tfProizvodNarudzba.getText());
                proizvod.setKategorija(tfNarudzbaKategorija.getText());
                proizvod.setDatum(dpDatumNarudzba.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-d")));
                proizvod.setMjesto(cbNarudzbaMjesto.getSelectionModel().getSelectedItem());
                proizvod.setMjesto_id(narudzba.getMjesto_id());
            }
            else proizvod=null;
            Stage stage = (Stage) tfNarudzbaKategorija.getScene().getWindow();
            stage.close();

    }
    public Narudzba getNarudzba(){ return narudzba; }
    public Proizvod getNoviProizvodIzNarudzbe() { return proizvod; }

    public void odbaciNarudzba(ActionEvent actionEvent) {
        narudzba=null;
        Stage stage = (Stage) tfNarudzbaKategorija.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbNarudzbaMjesto.setItems(listMjesto);
        vrste.addAll("Kupovina","Prodaja","Posudba");
        cbVrstaNarudzba.setItems(vrste);
        if(narudzba!=null) {
            tfProizvodNarudzba.setText(narudzba.getProizvod());
            tfOpisNarudzba.setText(narudzba.getOpis());
            DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().append(DateTimeFormatter.ofPattern("yyyy-d-MM")).toFormatter();
            dpDatumNarudzba.setValue(LocalDate.parse(narudzba.getDatum(), df));

            for (Mjesto mjesto : novaMjesta) {
                if (narudzba.getMjesto_id() == mjesto.getId())
                    cbNarudzbaMjesto.getSelectionModel().select(mjesto.getNaziv());
            }
            for (Proizvod proizvod : noviProizvodi) {
                if (proizvod.getId() == narudzba.getProizvod_id())
                    tfNarudzbaKategorija.setText(proizvod.getKategorija());
            }
            for(String s:vrste){
                if(narudzba.getVrsta().equals(s)) cbVrstaNarudzba.getSelectionModel().select(s);
            }
        }
    }
}
