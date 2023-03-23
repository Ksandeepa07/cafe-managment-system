package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.cj.util.StringUtils;
import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.UserModel;
import util.AnimationController;
import util.NotificationController;

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
    private TextField emailTxt;


    @FXML
    private ComboBox<?> chooseOption;

    @FXML
    private JFXButton signBtn;

    @FXML
    private Hyperlink alreadyHaveAnAccount;

    @FXML
    private PasswordField confirmPassword;

    public void alreadyHaveAnAccountClick(ActionEvent actionEvent) throws IOException {
        AnimationController.fadeUpAnimation("/lk.ijse.cafe_au_lait.view/loginPage.fxml", ancPane);
    }

    public void signClick(ActionEvent actionEvent) {
        String username=usernameTxt.getText();
        String password=passwordTxt.getText();
        String email=emailTxt.getText();

        User user=new User(username,password,email);
        try {
            if(passwordTxt.getText().equals(confirmPassword.getText())) {
                boolean isSaved = UserModel.save(user);
                if(isSaved) {
                    System.out.println("saved");
                }
            }else{
                NotificationController.ErrorMasseage("Password dont match.Please check your password!!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert ancPane != null : "fx:id=\"ancPane\" was not injected: check your FXML file 'signUp.fxml'.";
        assert usernameTxt != null : "fx:id=\"usernameTxt\" was not injected: check your FXML file 'signUp.fxml'.";
        assert passwordTxt != null : "fx:id=\"passwordTxt\" was not injected: check your FXML file 'signUp.fxml'.";
        assert chooseOption != null : "fx:id=\"chooseOption\" was not injected: check your FXML file 'signUp.fxml'.";
        assert alreadyHaveAnAccount != null : "fx:id=\"alreadyHaveAnAccount\" was not injected: check your FXML file 'signUp.fxml'.";

    }


}
