package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class signUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public AnchorPane ancPane;

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private ComboBox<?> chooseOption;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private Hyperlink alreadyHaveAnAccount;

    public void alreadyHaveAnAccountClick(ActionEvent actionEvent) throws IOException {
//        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.cafe_au_lait.view/signUp.fxml"));
//        ancPane.getChildren().clear();
//        ancPane.getChildren().add(load);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.cafe_au_lait.view/loginPage.fxml"));
        Parent load = fxmlLoader.load();
        ancPane.getChildren().setAll(load);

        // Fade out old scene
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1));
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.play();

        // Slide in new scene
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), load);

        slideIn.setFromY(load.getTranslateX() + 300);
        slideIn.setToY(load.getTranslateX());
        slideIn.play();

        // Remove old scene from parent after fade out is completed
        fadeOut.setOnFinished((event) -> {
            ancPane.getChildren().remove(0);
        });
    }


    @FXML
    void loginClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert ancPane != null : "fx:id=\"ancPane\" was not injected: check your FXML file 'signUp.fxml'.";
        assert usernameTxt != null : "fx:id=\"usernameTxt\" was not injected: check your FXML file 'signUp.fxml'.";
        assert passwordTxt != null : "fx:id=\"passwordTxt\" was not injected: check your FXML file 'signUp.fxml'.";
        assert chooseOption != null : "fx:id=\"chooseOption\" was not injected: check your FXML file 'signUp.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'signUp.fxml'.";
        assert alreadyHaveAnAccount != null : "fx:id=\"alreadyHaveAnAccount\" was not injected: check your FXML file 'signUp.fxml'.";

    }
}
