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

public class ProizvodController implements Initializable {
    public TextField tfNazivProizvod;
    public ChoiceBox<String> cbVrstaProizvod = new ChoiceBox<>();
    public ChoiceBox<String> cbMjestoProizvod = new ChoiceBox<String>();
    public DatePicker dpDatumProizvod;
    public ObservableList<String> listMjesto = FXCollections.observableArrayList();
    public ObservableList<String> listKategorije = FXCollections.observableArrayList();
    public Proizvod proizvod;
    private ArrayList<Mjesto> novaMjesta;

    public ProizvodController(Proizvod proizvod, ArrayList<Mjesto> mjesta, ArrayList<String> kategorija){
        this.proizvod=proizvod;
        //listMjesto = FXCollections.observableArrayList(mjesta);
        //listKategorije = FXCollections.observableArrayList(kategorija);
        for(Mjesto mjesto:mjesta){
            listMjesto.add(mjesto.getNaziv());
        }
        listKategorije.setAll(kategorija);
        this.novaMjesta=mjesta;
    }
    public ProizvodController(){

    }
    public Proizvod getProizvod() { return  proizvod; }

    public void potvrdiProizvod(ActionEvent actionEvent) {
        if(proizvod==null) proizvod = new Proizvod();
        proizvod.setNaziv(tfNazivProizvod.getText());
        proizvod.setKategorija(cbVrstaProizvod.getSelectionModel().getSelectedItem().toString());
        proizvod.setMjesto(cbMjestoProizvod.getSelectionModel().getSelectedItem().toString());
        proizvod.setDatum(dpDatumProizvod.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        for(Mjesto mjesto:novaMjesta){
            if(mjesto.getNaziv().equals(cbMjestoProizvod.getSelectionModel().getSelectedItem())) proizvod.setMjesto_id(mjesto.getId());
        }
        Stage stage = (Stage) tfNazivProizvod.getScene().getWindow();
        stage.close();
    }

    public void odbaciProizvod(ActionEvent actionEvent) {
        proizvod=null;
        Stage stage = (Stage) tfNazivProizvod.getScene().getWindow();
        stage.close();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cbMjestoProizvod.setItems(listMjesto);
        cbVrstaProizvod.setItems(listKategorije);

        if(proizvod != null){
            tfNazivProizvod.setText(proizvod.getNaziv());
            DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().append(DateTimeFormatter.ofPattern("yyyy-MM-d")).toFormatter();
            dpDatumProizvod.setValue(LocalDate.parse(proizvod.getDatum(),df));
            for(String mjesto : listMjesto){
                if(mjesto.equals(proizvod.getMjesto())) cbMjestoProizvod.getSelectionModel().select(mjesto);
            }
            for(String s:listKategorije){
                if(proizvod.getKategorija().equals(s)) cbVrstaProizvod.getSelectionModel().select(s);
            }
        }

    }


}
