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
import lk.ijse.cafe_au_lait.dto.Employee;
import lk.ijse.cafe_au_lait.dto.tm.EmployeeTM;
import lk.ijse.cafe_au_lait.model.EmployeeModel;
import lk.ijse.cafe_au_lait.util.DataValidateController;
import lk.ijse.cafe_au_lait.util.NotificationController;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static lk.ijse.cafe_au_lait.util.TextFieldBorderController.txtfieldbordercolor;

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
    private ImageView searchIcon;

    @FXML
    private TextField searchIdTxt;

    @FXML
    private Label contactCheckLb;

    @FXML
    private Label emailCheckLbl;

    @FXML
    void saveOnAction(ActionEvent event) {
        String id = idTxt.getText();
        String name = nameTxt.getText();
        String address = addressTxt.getText();
        String dob = String.valueOf(dobTxt.getValue());
        String nic = nicTxt.getText();
        String jobTitle = (String) jobTitileTxt.getValue();
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
                    Employee employee = new Employee(id, name, address, dob, nic, jobTitle, contact, email);

                    Boolean isSaved = null;
                    try {
                        isSaved = EmployeeModel.save(employee);
                        if (isSaved) {
                            idTxt.setText(" ");
                            nameTxt.setText(" ");
                            addressTxt.setText(" ");
                            jobTitileTxt.setValue(null);
                            dobTxt.setValue(null);
                            nicTxt.setText("");
                            contactTxt.setText("");
                            emailTxt.setText("");

                            NotificationController.animationMesseage("assets/tik.png", "Saved",
                                    "Employee Added sucessfully !!");
                            getAll();

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
            ObservableList<EmployeeTM> employeeData = EmployeeModel.getAll();
            tblEmployee.setItems(employeeData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colJobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    public void searchIconClick(MouseEvent mouseEvent) {
        try {
            Employee employee = EmployeeModel.searchById(searchIdTxt.getText());
            if (employee != null) {
                idTxt.setText(employee.getId());
                nameTxt.setText(employee.getName());
                addressTxt.setText(employee.getAddress());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(employee.getDob(), formatter);
                dobTxt.setValue(date);
                nicTxt.setText(employee.getNic());
                jobTitileTxt.setValue(employee.getJobTitle());
                contactTxt.setText(employee.getContact());
                emailTxt.setText(employee.getEmail());
            } else {
                NotificationController.ErrorMasseage("Employee ID Not Found");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void searchTable(KeyEvent keyEvent) throws SQLException {
        String searchValue = searchIdTxt.getText().trim();
        ObservableList<EmployeeTM> obList = EmployeeModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<EmployeeTM> filteredData = obList.filtered(new Predicate<EmployeeTM>() {
                @Override
                public boolean test(EmployeeTM employeetm) {
                    return String.valueOf(employeetm.getId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblEmployee.setItems(filteredData);
        } else {
            tblEmployee.setItems(obList);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = idTxt.getText();
        String name = nameTxt.getText();
        String address = addressTxt.getText();
        String dob = String.valueOf(dobTxt.getValue());
        String nic = nicTxt.getText();
        String jobTitle = (String) jobTitileTxt.getValue();
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
                    emailCheckLbl.setText("");

                    Employee employee = new Employee(id, name, address, dob, nic, jobTitle, contact, email);
                    try {
                        boolean isUpdated = EmployeeModel.update(employee);
                        boolean result = NotificationController.confirmationMasseage("Are you sure you want update this " +
                                "employee ?");
                        if (result) {
                            if (isUpdated) {
                                idTxt.setText("");
                                nameTxt.setText("");
                                addressTxt.setText("");
                                jobTitileTxt.setValue(null);
                                dobTxt.setValue(null);
                                nicTxt.setText("");
                                contactTxt.setText("");
                                emailTxt.setText("");

                                getAll();
                                NotificationController.animationMesseage("assets/tik.png", "Update",
                                        "Employee Updated sucessfully !!");
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

    public void deleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDeleted = EmployeeModel.delete(idTxt.getText());
            boolean result = NotificationController.confirmationMasseage("Are you sure you want delete this " +
                    "employee ?");
            if (result) {
                if (isDeleted) {
                    getAll();
                    NotificationController.animationMesseage("assets/tik.png", "Delete",
                            "Employee Deleted sucessfully !!");
                    idTxt.setText("");
                    nameTxt.setText("");
                    addressTxt.setText("");
                    jobTitileTxt.setValue(null);
                    dobTxt.setValue(null);
                    nicTxt.setText("");
                    contactTxt.setText("");
                    emailTxt.setText("");

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void tblClick(MouseEvent mouseEvent) {
        TablePosition pos = tblEmployee.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<EmployeeTM, ?>> columns = tblEmployee.getColumns();

        idTxt.setText(columns.get(0).getCellData(row).toString());
        nameTxt.setText(columns.get(1).getCellData(row).toString());
        addressTxt.setText(columns.get(2).getCellData(row).toString());
        dobTxt.setValue(LocalDate.parse(columns.get(3).getCellData(row).toString()));
        nicTxt.setText(columns.get(4).getCellData(row).toString());
        jobTitileTxt.setValue(columns.get(5).getCellData(row).toString());
        contactTxt.setText(columns.get(6).getCellData(row).toString());
        emailTxt.setText(columns.get(7).getCellData(row).toString());
    }


    @FXML
    void initialize() {
        txtfieldbordercolor(idTxt);
        txtfieldbordercolor(nameTxt);
        txtfieldbordercolor(addressTxt);
        txtfieldbordercolor(contactTxt);
        txtfieldbordercolor(emailTxt);
        txtfieldbordercolor(nicTxt);


        getCellValueFactory();
        getAll();
        jobTitileTxt.getItems().addAll("Admin", "Cashier");

    }


}
