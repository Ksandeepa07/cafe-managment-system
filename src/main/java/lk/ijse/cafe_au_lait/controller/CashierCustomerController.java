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

    boolean isCustomerValid;
    boolean isIdValid;
    boolean isEmailValidate;
    boolean isNameValidate;
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
    private ImageView newCustomerAncPane;
    @FXML
    private JFXButton reportBtn;
    @FXML
    private ImageView cutIdIcon;
    @FXML
    private ImageView custContactIcon;
    @FXML
    private ImageView custEmailIcon;
    @FXML
    private ImageView custNameIcon;
    @FXML
    private Tooltip idToolTip;

    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = CustomerModel.delete(idTxt.getText());
            boolean result = NotificationController.confirmationMasseage("Are you sure you want delete this " +
                    "customer ?");
            if (result) {
                if (isDeleted) {
                    deleteBtn.setDisable(true);
                    updateBtn.setDisable(true);
                    saveBtn.setDisable(true);
                    cutIdIcon.setVisible(false);
                    custNameIcon.setVisible(false);
                    custEmailIcon.setVisible(false);
                    custContactIcon.setVisible(false);
                    idTxt.setText("");
                    nameTxt.setText("");
                    contactTxt.setText("");
                    emailTxt.setText("");
                    getAll();
                    NotificationController.animationMesseage("/assets/tick.gif", "Delete",
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

//        if (DataValidateController.contactCheck(contact) | DataValidateController.emailCheck(email)) {
//            if (DataValidateController.contactCheck(contact)) {
//                contactTxt.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
//                contactCheckLb.setVisible(false);
//                contactCheckLb.setText(" ");
//                if (DataValidateController.emailCheck(email)) {
//                    emailTxt.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
//                    emailCheckLbl.setVisible(false);
//                    emailCheckLbl.setText(" ");
        Customer customer = new Customer(id, name, contact, email);
        boolean isSaved = CustomerModel.save(customer);
        if (isSaved) {
            deleteBtn.setDisable(true);
            updateBtn.setDisable(true);
            saveBtn.setDisable(true);
            getAll();
            idTxt.setText("");
            nameTxt.setText("");
            contactTxt.setText("");
            emailTxt.setText("");
            cutIdIcon.setVisible(false);
            custNameIcon.setVisible(false);
            custEmailIcon.setVisible(false);
            custContactIcon.setVisible(false);
            NotificationController.animationMesseage("/assets/tick.gif", "Saved",
                    "Customer Added sucessfully !!");

        }

//                } else {
//                    emailTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
//                    emailCheckLbl.setVisible(true);
//                    emailCheckLbl.setText("Invalid Email");
//                }
//            } else {
//                contactTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
//                contactCheckLb.setVisible(true);
//                contactCheckLb.setText("Invalid Contact");
//            }
//        } else {
//            emailTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
//            emailCheckLbl.setVisible(true);
//            emailCheckLbl.setText("Invalid Email");
//
//            contactTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
//            contactCheckLb.setVisible(true);
//            contactCheckLb.setText("Invalid Contact");
//        }
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
        saveBtn.setDisable(false);
        updateBtn.setDisable(false);
        deleteBtn.setDisable(false);
        cutIdIcon.setVisible(true);
        custNameIcon.setVisible(true);
        custEmailIcon.setVisible(true);
        custContactIcon.setVisible(true);
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

//        if (DataValidateController.contactCheck(contact) | DataValidateController.emailCheck(email)) {
//            if (DataValidateController.contactCheck(contact)) {
//                contactTxt.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
//                contactCheckLb.setVisible(false);
//                contactCheckLb.setText(" ");
//                if (DataValidateController.emailCheck(email)) {
//                    emailTxt.setStyle("-fx-border-color: #7B3927; -fx-border-width: 0 0 3 0;");
//                    emailCheckLbl.setVisible(false);
//                    emailCheckLbl.setText(" ");

        Customer customer = new Customer(id, name, contact, email);
        try {
            boolean isUpdated = CustomerModel.update(customer);
            boolean result = NotificationController.confirmationMasseage("Are you sure you want update this " +
                    "customer ?");
            if (result) {
                if (isUpdated) {
                    deleteBtn.setDisable(true);
                    updateBtn.setDisable(true);
                    saveBtn.setDisable(true);
                    deleteBtn.setDisable(true);
                    updateBtn.setDisable(true);
                    saveBtn.setDisable(true);
                    idTxt.setText(customer.getCustId());
                    nameTxt.setText(customer.getCustName());
                    contactTxt.setText(customer.getCustContact());
                    emailTxt.setText(customer.getCustEmail());
                    cutIdIcon.setVisible(false);
                    custNameIcon.setVisible(false);
                    custEmailIcon.setVisible(false);
                    custContactIcon.setVisible(false);

                    getAll();
                    NotificationController.animationMesseage("/assets/tick.gif", "Update",
                            "customer Updated sucessfully !!");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//                } else {
//                    emailTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
//                    emailCheckLbl.setVisible(true);
//                    emailCheckLbl.setText("Invalid Email");
//                }
//            } else {
//                contactTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
//                contactCheckLb.setVisible(true);
//                contactCheckLb.setText("Invalid Contact");
//            }
//        } else {
//            emailTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
//            emailCheckLbl.setVisible(true);
//            emailCheckLbl.setText("Invalid Email");
//
//            contactTxt.setStyle("-fx-border-color: red; -fx-border-width: 0 0 3 0;");
//            contactCheckLb.setVisible(true);
//            contactCheckLb.setText("Invalid Contact");
//        }


    }

    void getAll() {
        try {
            ObservableList<CustomerTM> eventData = CustomerModel.getAll();
            tblCustomer.setItems(eventData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void backBtnClick(ActionEvent actionEvent) {
        newCustomerAncPane.getScene().getWindow().hide();

    }

    void getCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("custContact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("custEmail"));


    }


    @FXML
    void CusromerIdKeyTyped(KeyEvent event) {
        idTxt.setTooltip(idToolTip);
        isIdValid = DataValidateController.customerIdValidate(idTxt.getText());

            saveBtn.setDisable(!isIdValid | contactTxt.getText().isEmpty() | emailTxt.getText().isEmpty() | nameTxt.getText().isEmpty());
            updateBtn.setDisable(!isIdValid | contactTxt.getText().isEmpty() | emailTxt.getText().isEmpty() | nameTxt.getText().isEmpty());
            deleteBtn.setDisable(!isIdValid | contactTxt.getText().isEmpty() | emailTxt.getText().isEmpty() | nameTxt.getText().isEmpty());



        if (isIdValid) {

            cutIdIcon.setVisible(true);

        } else {
            cutIdIcon.setVisible(false);
         }

    }

    @FXML
    void customerContatctKeyTyped(KeyEvent event) {
        isCustomerValid = DataValidateController.contactCheck(contactTxt.getText());

            saveBtn.setDisable(!isCustomerValid | idTxt.getText().isEmpty() | emailTxt.getText().isEmpty() | nameTxt.getText().isEmpty());
            updateBtn.setDisable(!isCustomerValid | idTxt.getText().isEmpty() | emailTxt.getText().isEmpty() | nameTxt.getText().isEmpty());
            deleteBtn.setDisable(!isCustomerValid | idTxt.getText().isEmpty() | emailTxt.getText().isEmpty() | nameTxt.getText().isEmpty());



        if (isCustomerValid) {

            custContactIcon.setVisible(true);

        } else {
            custContactIcon.setVisible(false);
         }

    }

    @FXML
    void customerEmailKeyTyped(KeyEvent event) {
        isEmailValidate = DataValidateController.emailCheck(emailTxt.getText());

            saveBtn.setDisable(!isEmailValidate | idTxt.getText().isEmpty() | contactTxt.getText().isEmpty() | nameTxt.getText().isEmpty());
            updateBtn.setDisable(!isEmailValidate | idTxt.getText().isEmpty() | contactTxt.getText().isEmpty() | nameTxt.getText().isEmpty());
            deleteBtn.setDisable(!isEmailValidate | idTxt.getText().isEmpty() | contactTxt.getText().isEmpty() | nameTxt.getText().isEmpty());




        if (isEmailValidate) {
            custEmailIcon.setVisible(true);

        } else {
            custEmailIcon.setVisible(false);

        }


    }

    @FXML
    void customerNameKeyTyped(KeyEvent event) {
        isNameValidate = DataValidateController.customerNameValidate(nameTxt.getText());

            saveBtn.setDisable(!isNameValidate | idTxt.getText().isEmpty() | contactTxt.getText().isEmpty() | emailTxt.getText().isEmpty());
            updateBtn.setDisable(!isNameValidate | idTxt.getText().isEmpty() | contactTxt.getText().isEmpty() | emailTxt.getText().isEmpty());
            deleteBtn.setDisable(!isNameValidate | idTxt.getText().isEmpty() | contactTxt.getText().isEmpty() | emailTxt.getText().isEmpty());




        if (isNameValidate) {

            custNameIcon.setVisible(true);


        } else {
            custNameIcon.setVisible(false);



        }

    }


    @FXML
    void initialize() {
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
        saveBtn.setDisable(true);
        contactCheckLb.setVisible(false);
        emailCheckLbl.setVisible(false);
        getCellValueFactory();
        getAll();

    }


}
