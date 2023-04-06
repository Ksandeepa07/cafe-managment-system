package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.dto.User;
import lk.ijse.cafe_au_lait.model.UserModel;
import lk.ijse.cafe_au_lait.util.AnimationController;
import lk.ijse.cafe_au_lait.util.NotificationController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController {

    @FXML
    public AnchorPane ancPane;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
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

    @FXML
    private ComboBox jobTitlte;

    public void alreadyHaveAnAccountClick(ActionEvent actionEvent) throws IOException {
        AnimationController.fadeUpAnimation("/view/loginPage.fxml", ancPane);
    }

    public void signClick(ActionEvent actionEvent) {
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        String email = emailTxt.getText();
        String jobTitle = (String) jobTitlte.getValue();

        User user = new User(username, password, email, jobTitle);

        if (passwordTxt.getText().equals(confirmPassword.getText())) {
            boolean isSaved = false;
            try {
                isSaved = UserModel.save(user);
                if (isSaved) {
                    NotificationController.animationMesseage("/assets/tick.gif", "sign up", "" +
                            "Sign Up Sucessfull !");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            NotificationController.ErrorMasseage("Password dont match.Please check your password!!");
        }

    }

    @FXML
    void initialize() {
        jobTitlte.getItems().addAll("Admin", "Cashier");

    }


}
