package inventura;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.assertj.core.internal.bytebuddy.matcher.CollectionOneToOneMatcher;

import java.lang.reflect.Method;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/main.fxml"));
        Controller controller = new Controller();
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setTitle("Inventura");
        primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.setResizable(true);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
