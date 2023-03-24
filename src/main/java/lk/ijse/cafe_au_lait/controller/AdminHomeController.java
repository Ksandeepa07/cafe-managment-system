package lk.ijse.cafe_au_lait.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminHomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label timeLbl;

    @FXML
    private Label datLbl;

    @FXML
    void initialize() {

        assert timeLbl != null : "fx:id=\"timeLbl\" was not injected: check your FXML file 'adminhome.fxml'.";
        assert datLbl != null : "fx:id=\"datLbl\" was not injected: check your FXML file 'adminhome.fxml'.";

    }

    public void homeBtnClick(ActionEvent actionEvent) {
    }

    public void employeeBtnClick(ActionEvent actionEvent) {
    }
}