package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import jfxtras.scene.control.LocalTimeTextField;
import lk.ijse.cafe_au_lait.dto.Employee;
import lk.ijse.cafe_au_lait.dto.Event;
import lk.ijse.cafe_au_lait.dto.tm.EmployeeTM;
import lk.ijse.cafe_au_lait.dto.tm.EventTM;
import lk.ijse.cafe_au_lait.model.EmployeeModel;
import lk.ijse.cafe_au_lait.model.EventModel;
import lk.ijse.cafe_au_lait.util.NotificationController;

import static lk.ijse.cafe_au_lait.util.TextFieldBorderController.txtfieldbordercolor;

public class CashierEventController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private TableColumn<?, ?> colEventId;

    @FXML
    private TableColumn<?, ?> colEventName;

    @FXML
    private TableColumn<?, ?> colEventTime;

    @FXML
    private TableColumn<?, ?> colEventType;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> coleventDate;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private DatePicker eventDateTxt;

    @FXML
    private TextField eventIdTxt;

    @FXML
    private TextField eventNameTxt;

    @FXML
    private LocalTimeTextField eventTimeTxt;

    @FXML
    private TextField eventTypeTxt;

    @FXML
    private ComboBox<String> idTxt;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TextField searchIdTxt;

    @FXML
    private TableView<EventTM> tblEvent;

    @FXML
    private JFXButton updateBtn;

    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted=EventModel.delete(eventIdTxt.getText());
            boolean result=NotificationController.confirmationMasseage("Are you sure you want delete this " +
                    "event ?");
            if(result){
                if(isDeleted){
                    getAll();
                    NotificationController.animationMesseage("assets/tik.png","Delete",
                            "Event Deleted sucessfully !!");
                    idTxt.setValue(null);
                    eventIdTxt.setText(" ");
                    eventNameTxt.setText(" ");
                    eventTypeTxt.setText(" ");
                    eventDateTxt.setValue(null);
                    eventTimeTxt.setLocalTime(null);

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        String id= (String) idTxt.getValue();
        String eventId=eventIdTxt.getText();
        String eventName=eventNameTxt.getText();
        String eventType=eventTypeTxt.getText();
        String eventDate= String.valueOf(eventDateTxt.getValue());
        String eventTime= String.valueOf(eventTimeTxt.getLocalTime());

        Event event1=new Event(id,eventId,eventName,eventType,eventDate,eventTime);
        try {
            boolean isSaved= EventModel.save(event1);
            if (isSaved){
                getAll();
                idTxt.setValue(null);
                eventIdTxt.setText(" ");
                eventNameTxt.setText(" ");
                eventTypeTxt.setText(" ");
                eventDateTxt.setValue(null);
                eventTimeTxt.setLocalTime(null);
                NotificationController.animationMesseage("assets/tik.png","Saved",
                        "Event Added sucessfully !!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void searchIconClick(MouseEvent event) {
        try {
            Event event1=EventModel.searchById(searchIdTxt.getText());
            if(event1!=null){
                idTxt.setValue(event1.getEmpId());
                eventIdTxt.setText(event1.getEventId());
                eventNameTxt.setText(event1.getEventName());
                eventTypeTxt.setText(event1.getEventType());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(event1.getEventDate(), formatter);
                eventDateTxt.setValue(date);
                eventTimeTxt.setLocalTime(LocalTime.parse(event1.getEventTime()));

            }else{
                NotificationController.ErrorMasseage("Event ID Not Found");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void searchTable(KeyEvent event) throws SQLException {
        String searchValue = searchIdTxt.getText().trim();
        ObservableList<EventTM> obList = EventModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<EventTM> filteredData = obList.filtered(new Predicate<EventTM>(){
                @Override
                public boolean test(EventTM eventTM) {
                    return String.valueOf(eventTM.getEventId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblEvent.setItems(filteredData);
        } else {
            tblEvent.setItems(obList);
        }

    }

    @FXML
    void tblClick(MouseEvent event) {
        TablePosition pos = tblEvent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<EventTM, ?>> columns = tblEvent.getColumns();

        idTxt.setValue(columns.get(0).getCellData(row).toString());
        eventIdTxt.setText(columns.get(1).getCellData(row).toString());
        eventNameTxt.setText(columns.get(2).getCellData(row).toString());
        eventTypeTxt.setText(columns.get(3).getCellData(row).toString());
        eventDateTxt.setValue(LocalDate.parse(columns.get(4).getCellData(row).toString()));
        eventTimeTxt.setLocalTime(LocalTime.parse(columns.get(5).getCellData(row).toString()));


    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id= (String) idTxt.getValue();
        String eventId=eventIdTxt.getText();
        String eventName=eventNameTxt.getText();
        String eventType=eventTypeTxt.getText();
        String eventDate= String.valueOf(eventDateTxt.getValue());
        String eventTime= String.valueOf(eventTimeTxt.getLocalTime());

        Event event1=new Event(id,eventId,eventName,eventType,eventDate,eventTime);
        try {
            boolean isUpdated=EventModel.update(event1);
            boolean result=NotificationController.confirmationMasseage("Are you sure you want update this " +
                    "event ?");
            if(result){
                if(isUpdated){
                    idTxt.setValue(null);
                    eventIdTxt.setText(" ");
                    eventNameTxt.setText(" ");
                    eventTypeTxt.setText(" ");
                    eventDateTxt.setValue(null);
                    eventTimeTxt.setLocalTime(null);

                    getAll();
                    NotificationController.animationMesseage("assets/tik.png","Update",
                            "Event Updated sucessfully !!");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    void loadEmployeeId(){
        try {
            ObservableList<String> eventData= EmployeeModel.loadEmpIds();
            idTxt.setItems(eventData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getAll(){
        try {
            ObservableList<EventTM> eventData =EventModel.getAll();
            tblEvent.setItems(eventData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEventId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colEventType.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        coleventDate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        colEventTime.setCellValueFactory(new PropertyValueFactory<>("eventTime"));


    }

    @FXML
    void initialize() {
        getCellValueFactory();
        getAll();
        loadEmployeeId();
        txtfieldbordercolor(eventIdTxt);
        txtfieldbordercolor(eventNameTxt);
        txtfieldbordercolor(eventTypeTxt);



    }

}