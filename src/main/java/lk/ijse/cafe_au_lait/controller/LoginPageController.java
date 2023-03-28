package lk.ijse.cafe_au_lait.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import lk.ijse.cafe_au_lait.dto.User;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.cafe_au_lait.model.UserModel;
import lk.ijse.cafe_au_lait.util.AnimationController;
import lk.ijse.cafe_au_lait.util.NotificationController;
import lk.ijse.cafe_au_lait.util.StageController;

public class LoginPageController {
    String username;
    String selectJob;
    String password=null;
    String jobTitle=null;



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
    private Hyperlink dontHaveAcoount;

    @FXML
    private Button showPasswordBtn;

    @FXML
    private Button hidePasswordBtn;

    @FXML
    private ImageView showPasswordImage;

    @FXML
    private ImageView hidePasswordImage;


    public void dontHaveClick(ActionEvent actionEvent) throws IOException {
        AnimationController.fadeAnimation("/view/signUp.fxml", ancPane);
    }

    public void forgotPasswordClick(ActionEvent actionEvent) throws IOException {
        loginBtn.getScene().getWindow().hide();
        StageController.changeStage("/view/forgotPassword.fxml","Password recovery");
    }



    @FXML
    void loginClick() throws IOException {
         username=usernameTxt.getText();
         selectJob= (String) chooseOption.getValue();
         password=null;
         jobTitle=null;
        try {
            User user=UserModel.SearchById(username);
            password=user.getPassword();
            jobTitle=user.getJobTitle();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(passwordTxt.getText().equals(password) && selectJob.equals(jobTitle)&& selectJob.equals("Cashier") ){
            NotificationController.animationMesseage("/assets/loginCoffeeCup.gif","Login",
                    "Login succesfull !!");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                loginBtn.getScene().getWindow().hide();
                try {
                    StageController.changeStage("/view/cashierDashboard.fxml","Dashboard");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
            timeline.play();
        }else if (passwordTxt.getText().equals(password) && selectJob.equals(jobTitle)&& selectJob.equals("Admin") ){
            NotificationController.animationMesseage("/assets/loginCoffeeCup.gif","Login","Login " +
                    "succesfull !!");

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                loginBtn.getScene().getWindow().hide();
                try {
                    StageController.changeStage("/view/admindashbord.fxml","Dashboard");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
            timeline.play();

        }else{
            NotificationController.animationMesseage("/assets/error.png","Error",
                    "Invalid details");
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

    public void passwordFieldEnter(KeyEvent keyEvent) {
        System.out.println("ok");
//        if (passwordTxt.getText().equals(password) && selectJob.equals(jobTitle)&& selectJob.equals("Admin") ){
//            dontHaveAcoount.setVisible(true);
//        }else{
//            dontHaveAcoount.setVisible(false);
//        }
    }


    @FXML
    void initialize() {
//        dontHaveAcoount.setVisible(false);
        txtShowPassword.setVisible(false);
        hidePasswordImage.setVisible(false);
        chooseOption.getItems().addAll("Admin", "Cashier");

    }



}
