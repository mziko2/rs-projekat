package inventura;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ProizvodController {
    public TextField tfNazivProizvod;
    public ChoiceBox cbVrstaProizvod;
    public ChoiceBox cbMjestoProizvod;
    public DatePicker dpDatumProizvod;

    public ProizvodController(TextField tfNazivProizvod, ChoiceBox cbVrstaProizvod, ChoiceBox cbMjestoProizvod, DatePicker dpDatumProizvod) {
        this.tfNazivProizvod = tfNazivProizvod;
        this.cbVrstaProizvod = cbVrstaProizvod;
        this.cbMjestoProizvod = cbMjestoProizvod;
        this.dpDatumProizvod = dpDatumProizvod;
    }

    public void potvrdiProizvod(ActionEvent actionEvent) {
    }

    public void odbaciProizvod(ActionEvent actionEvent) {

    }
}
