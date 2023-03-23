package lk.ijse.cafe_au_lait.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import dto.Employee;
import dto.tm.EmployeeTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.EmployeeModel;

public class AdminEmployeeCntroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField nicTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private ComboBox jobTitileTxt;

    @FXML
    private DatePicker dobTxt;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colJobTitle;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TextField contactTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException {
        String id=idTxt.getText();
        String name=nameTxt.getText();
        String address=addressTxt.getText();
        String dob= String.valueOf(dobTxt.getValue());
        String nic=nicTxt.getText();
        String jobTitle= (String) jobTitileTxt.getValue();
        String contact=contactTxt.getText();
        String email=emailTxt.getText();
        Employee employee=new Employee(id,name,address,dob,nic,jobTitle,contact,email);
        Boolean isSaved=EmployeeModel.save(employee);
        if (isSaved){
            System.out.println("saved");

        }

    }

    void getAll()  {

        try {
            ObservableList<EmployeeTM> employeeData =EmployeeModel.getAll();

            tblEmployee.setItems(employeeData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colJobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    @FXML
    void initialize() {
        getCellValueFactory();
        getAll();
        assert ancPane != null : "fx:id=\"ancPane\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert dobTxt != null : "fx:id=\"dobTxt\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert idTxt != null : "fx:id=\"idTxt\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert addressTxt != null : "fx:id=\"addressTxt\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert nicTxt != null : "fx:id=\"nicTxt\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert nameTxt != null : "fx:id=\"nameTxt\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert jobTitileTxt != null : "fx:id=\"jobTitileTxt\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert tblEmployee != null : "fx:id=\"tblEmployee\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert colName != null : "fx:id=\"colName\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert colAddress != null : "fx:id=\"colAddress\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert colDob != null : "fx:id=\"colDob\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert colNic != null : "fx:id=\"colNic\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert colJobTitle != null : "fx:id=\"colJobTitle\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert contactTxt != null : "fx:id=\"contactTxt\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert emailTxt != null : "fx:id=\"emailTxt\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'adminEmployee.fxml'.";
        jobTitileTxt.getItems().addAll("Admin", "Cashier");

    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }
}
