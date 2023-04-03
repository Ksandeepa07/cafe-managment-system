package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.dto.Item;
import lk.ijse.cafe_au_lait.dto.SupplyLoad;
import lk.ijse.cafe_au_lait.dto.tm.SupplyLoadTM;
import lk.ijse.cafe_au_lait.model.ItemModel;
import lk.ijse.cafe_au_lait.model.OrderModel;
import lk.ijse.cafe_au_lait.model.SupplierModel;
import lk.ijse.cafe_au_lait.model.SupplyLoadModel;
import lk.ijse.cafe_au_lait.util.NotificationController;
import lk.ijse.cafe_au_lait.util.StageController;
import lk.ijse.cafe_au_lait.util.TimeController;

public class SupplierLoadController {
ObservableList<SupplyLoadTM>obList= FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton addSupplyLoad;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private Label category;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private ComboBox<String> itemId;

    @FXML
    private Label itemName;

    @FXML
    private TextField netTotall;

    @FXML
    private Label orderId;

    @FXML
    private JFXButton placeOrderBtn;

    @FXML
    private TextField quantity;

    @FXML
    private Label quantityAvailable;

    @FXML
    private Label supplYLoadDate;

    @FXML
    private ComboBox<String> supplierId;

    @FXML
    private Label supplierName;

    @FXML
    private Label supplyLoadTime;

    @FXML
    private Label supplyLoadTxt;

    @FXML
    private Label supplyQtyError;



    @FXML
    private TableView<SupplyLoadTM> tblSupplyLoads;

    @FXML
    void addSupplyLoadClick(ActionEvent event) {
        String id=itemId.getValue();
        String name=itemName.getText();
        String type=category.getText();
        Integer qty= Integer.valueOf(quantity.getText());
        Button remove=new Button("Remove");
        setRemoveAction(remove);


        if(quantity.getText().isEmpty()){
            supplyQtyError.setVisible(true);
        }else{
            if (!obList.isEmpty()) {
                for (int i = 0; i < tblSupplyLoads.getItems().size(); i++) {
                    if (colId.getCellData(i).equals(id)) {
                        qty += (int) colQuantity.getCellData(i);
                        obList.get(i).setQuantity(qty);
                        tblSupplyLoads.refresh();
                        return;
                    }
                }
            }
            SupplyLoadTM supplyLoadTM=new SupplyLoadTM(id,name,type,qty,remove);
            obList.add(supplyLoadTM);
            tblSupplyLoads.setItems(obList);
            quantity.setText("");
        }



    }

    @FXML
    void checkSupplyLoadDetails(ActionEvent event) {

    }


    private void setRemoveAction(Button remove) {
        remove.setOnAction((e) -> {
            Boolean result = NotificationController.confirmationMasseage("Are you sure want to remove ?");
            if (result) {
                int index = tblSupplyLoads.getSelectionModel().getSelectedIndex();
                obList.remove(index);
                tblSupplyLoads.refresh();


            }

        });
    }


    void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("remove"));

    }

    @FXML
    void itemIdClick(ActionEvent event) {
        String id=itemId.getValue();
        Item item=ItemModel.searchById(id);
        itemName.setText(item.getName());
        category.setText(item.getCategory());
        quantityAvailable.setText(String.valueOf(item.getQuantity()));

    }

    @FXML
    void newSupplierBrtnClick(ActionEvent event) {
        StageController.changeStage("/view/adminSupplier.fxml","Supply");

    }
    @FXML
    void placeOrderClick(ActionEvent event) {
        String supId=supplierId.getValue();
        String supLoadId=supplyLoadTxt.getText();
        String payment=netTotall.getText();


        List<SupplyLoad> data=new ArrayList<>();
        for(int i=0; i<tblSupplyLoads.getItems().size(); i++){
            SupplyLoadTM supplyLoadTM=obList.get(i);
            SupplyLoad supplyLoad=new SupplyLoad(
                    supplyLoadTM.getItemId(),supplyLoadTM.getQuantity()
            );
            data.add(supplyLoad);
        }

        try {
            boolean isPlaced=SupplyLoadModel.PlaceSupplyLoad(supId,supLoadId,payment,data);
            if(isPlaced){
                NotificationController.animationMesseage("/assets/tick.gif","Supply Load","Supply load added sucessfully!!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @FXML
    void supllierIdClick(ActionEvent event) {

    }

    @FXML
    void tblClick(MouseEvent event) {

    }

    void loadSupplierIds(){
        try {
            ObservableList<String> supplierData = SupplierModel.loadSupplierIds();
            supplierId.setItems(supplierData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void loadItemIds(){
        ObservableList<String> itemData= ItemModel.loadItemId();
        itemId.setItems(itemData);
    }

    private void generateNextSupplyOrderId() {
        try {
            String id = SupplyLoadModel.getNextOrderId();
            supplyLoadTxt.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void initialize() {
        supplyQtyError.setVisible(false);
        TimeController.timeNow(supplyLoadTime,supplYLoadDate);
        setCellValueFactory();
        generateNextSupplyOrderId();
        loadSupplierIds();
        loadItemIds();

    }

}
