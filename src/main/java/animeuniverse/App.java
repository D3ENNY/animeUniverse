package animeuniverse;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import motor.Interfaccia;

import java.io.IOException;
import java.util.Objects;

public class App extends Application implements Interfaccia {
    LoginController lc = new LoginController();

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(loginPath)));
        Scene scene = new Scene(root);
        stage.setTitle("Anime Universe");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(false);
    }

    public static void main(String[] args) {
        launch();
    }
}