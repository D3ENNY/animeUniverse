package motor;

public interface Interfaccia {

    /*  regex */
    String numberRegex = "[0-9]+[.]?[0-9]*";
    String formatRegex = " &#& ";

    /* path */
    String path = "database.txt";
    String loginPath = "../gui/Login.fxml";
    String imagePath = "assets/animeImage/";
    String defaultImagePath="src/assets/testImage";
    String systemImagePath="assets/systemImage/";
    String palceHolderPath=systemImagePath+"placeholder.png";
    String cardAdminPath = "../gui/CardAdmin.fxml";
    String adminPath = "../gui/Admin.fxml";
    String userPath = "../gui/User.fxml";
    String cardUserPath = "../gui/CardUser.fxml";
    String animePath = "../gui/Anime.fxml";
    String faviconPath = systemImagePath+"favicon.png";

    /* color */
    String btnPress = "-fx-background-color: #524a7b; ";
    String btnDefault = "-fx-background-color : #393351";

    /* login */
    String adminUser = "Root";
    String adminPassword = "admin";


}
