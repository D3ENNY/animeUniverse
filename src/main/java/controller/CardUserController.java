package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import motor.Interfaccia; 

import object.Anime;

import java.io.IOException;
import java.util.Map;

public class CardUserController implements Interfaccia {

    Anime an;

    @FXML
    private ImageView animeImage;

    @FXML
    private Text animeTitle;

    @FXML
    private Button btn;

    @FXML
    void selectAnime(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(animePath));
        Parent root = fxmlLoader.load();
        AnimeController ac = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        ac.insertData(an);
        stage.show();
    }

    public void setData(Anime an, Map<String, String> image){
        this.an=an;

        Image img=new Image(palceHolderPath);
        try {
            img = new Image(an.getImage());
        }catch (Exception e){
            try {
                for (String i :image.keySet()) {
                    if(i.equals(an.getTitle()))
                        img=new Image(image.get(i));
                }
            }catch(Exception k){
                img = new Image(palceHolderPath);
            }
        }

        animeImage.preserveRatioProperty();
        animeImage.setSmooth(false);

        animeImage.setImage(img);

        animeTitle.setText(an.getTitle());

    }
}
