package inventura;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Controller implements Initializable {



    public TableView<Proizvod> tbProizvod;
    public TableColumn tbProizvodId;
    public TableColumn tbProizvodNaziv;
    public TableColumn tbProizvodKategorija;
    public TableColumn tbProizvodProstor;
    public TableColumn tbProizvodDatum;
    public ComboBox cbFiltrirajKategorija;
    public ComboBox cbFiltriraj;
    public ComboBox cbSortiraj;
    public ImageView ivSlika;
    public TextField tfPretragaProstor;
    public TableView<Mjesto> tvProstor;
    public TableColumn tbProstorId;
    public TableColumn tbProstorNaziv;
    public TableColumn tbProstorLokacija;
    public TableColumn tbProstorOpis;
    public TableColumn tbNarudzbaId;
    public TableColumn tbNarudzbaKategorija;
    public TableColumn tbNarudzbaVrsta;
    public TableColumn tbNarudzbaDatum;
    public TableColumn tbNarudzbaOpis;
    public TableView<Narudzba> tvNarudzba;
    public TableColumn tbNarudzbaProizvod;
    private InventuraDAO dao;
    private ObservableList<Proizvod> listProizvod;
    private ObservableList<Mjesto> listMjesto;
    private ObservableList<Narudzba> listNarudzba;

    public Controller(){
        dao = InventuraDAO.getInstance();
        listProizvod= FXCollections.observableArrayList(dao.proizvodi());
        listMjesto=FXCollections.observableArrayList(dao.mjesta());
        listNarudzba=FXCollections.observableArrayList(dao.narudzbe());

    }
    public void dodajProizvod(ActionEvent actionEvent) {

        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/proizvod.fxml"));
            ProizvodController proizvodController = new ProizvodController(null,dao.mjesta(),dao.dajKategorijeProizvoda());
            loader.setController(proizvodController);
            root = loader.load();
            stage.setTitle("Grad");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(true);
            stage.show();

            stage.setOnHiding(event ->{
                Proizvod proizvod = proizvodController.getProizvod();
                if(proizvod!=null){
                    proizvod.setMjesto_id(dao.dajIdMjesta(proizvod.getMjesto()));
                    dao.dodajProizvod(proizvod);
                    listProizvod.setAll(dao.proizvodi());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    public void urediProizvod(ActionEvent actionEvent) {
        Proizvod proizvod = tbProizvod.getSelectionModel().getSelectedItem();
        if(proizvod==null) return;
        Stage stage = new Stage();
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/proizvod.fxml"));
            ProizvodController proizvodController = new ProizvodController(proizvod,dao.mjesta(), dao.dajKategorijeProizvoda());
            loader.setController(proizvodController);
            root=loader.load();
            stage.setTitle("Izmjena proizvoda");
            stage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            stage.show();

            stage.setOnHiding(event ->{
                Proizvod noviProizvod=proizvodController.getProizvod();
                if(noviProizvod!=null){
                    try{
                        dao.izmjeniProizvod(noviProizvod);
                        listProizvod.setAll(dao.proizvodi());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void obrisiProizvod(ActionEvent actionEvent) {
        Proizvod proizvod = tbProizvod.getSelectionModel().getSelectedItem();
        if(proizvod==null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda brisanja");
        alert.setHeaderText("Brisanje proizvoda "+proizvod.getNaziv());
        alert.setContentText("Da li ste sigurni da želite obrisati proizvod "+proizvod.getNaziv()+"?");
        alert.setResizable(true);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==ButtonType.OK){
            dao.obrisiProizvod(proizvod.getId());
            listProizvod.setAll(dao.proizvodi());
        }
    }

    public void dodajSlikuProizvod(ActionEvent actionEvent) {

        JFileChooser fileChooser= new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);

    }

    public void dodajProstor(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mjesto.fxml"));
            MjestoController mjestoController = new MjestoController(null);
            loader.setController(mjestoController);
            root = loader.load();
            stage.setTitle("Grad");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(true);
            stage.show();
            stage.setOnHiding(event ->{
                Mjesto mjesto = mjestoController.getMjesto();
                if(mjesto!=null){
                    dao.dodajMjesto(mjesto);
                    listMjesto.setAll(dao.mjesta());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void urediProstor(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        Mjesto mjesto = tvProstor.getSelectionModel().getSelectedItem();
        if(mjesto==null) return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mjesto.fxml"));
            MjestoController mjestoController = new MjestoController(mjesto);
            loader.setController(mjestoController);
            root = loader.load();
            stage.setTitle("Grad");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(true);
            stage.show();
            stage.setOnHiding(event ->{
                Mjesto novoMjesto = mjestoController.getMjesto();
                if(novoMjesto!=null){
                    try {
                        dao.izmjeniMjesto(novoMjesto);
                        listMjesto.setAll(dao.mjesta());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void obrisiProstor(ActionEvent actionEvent) {
        Mjesto mjesto = tvProstor.getSelectionModel().getSelectedItem();
        if(mjesto==null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda brisanja");
        alert.setHeaderText("Brisanje mjesta "+mjesto.getNaziv());
        alert.setContentText("Da li ste sigurni da želite obrisati mjesto "+mjesto.getNaziv()+"?");
        alert.setResizable(true);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==ButtonType.OK){
            dao.obrisiMjesto(mjesto.getId());
            listMjesto.setAll(dao.mjesta());
        }
    }

    public void dodajNarudzba(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/narudzba.fxml"));
            root = loader.load();
            stage.setTitle("Grad");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void urediNarduzba(ActionEvent actionEvent) throws SQLException {

    }


    public void obrisiNarudzba(ActionEvent actionEvent) {
        Narudzba narudzba = tvNarudzba.getSelectionModel().getSelectedItem();
        if(narudzba==null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda brisanja");
        alert.setHeaderText("Brisanje narudzbe proizvoda "+narudzba.getProizvod());
        alert.setContentText("Da li ste sigurni da želite obrisati narudzbu za proizvod "+narudzba.getProizvod()+"?");
        alert.setResizable(true);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==ButtonType.OK){
            dao.obrisiNarudzbu(narudzba.getId());
            listNarudzba.setAll(dao.narudzbe());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dao.vratiBazuNaDefault();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tbProizvod.setItems(listProizvod);
        tbProizvodId.setCellValueFactory(new PropertyValueFactory("id"));
        tbProizvodNaziv.setCellValueFactory(new PropertyValueFactory("naziv"));
        tbProizvodKategorija.setCellValueFactory(new PropertyValueFactory("kategorija"));
        tbProizvodDatum.setCellValueFactory(new PropertyValueFactory("datum"));
        tbProizvodProstor.setCellValueFactory(new PropertyValueFactory("mjesto"));
        tvProstor.setItems(listMjesto);
        tbProstorId.setCellValueFactory(new PropertyValueFactory("id"));
        tbProstorNaziv.setCellValueFactory(new PropertyValueFactory("naziv"));
        tbProstorLokacija.setCellValueFactory(new PropertyValueFactory("lokacija"));
        tbProstorOpis.setCellValueFactory(new PropertyValueFactory("opis"));
        tvNarudzba.setItems(listNarudzba);
        tbNarudzbaId.setCellValueFactory(new PropertyValueFactory("id"));
        tbNarudzbaProizvod.setCellValueFactory(new PropertyValueFactory("proizvod"));
        tbNarudzbaVrsta.setCellValueFactory(new PropertyValueFactory("vrsta"));
        tbNarudzbaOpis.setCellValueFactory(new PropertyValueFactory("opis"));
        tbNarudzbaDatum.setCellValueFactory(new PropertyValueFactory("datum"));

    }

}
