package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.dto.Customer;
import lk.ijse.cafe_au_lait.dto.tm.CustomerTM;
import lk.ijse.cafe_au_lait.model.CustomerModel;
import lk.ijse.cafe_au_lait.util.DataValidateController;
import lk.ijse.cafe_au_lait.util.NotificationController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CashierCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colEventTime;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> coleventDate;

    @FXML
    private TextField contactTxt;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TextField searchIdTxt;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private Label emailCheckLbl;

    @FXML
    private Label contactCheckLb;


    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = CustomerModel.delete(idTxt.getText());
            boolean result = NotificationController.confirmationMasseage("Are you sure you want delete this " +
                    "customer ?");
            if (result) {
                if (isDeleted) {
                    idTxt.setText("");
                    nameTxt.setText("");
                    contactTxt.setText("");
                    emailTxt.setText("");
                    getAll();
                    NotificationController.animationMesseage("assets/tik.png", "Delete",
                            "Customer Deleted sucessfully !!");


                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        String id = idTxt.getText();
        String name = nameTxt.getText();
        String contact = contactTxt.getText();
        String email = emailTxt.getText();

        if (DataValidateController.contactCheck(contact) | DataValidateController.emailCheck(email)) {
            if (DataValidateController.contactCheck(contact)) {
                contactTxt.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
                contactCheckLb.setVisible(false);
                contactCheckLb.setText(" ");
                if (DataValidateController.emailCheck(email)) {
                    emailTxt.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
                    emailCheckLbl.setVisible(false);
                    emailCheckLbl.setText(" ");
                    Customer customer = new Customer(id, name, contact, email);
                    boolean isSaved = CustomerModel.save(customer);
                    if (isSaved) {
                        getAll();
                        idTxt.setText("");
                        nameTxt.setText("");
                        contactTxt.setText("");
                        emailTxt.setText("");
                        NotificationController.animationMesseage("assets/tik.png", "Saved",
                                "Customer Added sucessfully !!");

                    }

                } else {
                    emailTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
                    emailCheckLbl.setVisible(true);
                    emailCheckLbl.setText("Invalid Email");
                }
            } else {
                contactTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
                contactCheckLb.setVisible(true);
                contactCheckLb.setText("Invalid Contact");
            }
        } else {
            emailTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
            emailCheckLbl.setVisible(true);
            emailCheckLbl.setText("Invalid Email");

            contactTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
            contactCheckLb.setVisible(true);
            contactCheckLb.setText("Invalid Contact");
        }
    }

    @FXML
    void searchIconClick(MouseEvent event) {

        Customer customer = CustomerModel.searchById(searchIdTxt.getText());
        if (customer != null) {
            idTxt.setText(customer.getCustId());
            nameTxt.setText(customer.getCustName());
            contactTxt.setText(customer.getCustContact());
            emailTxt.setText(customer.getCustEmail());

        } else {
            NotificationController.ErrorMasseage("Event ID Not Found");
        }


    }

    @FXML
    void searchTable(KeyEvent event) throws SQLException {
        String searchValue = searchIdTxt.getText().trim();
        ObservableList<CustomerTM> obList = CustomerModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<CustomerTM> filteredData = obList.filtered(new Predicate<CustomerTM>() {
                @Override
                public boolean test(CustomerTM customerTM) {
                    return String.valueOf(customerTM.getCustId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblCustomer.setItems(filteredData);
        } else {
            tblCustomer.setItems(obList);
        }

    }

    @FXML
    void tblClick(MouseEvent event) {
        TablePosition pos = tblCustomer.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<CustomerTM, ?>> columns = tblCustomer.getColumns();

        idTxt.setText(columns.get(0).getCellData(row).toString());
        nameTxt.setText(columns.get(1).getCellData(row).toString());
        contactTxt.setText(columns.get(2).getCellData(row).toString());
        emailTxt.setText(columns.get(3).getCellData(row).toString());

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = idTxt.getText();
        String name = nameTxt.getText();
        String contact = contactTxt.getText();
        String email = emailTxt.getText();

        if (DataValidateController.contactCheck(contact) | DataValidateController.emailCheck(email)) {
            if (DataValidateController.contactCheck(contact)) {
                contactTxt.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
                contactCheckLb.setVisible(false);
                contactCheckLb.setText(" ");
                if (DataValidateController.emailCheck(email)) {
                    emailTxt.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
                    emailCheckLbl.setVisible(false);
                    emailCheckLbl.setText(" ");

                    Customer customer = new Customer(id, name, contact, email);
                    try {
                        boolean isUpdated = CustomerModel.update(customer);
                        boolean result = NotificationController.confirmationMasseage("Are you sure you want update this " +
                                "customer ?");
                        if (result) {
                            if (isUpdated) {
                                idTxt.setText(customer.getCustId());
                                nameTxt.setText(customer.getCustName());
                                contactTxt.setText(customer.getCustContact());
                                emailTxt.setText(customer.getCustEmail());

                                getAll();
                                NotificationController.animationMesseage("assets/tik.png", "Update",
                                        "customer Updated sucessfully !!");
                            }
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    emailTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
                    emailCheckLbl.setVisible(true);
                    emailCheckLbl.setText("Invalid Email");
                }
            } else {
                contactTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
                contactCheckLb.setVisible(true);
                contactCheckLb.setText("Invalid Contact");
            }
        } else {
            emailTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
            emailCheckLbl.setVisible(true);
            emailCheckLbl.setText("Invalid Email");

            contactTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
            contactCheckLb.setVisible(true);
            contactCheckLb.setText("Invalid Contact");
        }


    }

    void getAll() {
        try {
            ObservableList<CustomerTM> eventData = CustomerModel.getAll();
            tblCustomer.setItems(eventData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("custContact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("custEmail"));


    }

    @FXML
    void initialize() {
        contactCheckLb.setVisible(false);
        emailCheckLbl.setVisible(false);
        getCellValueFactory();
        getAll();

    }

}
