package lk.ijse.cafe_au_lait.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.AnimationController;
import util.NotificationController;
import util.StageController;

public class LoginPageController {

    String password;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button loginBtn;

    @FXML
    private ComboBox  chooseOption;

    @FXML
    public AnchorPane ancPane;

    @FXML
    private TextField txtShowPassword;

    @FXML
    private Button showPasswordBtn;

    @FXML
    private Button hidePasswordBtn;

    @FXML
    private ImageView showPasswordImage;

    @FXML
    private ImageView hidePasswordImage;


    public void dontHaveClick(ActionEvent actionEvent) throws IOException {
        AnimationController.fadeAnimation("/lk.ijse.cafe_au_lait.view/signUp.fxml", ancPane);
    }

    public void forgotPasswordClick(ActionEvent actionEvent) throws IOException {
        loginBtn.getScene().getWindow().hide();
        StageController.changeStage("/lk.ijse.cafe_au_lait.view/forgotPassword.fxml","Password recovery");
    }



    @FXML
    void loginClick() throws IOException {
        if( chooseOption.getValue().equals("Admin")){
            NotificationController.animationMesseage("/lk.ijse.cafe_au_lait.assets/loginCoffeeCup.gif","Login",
                    "Login succesfull !!");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                loginBtn.getScene().getWindow().hide();
                try {
                    StageController.changeStage("/lk.ijse.cafe_au_lait.view/admindashbord.fxml","Dashboard");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
            timeline.play();
        }else{
            NotificationController.animationMesseage("/lk.ijse.cafe_au_lait.assets/loginCoffeeCup.gif","Login","Login " +
                    "succesfull !!");

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                loginBtn.getScene().getWindow().hide();
                try {
                    StageController.changeStage("/lk.ijse.cafe_au_lait.view/cashierDashboard.fxml","Dashboard");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
            timeline.play();


        }
    }

    public void shiwPasswordClick(MouseEvent mouseEvent) {
        password=passwordTxt.getText();
        passwordTxt.setVisible(false);
        showPasswordImage.setVisible(false);
        txtShowPassword.setVisible(true);
        hidePasswordImage.setVisible(true);
        txtShowPassword.setText(password);
    }

    public void hidepasswordClick(MouseEvent mouseEvent) {
        password=txtShowPassword.getText();
        passwordTxt.setVisible(true);
        showPasswordImage.setVisible(true);
        txtShowPassword.setVisible(false);
        hidePasswordImage.setVisible(false);
        passwordTxt.setText(password);

    }


    @FXML
    void initialize() {

        txtShowPassword.setVisible(false);
        hidePasswordImage.setVisible(false);
        assert usernameTxt != null : "fx:id=\"usernameTxt\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert passwordTxt != null : "fx:id=\"passwordTxt\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert chooseOption != null : "fx:id=\"chooseOption\" was not injected: check your FXML file 'loginPage.fxml'.";
        chooseOption.getItems().addAll("Admin", "Cashier");

    }

}
