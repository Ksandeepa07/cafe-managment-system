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

public class SignUpController {

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
        AnimationController.fadeUpAnimation("/lk.ijse.cafe_au_lait.view/loginPage.fxml", ancPane);
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
