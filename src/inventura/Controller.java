package inventura;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Controller {



    public TableView tbProizvod;
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
    public TableView tvProstor;
    public TableColumn tbProstorId;
    public TableColumn tbProstorNaziv;
    public TableColumn tbProstorLokacija;
    public TableColumn tbProstorOpis;
    public TableColumn tbNarudzbaId;
    public TableColumn tbNarudzbaKategorija;
    public TableColumn tbNarudzbaVrsta;
    public TableColumn tbNarudzbaDatum;
    public TableColumn tbNarudzbaOpis;
    private InventuraDAO dao;

    public Controller(){
        dao = InventuraDAO.getInstance();
    }
    public void dodajProizvod(ActionEvent actionEvent) {

        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/proizvod.fxml"));
            root = loader.load();
            stage.setTitle("Grad");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(true);
            stage.show();

            stage.setOnHiding(event ->{

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void urediProizvod(ActionEvent actionEvent) {

    }

    public void obrisiProizvod(ActionEvent actionEvent) {

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
            root = loader.load();
            stage.setTitle("Grad");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void urediProstor(ActionEvent actionEvent) {

    }

    public void obrisiProstor(ActionEvent actionEvent) {

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

    public void urediNarduzba(ActionEvent actionEvent) {

    }
}
