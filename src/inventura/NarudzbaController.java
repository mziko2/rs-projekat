package inventura;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    public Proizvod proizvod;
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
        boolean nasao = false;
        if(narudzba==null) narudzba = new Narudzba();
        narudzba.setProizvod(tfProizvodNarudzba.getText());
        narudzba.setOpis(tfOpisNarudzba.getText());
        narudzba.setVrsta(cbVrstaNarudzba.getSelectionModel().getSelectedItem());
        narudzba.setDatum(dpDatumNarudzba.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        for(Mjesto mjesto:novaMjesta){
            if(cbNarudzbaMjesto.getSelectionModel().getSelectedItem().equals(mjesto.getNaziv())) narudzba.setMjesto_id(mjesto.getId());
        }
        for(Proizvod proizvod:noviProizvodi) {
            if (narudzba.getProizvod().equals(proizvod.getNaziv())) {
                nasao = true;
                break;
            }
        }
            if(nasao){
                proizvod.setNaziv(tfProizvodNarudzba.getText());
                proizvod.setKategorija(tfNarudzbaKategorija.getText());
                proizvod.setDatum(dpDatumNarudzba.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                proizvod.setMjesto(cbNarudzbaMjesto.getSelectionModel().getSelectedItem());
            }

    }
    public Narudzba getNarudzba(){return narudzba; }
    public Proizvod getNoviProizvodIzNarudzbe() {return proizvod; }

    public void odbaciNarudzba(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbNarudzbaMjesto.setItems(listMjesto);
        vrste.addAll("Kupovina","Prodaja","Posudba");
        cbVrstaNarudzba.setItems(vrste);
        if(narudzba!=null){
            tfProizvodNarudzba.setText(narudzba.getProizvod());
            tfOpisNarudzba.setText(narudzba.getOpis());
            DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().append(DateTimeFormatter.ofPattern("yyyy-MM-d")).toFormatter();
            dpDatumNarudzba.setValue(LocalDate.parse(narudzba.getDatum(),df));
        }
        for(Mjesto mjesto: novaMjesta){
            if(narudzba.getMjesto_id()==mjesto.getId()) cbNarudzbaMjesto.getSelectionModel().select(mjesto.getNaziv());
        }
        for(Proizvod proizvod : noviProizvodi){
            if(proizvod.getId()==narudzba.getProizvod_id()) tfNarudzbaKategorija.setText(proizvod.getKategorija());
        }
    }
}
