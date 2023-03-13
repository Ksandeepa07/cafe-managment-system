package lk.ijse.cafe_au_lait.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private ComboBox chooseOption;

    @FXML
    void initialize() {
        assert usernameTxt != null : "fx:id=\"usernameTxt\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert passwordTxt != null : "fx:id=\"passwordTxt\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert chooseOption != null : "fx:id=\"chooseOption\" was not injected: check your FXML file 'loginPage.fxml'.";
        chooseOption.getItems().addAll("Admin", "Cashier");
    }
}
