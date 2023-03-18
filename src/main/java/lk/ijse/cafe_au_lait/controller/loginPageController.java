package lk.ijse.cafe_au_lait.controller;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.swing.text.html.ImageView;

public class loginPageController {
    String username= "kaveen";
    String password="1234";

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


    public void dontHaveClick(ActionEvent actionEvent) throws IOException {
//        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.cafe_au_lait.view/signUp.fxml"));
//        ancPane.getChildren().clear();
//        ancPane.getChildren().add(load);


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.cafe_au_lait.view/signUp.fxml"));
        Parent load = fxmlLoader.load();
        ancPane.getChildren().setAll(load);

            // Fade out old scene
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1));
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.play();

            // Slide in new scene
            TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), load);

            slideIn.setFromY(load.getTranslateX() - 300);
            slideIn.setToY(load.getTranslateX());
            slideIn.play();

            // Remove old scene from parent after fade out is completed
            fadeOut.setOnFinished((event) -> {
                ancPane.getChildren().remove(0);
            });
        }

    public void forgotPasswordClick(ActionEvent actionEvent) throws IOException {
        loginBtn.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.cafe_au_lait.view/forgotPassword.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Password recovery page");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void loginClick() throws IOException {
        if( chooseOption.getValue().equals("Admin")){
            NotificationController.loginAnimation();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                loginBtn.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.cafe_au_lait.view/admindashbord.fxml"));
                Parent root1 = null;
                try {
                    root1 = (Parent) fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("ABC");
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.show();

            }));
            timeline.play();

        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"wrong details").show();
        }

    }

    @FXML
    void initialize() {

        assert usernameTxt != null : "fx:id=\"usernameTxt\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert passwordTxt != null : "fx:id=\"passwordTxt\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert chooseOption != null : "fx:id=\"chooseOption\" was not injected: check your FXML file 'loginPage.fxml'.";
        chooseOption.getItems().addAll("Admin", "Cashier");

    }



}
