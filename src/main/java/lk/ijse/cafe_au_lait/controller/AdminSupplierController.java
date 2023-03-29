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
import lk.ijse.cafe_au_lait.dto.Supplier;
import lk.ijse.cafe_au_lait.dto.tm.SupplierTM;
import lk.ijse.cafe_au_lait.model.SupplierModel;
import lk.ijse.cafe_au_lait.util.DataValidateController;
import lk.ijse.cafe_au_lait.util.NotificationController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static lk.ijse.cafe_au_lait.util.TextFieldBorderController.txtfieldbordercolor;

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
    private Label emailCheckLbl;

    @FXML
    private Label contactCheckLb;

    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = SupplierModel.delete(idTxt.getText());
            boolean result = NotificationController.confirmationMasseage("Are you sure you want remove this " +
                    "employee ?");
            if (result) {

                if (isDeleted) {
                    idTxt.setText(" ");
                    nameTxt.setText(" ");
                    addressTxt.setText(" ");
                    contactTxt.setText(" ");
                    emailTxt.setText(" ");
                    getAll();
                    NotificationController.animationMesseage("assets/tik.png", "Delete",
                            "Supplier Deleted sucessfully !!");
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
        String address = addressTxt.getText();
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

                    Supplier supplier = new Supplier(id, name, contact, address, email);
                    try {
                        boolean isSaved = SupplierModel.save(supplier);
                        if (isSaved) {
                            idTxt.setText(" ");
                            nameTxt.setText(" ");
                            addressTxt.setText(" ");
                            contactTxt.setText(" ");
                            emailTxt.setText(" ");
                            getAll();
                            NotificationController.animationMesseage("assets/tik.png", "Saved",
                                    "Supplier Added sucessfully !!");

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

    @FXML
    void searchIconClick(MouseEvent event) {
        try {
            Supplier supplier = SupplierModel.searchById(searchIdTxt.getText());
            if (supplier != null) {
                idTxt.setText(supplier.getId());
                nameTxt.setText(supplier.getName());
                contactTxt.setText(supplier.getContact());
                addressTxt.setText(supplier.getAddress());
                emailTxt.setText(supplier.getEmail());
            } else {
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
            ObservableList<SupplierTM> filteredData = obList.filtered(new Predicate<SupplierTM>() {
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
        String id = idTxt.getText();
        String name = nameTxt.getText();
        String contact = contactTxt.getText();
        String address = addressTxt.getText();
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

        Supplier supplier = new Supplier(id, name, contact, address, email);
        boolean result = NotificationController.confirmationMasseage("Are you sure you want update this " +
                "employee ?");
        if (result) {
            try {
                boolean isUpdated = SupplierModel.update(supplier);
                if (isUpdated) {
                    idTxt.setText(" ");
                    nameTxt.setText(" ");
                    addressTxt.setText(" ");
                    contactTxt.setText(" ");
                    emailTxt.setText(" ");
                    getAll();
                    NotificationController.animationMesseage("assets/tik.png", "Update",
                            "Suplier Updated sucessfully !!");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
            ObservableList<SupplierTM> supplierData = SupplierModel.getAll();
            tblSupplier.setItems(supplierData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    public void tblClick(MouseEvent mouseEvent) {
        TablePosition pos = tblSupplier.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<SupplierTM, ?>> columns = tblSupplier.getColumns();

        idTxt.setText(columns.get(0).getCellData(row).toString());
        nameTxt.setText(columns.get(1).getCellData(row).toString());
        contactTxt.setText(columns.get(2).getCellData(row).toString());
        addressTxt.setText(columns.get(3).getCellData(row).toString());
        emailTxt.setText(columns.get(4).getCellData(row).toString());
    }


    @FXML
    void initialize() {
        txtfieldbordercolor(idTxt);
        txtfieldbordercolor(nameTxt);
        txtfieldbordercolor(addressTxt);
        txtfieldbordercolor(contactTxt);
        txtfieldbordercolor(emailTxt);


        getAll();
        getCellValueFactory();
    }
}
