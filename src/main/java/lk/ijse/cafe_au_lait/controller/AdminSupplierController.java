package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import lk.ijse.cafe_au_lait.dto.Supplier;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.dto.tm.SupplierTM;
import lk.ijse.cafe_au_lait.model.SupplierModel;
import lk.ijse.cafe_au_lait.util.NotificationController;

public class AdminSupplierController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField contactTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colJobTitle;

    @FXML
    private TextField emailTxt;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private TextField searchIdTxt;

    @FXML
    private ImageView searchIcon;

    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted=SupplierModel.delete(idTxt.getText());
            boolean result=NotificationController.confirmationMasseage("Are you sure you want remove this " +
                    "employee ?");
            if(result){

                if(isDeleted){
                    idTxt.setText(" ");
                    nameTxt.setText(" ");
                    addressTxt.setText(" ");
                    contactTxt.setText(" ");
                    emailTxt.setText(" ");
                    getAll();
                    NotificationController.animationMesseage("assets/tik.png","Delete",
                            "Supplier Deleted sucessfully !!");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        String id=idTxt.getText();
        String  name=nameTxt.getText();
        String contact=contactTxt.getText();
        String address=addressTxt.getText();
        String email=emailTxt.getText();

        Supplier supplier=new Supplier(id,name,contact,address,email);
        try {
            boolean isSaved=SupplierModel.save(supplier);
            if(isSaved){
                idTxt.setText(" ");
                nameTxt.setText(" ");
                addressTxt.setText(" ");
                contactTxt.setText(" ");
                emailTxt.setText(" ");
                getAll();
                NotificationController.animationMesseage("assets/tik.png","Saved",
                        "Supplier Added sucessfully !!");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void searchIconClick(MouseEvent event) {
        try {
            Supplier supplier=SupplierModel.searchById(searchIdTxt.getText());
            if(supplier!=null){
                idTxt.setText(supplier.getId());
                nameTxt.setText(supplier.getName());
                contactTxt.setText(supplier.getContact());
                addressTxt.setText(supplier.getAddress());
                emailTxt.setText(supplier.getEmail());
            }else{
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void searchTable(KeyEvent event) throws SQLException {
        String searchValue = searchIdTxt.getText().trim();
        ObservableList<SupplierTM> obList = SupplierModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<SupplierTM> filteredData = obList.filtered(new Predicate<SupplierTM>(){
                @Override
                public boolean test(SupplierTM supplier) {
                    return String.valueOf(supplier.getId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblSupplier.setItems(filteredData);
        } else {
            tblSupplier.setItems(obList);
        }


    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id=idTxt.getText();
        String  name=nameTxt.getText();
        String contact=contactTxt.getText();
        String address=addressTxt.getText();
        String email=emailTxt.getText();

        Supplier supplier=new Supplier(id,name,contact,address,email);
        boolean result=NotificationController.confirmationMasseage("Are you sure you want update this " +
                "employee ?");
        if(result){
            try {
                boolean isUpdated=SupplierModel.update(supplier);
                if(isUpdated){
                    idTxt.setText(" ");
                    nameTxt.setText(" ");
                    addressTxt.setText(" ");
                    contactTxt.setText(" ");
                    emailTxt.setText(" ");
                    getAll();
                    NotificationController.animationMesseage("assets/tik.png","Update",
                            "Suplier Updated sucessfully !!");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    void getAll(){
        try {
            ObservableList<SupplierTM> supplierData =SupplierModel.getAll();
            tblSupplier.setItems(supplierData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    @FXML
    void initialize() {
        getAll();;
        getCellValueFactory();
    }
}
