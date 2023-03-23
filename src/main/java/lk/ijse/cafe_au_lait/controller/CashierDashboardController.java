package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.TimeController;

public class CashierDashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton homeBtn;

    @FXML
    private JFXButton inventoryBtn;

    @FXML
    private JFXButton deliveryBtn;

    @FXML
    private JFXButton customerBtn;

    @FXML
    private JFXButton ordersBtn;

    @FXML
    private JFXButton eventBtn;

    @FXML
    private Label timeLbl;

    @FXML
    private Label datLbl;

    @FXML
    private AnchorPane ancPane;

    @FXML
    void customerBtnClick(ActionEvent event) {

    }

    @FXML
    void deliveryBtnClick(ActionEvent event) {

    }

    @FXML
    void eventBttnClick(ActionEvent event) {

    }

    @FXML
    void homeBtnClick(ActionEvent event) {

    }

    @FXML
    void inventoryBtnClick(ActionEvent event) {

    }

    @FXML
    void logoutClick(MouseEvent event) {

    }

    @FXML
    void ordersBtnClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        TimeController.timeNow(timeLbl,datLbl);
        assert anchorpane != null : "fx:id=\"anchorpane\" was not injected: check your FXML file 'cashierDashboard.fxml'.";
        assert homeBtn != null : "fx:id=\"homeBtn\" was not injected: check your FXML file 'cashierDashboard.fxml'.";
        assert inventoryBtn != null : "fx:id=\"inventoryBtn\" was not injected: check your FXML file 'cashierDashboard.fxml'.";
        assert deliveryBtn != null : "fx:id=\"deliveryBtn\" was not injected: check your FXML file 'cashierDashboard.fxml'.";
        assert customerBtn != null : "fx:id=\"customerBtn\" was not injected: check your FXML file 'cashierDashboard.fxml'.";
        assert ordersBtn != null : "fx:id=\"ordersBtn\" was not injected: check your FXML file 'cashierDashboard.fxml'.";
        assert eventBtn != null : "fx:id=\"eventBtn\" was not injected: check your FXML file 'cashierDashboard.fxml'.";
        assert timeLbl != null : "fx:id=\"timeLbl\" was not injected: check your FXML file 'cashierDashboard.fxml'.";
        assert datLbl != null : "fx:id=\"datLbl\" was not injected: check your FXML file 'cashierDashboard.fxml'.";
        assert ancPane != null : "fx:id=\"ancPane\" was not injected: check your FXML file 'cashierDashboard.fxml'.";

    }
}
