package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import motor.Interfaccia;

import java.io.IOException;

public class LoginController implements Interfaccia {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void enter(MouseEvent event) throws IOException {

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        Stage st = (Stage) username.getScene().getWindow();
        if (!username.getText().isEmpty() && !password.getText().isEmpty()) {

            if (username.getText().equals(adminUser) && password.getText().equals(adminPassword)) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(adminPath));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                stage.getIcons().add(new Image(faviconPath));
                st.close();
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(userPath));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                st.close();
            }
        }else{
            errorAlert.setHeaderText("missing inputs");
            errorAlert.setContentText("please enter all data and continue");
            errorAlert.showAndWait();
        }

    }

}
