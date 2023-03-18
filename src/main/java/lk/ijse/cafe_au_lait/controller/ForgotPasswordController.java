package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    int otp;

    @FXML
    void sendCodeClick(ActionEvent event) throws Exception {
        Random random=new Random();
        otp=random.nextInt(9000);
        otp+=1000;

        try {
            EmailController.sendEmail(Emailtxtt.getText(), "cafe au lait verification", otp+"");
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        submitButton.setVisible(true);
        otpLbl.setVisible(true);
        otpTxt.setVisible(true);

    }

    @FXML
    void submmitCodeClick(ActionEvent event) throws IOException {
        if(String.valueOf(otp).equals(otpTxt.getText()) ){

            EmailTxt.setText("Enter your new password");
            otpLbl.setText("Confirm new Password");
            sendCodeBtn.setVisible(false);
        if(submitButton.getText().equals("Save")){
            recoveryAnchorpane.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.cafe_au_lait.view/loginPage.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();

        }

        }else{
          new Alert(Alert.AlertType.ERROR,"wrong otp").show();
          submitButton.setText("submit");

        }
        submitButton.setText("Save");



    }

    @FXML
    void initialize() {
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
