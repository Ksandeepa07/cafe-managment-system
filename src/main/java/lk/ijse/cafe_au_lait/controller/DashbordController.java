package lk.ijse.cafe_au_lait.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class DashbordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
     private Label timeLbl;

    @FXML
     private  Label datLbl;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton homeBtn;

    @FXML
    private JFXButton employeeBtn;

    @FXML
    private JFXButton supplierBtn;

    @FXML
    private JFXButton inventoryBtn;

    @FXML
    private JFXButton ordersBtn;

    @FXML
    private JFXButton reportsBtn;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private Label lblStatus;
    int x=0;



    private  void Timenow(){
        Thread thread =new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
           SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM,  dd, yyyy");
            while (true){
                try{
                    Thread.sleep(1000);

                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                String timenow1 = sdf1.format(new Date());

                Platform.runLater(() ->{
                    timeLbl.setText(timenow);
                    datLbl.setText(timenow1);
                });
            }
        });
        thread.start();
    }

    public void homeBtnClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.cafe_au_lait.view/adminhome.fxml"));
        ancPane.getChildren().clear();
        ancPane.getChildren().add(load);

    }

    public void employeeBtnClick(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.cafe_au_lait.view/adminEmployee.fxml"));
        ancPane.getChildren().clear();
        ancPane.getChildren().add(load);
    }

    @FXML
    void supplierBtnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.cafe_au_lait.view/adminSupplier.fxml"));
        ancPane.getChildren().clear();
        ancPane.getChildren().add(load);

    }

    @FXML
    void inventoryBtnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.cafe_au_lait.view/adminInventoy.fxml"));
        ancPane.getChildren().clear();
        ancPane.getChildren().add(load);

    }

    @FXML
    void ordersBtnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.cafe_au_lait.view/adminOrders.fxml"));
        ancPane.getChildren().clear();
        ancPane.getChildren().add(load);
    }

    @FXML
    void reportsBttnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.cafe_au_lait.view/adminReports.fxml"));
        ancPane.getChildren().clear();
        ancPane.getChildren().add(load);

    }


    @FXML
    void initialize() {

        Timenow();
        assert timeLbl != null : "fx:id=\"timeLbl\" was not injected: check your FXML file 'admindashbord.fxml'.";

    }


}
