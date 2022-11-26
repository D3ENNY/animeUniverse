package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import motor.Interfaccia;
import motor.Motor;
import object.Anime;

import java.util.Map;

public class CardAdminController implements Interfaccia {

    Motor mt;
    private int cntClick=0;

    @FXML
    private ImageView animeImage;

    @FXML
    private Text animeTitle;

    @FXML
    private Button btn;

    @FXML
    void takeAnime(MouseEvent event) {
        cntClick++;
        mt.setSelectedAnime(animeTitle.getText());
        if(cntClick==1)
            btn.setStyle(btnPress);
        else{
            btn.setStyle(btnDefault);
            cntClick = 0;
        }

    }

    public void setData(Anime an, Motor mt, Map<String, String> image){
        this.mt=mt;
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
