package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import motor.Interfaccia;
import object.Anime;

public class AnimeController implements Interfaccia {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView animeImg;

    @FXML
    private Text episode;

    @FXML
    private Text mangaka;

    @FXML
    private Text plot;

    @FXML
    private Text release;

    @FXML
    private Text season;

    @FXML
    private Text studio;

    @FXML
    private Text title;

    public void backPage(javafx.scene.input.MouseEvent mouseEvent) {
        Stage st = (Stage) title.getScene().getWindow();
        st.close();

    }

    protected void insertData(Anime an) {

        Image img;
        try {
            img = new Image(an.getImage());
        }catch (Exception e){
            img = new Image(palceHolderPath);
        }

        animeImg.setImage(img);
        title.setText(an.getTitle());
        studio.setText(an.getStudio());
        mangaka.setText(an.getMangaka());
        release.setText(String.valueOf(an.getRelease()));
        season.setText(String.valueOf(an.getSeason()));
        episode.setText(String.valueOf(an.getEpisodes()));
        plot.setText(an.getPlot());

    }

}
