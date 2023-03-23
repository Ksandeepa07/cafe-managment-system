package lk.ijse.cafe_au_lait.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.StageController;
import util.TimeController;

public class AdminDashbordController {

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

    public void homeBtnClick(javafx.event.ActionEvent actionEvent) throws IOException {
        StageController.changeScene("/lk.ijse.cafe_au_lait.view/adminhome.fxml",ancPane);
    }

    public void employeeBtnClick(ActionEvent actionEvent) throws IOException {
        StageController.changeScene("/lk.ijse.cafe_au_lait.view/adminEmployee.fxml",ancPane);
    }

    @FXML
    void supplierBtnClick(ActionEvent event) throws IOException {
        StageController.changeScene("/lk.ijse.cafe_au_lait.view/adminSupplier.fxml",ancPane);
    }

    @FXML
    void inventoryBtnClick(ActionEvent event) throws IOException {
        StageController.changeScene("/lk.ijse.cafe_au_lait.view/adminInventoy.fxml",ancPane);
    }

    @FXML
    void ordersBtnClick(ActionEvent event) throws IOException {
        StageController.changeScene("/lk.ijse.cafe_au_lait.view/adminOrders.fxml",ancPane);
    }

    @FXML
    void reportsBttnClick(ActionEvent event) throws IOException {
       StageController.changeScene("/lk.ijse.cafe_au_lait.view/adminReports.fxml",ancPane);
    }

    public void logoutClick(MouseEvent mouseEvent) throws IOException {
        anchorpane.getScene().getWindow().hide();
        StageController.changeStage("/lk.ijse.cafe_au_lait.view/loginPage.fxml","Login");
    }

    @FXML
    void initialize() {
        TimeController.timeNow(timeLbl,datLbl);
        assert timeLbl != null : "fx:id=\"timeLbl\" was not injected: check your FXML file 'admindashbord.fxml'.";

    }



}
