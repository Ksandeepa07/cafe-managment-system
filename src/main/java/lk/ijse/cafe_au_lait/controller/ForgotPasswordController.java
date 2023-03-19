package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.mysql.cj.conf.IntegerProperty;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.mail.MessagingException;

public class ForgotPasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton sendCodeBtn;

    @FXML
    private TextField otpTxt;

    @FXML
    private JFXButton submitButton;

    @FXML
    private Label otpLbl;

    @FXML
    private Label EmailTxt;

    @FXML
    private TextField Emailtxtt;

    @FXML
    private AnchorPane recoveryAnchorpane;

    @FXML
    private JFXButton submitButton1;

    @FXML
    private Label countLbl;

    int otp;

//    private static Timeline timeline;
//    private static SimpleIntegerProperty timeSeconds = new SimpleIntegerProperty();
//    private static final int START_TIME = 30;


    @FXML
    void sendCodeClick(ActionEvent event) throws Exception {
        AnimationController.updateTime(sendCodeBtn,countLbl);

        if(DataValidateController.emailCheck(Emailtxtt.getText())){
            try {

//                sendCodeBtn.setDisable(true);
//                timeSeconds.set(START_TIME);
//                timeline = new Timeline();
//                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(START_TIME+1),
//                        new KeyValue(timeSeconds, 0)));
//                timeline.setOnFinished(event1 -> {
//                    sendCodeBtn.setDisable(false);
//                    countLbl.setVisible(false);
//
//                });
//                timeline.playFromStart();
//                countLbl.textProperty().bind(timeSeconds.asString());



                submitButton.setVisible(true);
                otpLbl.setVisible(true);
                otpTxt.setVisible(true);

                Random random=new Random();
                otp=random.nextInt(9000);
                otp+=1000;
                EmailController.sendEmail(Emailtxtt.getText(), "cafe au lait verification", otp+"");
                System.out.println("Email sent successfully.");
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }else{
            NotificationController.ErrorMasseage("invalid e-mail address");
            submitButton.setVisible(false);
            otpLbl.setVisible(false);
            otpTxt.setVisible(false);
        }







    }






    @FXML
    void submmitCodeClick(ActionEvent event) throws IOException {
        if(String.valueOf(otp).equals(otpTxt.getText()) ){
            EmailTxt.setText("Enter your new password");
            otpLbl.setText("Confirm new Password");
            sendCodeBtn.setVisible(false);
            submitButton.setVisible(false);
            submitButton1.setVisible(true);
            otpTxt.setText(" ");
            Emailtxtt.setText(" ");

        }else{
            NotificationController.ErrorMasseage("Wrong OTP !");
            submitButton.setText("submit");

        }
    }
    @FXML
    void submmitCodeClick1(ActionEvent event) throws IOException {
        recoveryAnchorpane.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.cafe_au_lait.view/loginPage.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void initialize() {
        submitButton1.setVisible(false);
        submitButton.setVisible(false);
        otpLbl.setVisible(false);
        otpTxt.setVisible(false);
        assert sendCodeBtn != null : "fx:id=\"sendCodeBtn\" was not injected: check your FXML file 'forgotPassword.fxml'.";
        assert otpTxt != null : "fx:id=\"otpTxt\" was not injected: check your FXML file 'forgotPassword.fxml'.";
        assert submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'forgotPassword.fxml'.";
        assert otpLbl != null : "fx:id=\"otpLbl\" was not injected: check your FXML file 'forgotPassword.fxml'.";
        assert EmailTxt != null : "fx:id=\"EmailTxt\" was not injected: check your FXML file 'forgotPassword.fxml'.";

    }
}
