package controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import motor.List;
import motor.Motor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import motor.Interfaccia;
import object.Anime;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Interfaccia, Initializable {

    private Motor mt = new Motor();
    private List lst = new List();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane animeInput;

    @FXML
    private TextField search;

    @FXML
    void normalSort(javafx.scene.input.MouseEvent event) {
        lst.normal_Sort();
        insertCard();
    }

    @FXML
    void descendSort(javafx.scene.input.MouseEvent event) {
        lst.zA_Sort();
        insertCard();
    }

    @FXML
    void ascendSort(MouseEvent event) {
        lst.aZ_Sort();
        insertCard();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lst.getAnimeList().clear();
        insertCard();
    }

    @FXML
    void searchAnime(KeyEvent event) {
        mt.searchAnime(search.getText(), lst);
        insertCard();

    }

    public void logOut(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(loginPath));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage st = (Stage) anchorPane.getScene().getWindow();
        st.close();
    }

    void insertCard(){
        animeInput.getChildren().clear();

        if(lst.getAnimeList().isEmpty())
        try {
            mt.query(lst);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int column = 1;
        int row = 1;
        try {
            for (Anime a : lst.getAnimeList()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(cardUserPath));
                anchorPane = fxmlLoader.load();
                CardUserController cardController = fxmlLoader.getController();
                cardController.setData(a, lst.getImage());

                if (column == 4) {
                    column = 1;
                    row++;
                }
                animeInput.add(anchorPane, column++, row);
                //set grid width
                animeInput.setMinWidth(Region.USE_COMPUTED_SIZE);
                animeInput.setPrefWidth(Region.USE_COMPUTED_SIZE);
                animeInput.setMaxWidth(Region.USE_PREF_SIZE);
                //set animeInput height
                animeInput.setMinHeight(Region.USE_COMPUTED_SIZE);
                animeInput.setPrefHeight(Region.USE_COMPUTED_SIZE);
                animeInput.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
