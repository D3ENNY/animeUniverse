package controller;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import motor.List;
import motor.Interfaccia;
import motor.Motor;
import object.Anime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Interfaccia, Initializable {

    private final List lst = new List();
    private final Motor mt = new Motor();

    private File selectedFile;

    @FXML
    private TextArea title;

    @FXML
    private TextArea studio;

    @FXML
    private TextArea mangaka;

    @FXML
    private TextArea release;

    @FXML
    private TextArea season;

    @FXML
    private TextArea episodes;

    @FXML
    private TextArea plot;

    @FXML
    private GridPane animeInput;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField search;


    @FXML
    void addElement(MouseEvent event) throws IOException {
        checkAddElement();
        lst.getAnimeList().clear();
        insertCard();

    }

    @FXML
    void deletElement(MouseEvent event) throws IOException {
        boolean isDelete =  mt.deleteFromFile(lst);
       if (!isDelete){
           Alert errorAlert = new Alert(Alert.AlertType.ERROR);
           errorAlert.setHeaderText("insufficient arguments");
           errorAlert.setContentText("please selected the anime you want delete");
           errorAlert.showAndWait();
       }
       lst.getAnimeList().clear();
       insertCard();

    }

    @FXML
    void fileManager(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(defaultImagePath));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All image", "*.png","*.jpeg","*.jpg","*.webp"),
                new FileChooser.ExtensionFilter("png File","*.png"),
                new FileChooser.ExtensionFilter("jpeg file", "*.jpeg"),
                new FileChooser.ExtensionFilter("jpg file","*.jpg"),
                new FileChooser.ExtensionFilter("webp file","*.webp")
        );

        selectedFile = fc.showOpenDialog(null);

    }


    @FXML
    void logOut(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(loginPath));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage st = (Stage) title.getScene().getWindow();
        st.close();

    }

    @FXML
    void normalSort(MouseEvent event) {
        lst.normal_Sort();
        insertCard();

    }

    @FXML
    void descendSort(MouseEvent event) {
        lst.zA_Sort();
        insertCard();

    }

    @FXML
    void ascendSort(MouseEvent event) {
        lst.aZ_Sort();
        insertCard();

    }

    @FXML
    void searchAnime(KeyEvent event) {
        mt.searchAnime(search.getText(), lst);
        insertCard();

    }

    private void checkAddElement() throws IOException {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        if(!release.getText().matches(numberRegex) && !release.getText().isEmpty() ||
                !season.getText().matches(numberRegex) && !season.getText().isEmpty() ||
                !episodes.getText().matches(numberRegex) && !episodes.getText().isEmpty()) {

            errorAlert.setHeaderText("incorrect parameters");
            errorAlert.setContentText("please enter an integer value for the date of release, or the number of the season, or the number of the episode");
            errorAlert.showAndWait();

        }else if(!title.getText().isEmpty() &&
                !studio.getText().isEmpty() &&
                !mangaka.getText().isEmpty() &&
                !release.getText().isEmpty() &&
                !season.getText().isEmpty() &&
                !episodes.getText().isEmpty() &&
                !plot.getText().isEmpty()){

            Anime an = new Anime();
            an.setTitle(title.getText());
            an.setStudio(studio.getText());
            an.setMangaka(mangaka.getText());
            an.setPlot(plot.getText());
            an.setRelease(Integer.parseInt(release.getText()));
            an.setSeason(Integer.parseInt(season.getText()));
            an.setEpisodes(Integer.parseInt(episodes.getText()));
            if(selectedFile != null) {
                FileInputStream in = new FileInputStream(selectedFile.getAbsoluteFile());
                FileOutputStream ou = new FileOutputStream("src/"+imagePath+an.getTitle()+".png");
                BufferedInputStream bin  = new BufferedInputStream(in);
                BufferedOutputStream bou = new BufferedOutputStream(ou);
                int x = 0;
                while(x != -1) {
                    x = bin.read();
                    bou.write(x);
                }
                bin.close();
                bou.close();
                an.setImage(imagePath+an.getTitle()+".png");
                lst.getImage().put(an.getTitle(),selectedFile.getAbsolutePath());
            }else
                System.out.println("file is not valid");
            lst.getAnimeList().add(an);
            mt.writeFile(lst.getAnimeList());

            release.clear(); studio.clear(); mangaka.clear(); title.clear(); season.clear(); episodes.clear(); plot.clear();
        }else{
            errorAlert.setHeaderText("missing inputs");
            errorAlert.setContentText("please enter all data and continue");
            errorAlert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      insertCard();
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(cardAdminPath));
                anchorPane = fxmlLoader.load();
                CardAdminController cardController = fxmlLoader.getController();

                cardController.setData(a, mt, lst.getImage());

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