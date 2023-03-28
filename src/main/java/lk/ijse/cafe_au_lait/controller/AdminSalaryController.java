package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import lk.ijse.cafe_au_lait.dto.Salary;
import lk.ijse.cafe_au_lait.dto.tm.EmployeeTM;
import lk.ijse.cafe_au_lait.dto.tm.ItemTM;
import lk.ijse.cafe_au_lait.dto.tm.SalaryTM;
import lk.ijse.cafe_au_lait.model.EmployeeModel;
import lk.ijse.cafe_au_lait.model.SalaryModel;
import lk.ijse.cafe_au_lait.util.NotificationController;

public class AdminSalaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private TextField payamentTxt;

    @FXML
    private TextField salaryTxt;

    @FXML
    private TableView<SalaryTM> tbllsalary;

    @FXML
    private TableColumn<?, ?> colempId;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colMethod;

    @FXML
    private TableColumn<SalaryTM, Double> colPayment;

    @FXML
    private TableColumn<SalaryTM, Double> colOverTime;

    @FXML
    private TextField overTimeTxt;

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
    private ComboBox<String> idTxt;

    @FXML
    private ComboBox methodTxt;

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void idTxtClick(ActionEvent event) {


    }

    @FXML
    void methodTxtClick(ActionEvent event) {

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        Double overTyime;
        String id=idTxt.getValue();
        String salaryId=salaryTxt.getText();
        String method= (String) methodTxt.getValue();
        Double payment= Double.valueOf(payamentTxt.getText());
        if(overTimeTxt.getText().isEmpty()){
            overTyime= 0.0;
        }else{
            overTyime= Double.valueOf(overTimeTxt.getText());
        }


        Salary salary=new Salary(id,salaryId,method,payment,overTyime);

        try {
            boolean isSaved=SalaryModel.save(salary);
            if(isSaved){
                idTxt.setValue(null);
                salaryTxt.setText(" ");
                methodTxt.setValue(null);
                payamentTxt.setText(" ");
                overTimeTxt.setText(" ");
                getAll();
                NotificationController.animationMesseage("assets/tik.png","Saved",
                        "Salary Added sucessfully !!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void searchIconClick(MouseEvent event) {

    }

    @FXML
    void searchTable(KeyEvent event) throws SQLException {
        String searchValue = searchIdTxt.getText().trim();
        ObservableList<SalaryTM> obList = SalaryModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<SalaryTM> filteredData = obList.filtered(new Predicate<SalaryTM>(){
                @Override
                public boolean test(SalaryTM salarytm) {
                    return String.valueOf(salarytm.getEmpId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tbllsalary.setItems(filteredData);
        } else {
            tbllsalary.setItems(obList);
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        Double overTyime;
        String id=idTxt.getValue();
        String salaryId=salaryTxt.getText();
        String method= (String) methodTxt.getValue();
        Double payment= Double.valueOf(payamentTxt.getText());
        if(overTimeTxt.getText().isEmpty()){
            overTyime= 0.0;
        }else{
            overTyime= Double.valueOf(overTimeTxt.getText());
        }
        Salary salary=new Salary(id,salaryId,method,payment,overTyime);
        try {
            boolean isUpdated=SalaryModel.update(salary);
            boolean result=NotificationController.confirmationMasseage("Are you sure you want update this " +
                    "salary ?");
            if (result) {
                if(isUpdated){
                    idTxt.setValue(null);
                    salaryTxt.setText(" ");
                    methodTxt.setValue(null);
                    payamentTxt.setText(" ");
                    overTimeTxt.setText(" ");
                    getAll();
                    NotificationController.animationMesseage("assets/tik.png","Update",
                            "Salary Updated sucessfully !!");

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    void getAll(){
        try {
            ObservableList<SalaryTM> salaryData=SalaryModel.getAll();
            tbllsalary.setItems(salaryData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory(){
            colempId.setCellValueFactory(new PropertyValueFactory<>("empId"));
            colSalary.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
            colMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
            colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
            colOverTime.setCellValueFactory(new PropertyValueFactory<>("overTime"));

            ////////////////////
            colPayment.setCellFactory(new Callback<TableColumn<SalaryTM, Double>, TableCell<SalaryTM, Double>>() {
                private final DecimalFormat format = new DecimalFormat("#.00");

                @Override
                public TableCell<SalaryTM, Double> call(TableColumn<SalaryTM, Double> column) {
                    return new TableCell<SalaryTM, Double>() {
                        @Override
                        protected void updateItem(Double item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(format.format(item));
                            }
                        }
                    };
                }
            });

        colOverTime.setCellFactory(new Callback<TableColumn<SalaryTM, Double>, TableCell<SalaryTM, Double>>() {
            private final DecimalFormat format = new DecimalFormat("#.00");

            @Override
            public TableCell<SalaryTM, Double> call(TableColumn<SalaryTM, Double> column) {
                return new TableCell<SalaryTM, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(format.format(item));
                        }
                    }
                };
            }
        });
    }

    public void tblClick(MouseEvent mouseEvent) {
        TablePosition pos = tbllsalary.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<SalaryTM, ?>> columns = tbllsalary.getColumns();

        idTxt.setValue(columns.get(0).getCellData(row).toString());
        salaryTxt.setText(columns.get(1).getCellData(row).toString());
        methodTxt.setValue(columns.get(2).getCellData(row).toString());
        payamentTxt.setText(columns.get(3).getCellData(row).toString());
        overTimeTxt.setText(columns.get(4).getCellData(row).toString());
    }






    void loadEmployeeId(){
        try {
            ObservableList<String> employeeData= SalaryModel.loadEmpIds();
            idTxt.setItems(employeeData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void initialize() {
         getAll();
        getCellValueFactory();
        loadEmployeeId();

        methodTxt.getItems().addAll("Card", "Cash");



    }


}
