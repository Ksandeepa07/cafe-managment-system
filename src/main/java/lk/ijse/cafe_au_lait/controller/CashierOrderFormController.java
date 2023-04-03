package lk.ijse.cafe_au_lait.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe_au_lait.dto.Customer;
import lk.ijse.cafe_au_lait.dto.Item;
import lk.ijse.cafe_au_lait.dto.Delivery;
import lk.ijse.cafe_au_lait.dto.Order;
import lk.ijse.cafe_au_lait.dto.tm.CartTM;
import lk.ijse.cafe_au_lait.model.CustomerModel;
import lk.ijse.cafe_au_lait.model.ItemModel;
import lk.ijse.cafe_au_lait.model.OrderModel;
import lk.ijse.cafe_au_lait.model.PlaceOrderModel;
import lk.ijse.cafe_au_lait.util.NotificationController;
import lk.ijse.cafe_au_lait.util.StageController;
import lk.ijse.cafe_au_lait.util.TimeController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CashierOrderFormController {

    private final ObservableList<CartTM> obList = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private JFXButton addToCart;
    @FXML
    private AnchorPane ancPane;
    @FXML
    private Label category;
    @FXML
    private TableColumn<?, ?> colCategory;
    @FXML
    private TableColumn<?, ?> colDelivery;
    @FXML
    private TableColumn<?, ?> colAction;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colQuantity;
    @FXML
    private TableColumn<?, ?> colTotal;
    @FXML
    private TableColumn<?, ?> colUnitPrice;
    @FXML
    private ComboBox<String> custId;
    @FXML
    private Label custName;
    @FXML
    private RadioButton deliveryNo;
    @FXML
    private RadioButton deliveryYes;
    @FXML
    private ComboBox<String> itemId;
    @FXML
    private Label itemName;
    @FXML
    private Label netTotall;
    @FXML
    private Label orderDate;
    @FXML
    private Label orderId;
    @FXML
    private Label orderTime;
    @FXML
    private JFXButton placeOrderBtn;
    @FXML
    private TextField quantity;
    @FXML
    private Label quantityAvailable;
    @FXML
    private TableView<CartTM> tblOrder;
    @FXML
    private Label unitPrice;
    private String delivery;
    @FXML
    private Label balanceLbl;
    @FXML
    private TextField cashTxt;

    Item item = null;


    @FXML
    void tblClick(MouseEvent event) {

    }

    private void generateNextOrderId() {
        try {
            String id = OrderModel.getNextOrderId();
            orderId.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    void loadCustId() {
        ObservableList<String> custData = CustomerModel.loadCustId();
        custId.setItems(custData);
    }

    public void custIdClick(ActionEvent actionEvent) {
        Customer customer = null;
        try {
            customer = CustomerModel.searchById(custId.getValue());
            custName.setText(customer.getCustName());
        } catch (Exception throwables) {
            System.out.println(throwables);
        }


    }

    void loadItemId() {

        ObservableList<String> itemData = ItemModel.loadItemId();
        itemId.setItems(itemData);

    }

    public void itemIdClick(ActionEvent actionEvent) {
        String id = itemId.getValue();

        try {
            item = ItemModel.searchById(id);
            itemName.setText(item.getName());
            if(item.getQuantity()<=0){
                quantityAvailable.setText("Out Of Stock");
                NotificationController.ErrorMasseage("Item "+item.getName() +" out of stock ");
            }else{
                quantityAvailable.setText(String.valueOf(item.getQuantity()));
            }
            category.setText(item.getCategory());
            unitPrice.setText(String.valueOf(item.getPrice()));
        } catch (Exception throwables) {
            System.out.println(throwables);
        }

    }

    @FXML
    void addToCartClick(ActionEvent event) {
        String id = itemId.getValue();
        String itemName = custName.getText();
        Double price = Double.valueOf(unitPrice.getText());
        Integer qty = Integer.valueOf(quantity.getText());
        String type = category.getText();
        Double total = price * qty;
        Button btnRemove = new Button("Remove");
        btnRemove.setStyle("-fx-background-color: #7B3927;-fx-text-fill: #dfa47e");
        setRemoveBtnOnAction(btnRemove);
        if(item.getQuantity()<qty){
            NotificationController.ErrorMasseage("Not sufficient quantity for "+item.getName());

        }else{
            if (!obList.isEmpty()) {
                for (int i = 0; i < tblOrder.getItems().size(); i++) {
                    if (colId.getCellData(i).equals(id)) {
                        qty += (int) colQuantity.getCellData(i);
                        total = qty * price;

                        obList.get(i).setQuantity(qty);
                        obList.get(i).setTotalPrice(total);

                        tblOrder.refresh();
                        calculateNetTotal();
                        return;
                    }
                }
            }

            CartTM cartTm = new CartTM(id, itemName, type, qty, price, total, delivery, btnRemove);

            obList.add(cartTm);
            tblOrder.setItems(obList);
            calculateNetTotal();

            quantity.setText("");

        }




    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            double total = (double) colTotal.getCellData(i);
            netTotal += total;

        }
        netTotall.setText(String.valueOf(netTotal));

    }

    private void setRemoveBtnOnAction(Button btnRemove) {
        btnRemove.setOnAction((e) -> {
            Boolean result = NotificationController.confirmationMasseage("Are you sure want to remove ?");
            if (result) {

                int index = tblOrder.getSelectionModel().getSelectedIndex();
                obList.remove(index);

                tblOrder.refresh();
                calculateNetTotal();
            }

        });
    }

    public void deliveryYesActionClick(ActionEvent actionEvent) {
        StageController.changeStage("/view/newDeliverForm.fxml","deliver");
        delivery = deliveryYes.getText();
    }

    public void deliveryNoActionClick(ActionEvent actionEvent) {
        delivery = deliveryNo.getText();

    }

    void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colDelivery.setCellValueFactory(new PropertyValueFactory<>("Delivery"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("remove"));

    }

    @FXML
    void placeOrderClick(ActionEvent event) {
        String oId = orderId.getText();
        String customerId = custId.getValue();
        Double orderPayment = Double.valueOf(netTotall.getText());


        List<Order> orderDtoList = new ArrayList<>();

        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            CartTM cartTM = obList.get(i);
            Order orderDto = new Order(
                    cartTM.getItemId(),
                    cartTM.getQuantity(),
                    cartTM.getDelivery()
            );
            orderDtoList.add(orderDto);
            try {
                boolean isPlaced = PlaceOrderModel.placeOrder(oId, customerId, orderPayment, cartTM, orderDtoList);

                Delivery newDeliverDto=new Delivery();
                if (isPlaced) {
                    NotificationController.confirmationMasseage("order placed sucesfull !!!");
                    custId.setValue(null);
                    custName.setText("");
                    itemId.setValue(null);
                    itemName.setText("");
                    category.setText("");
                    quantity.setText("");
                    unitPrice.setText("");
                    quantityAvailable.setText("");
                    deliveryYes.setSelected(false);
                    deliveryNo.setSelected(false);
                    tblOrder.getItems().clear();
                    generateNextOrderId();
                }

            } catch (Exception e) {
                System.out.println(e);

            }

        }
    }

    public void cashTyped(KeyEvent keyEvent) {
        Double cash = Double.valueOf(cashTxt.getText());
        Double total = Double.valueOf(netTotall.getText());
        Double balance = cash - total;
        balanceLbl.setText(String.valueOf(balance));
        if (balance >= 0) {
            placeOrderBtn.setDisable(false);
        }
    }

    public void newCustomerBtnClick(ActionEvent actionEvent) {
        StageController.changeStage("/view/cashierCustomer.fxml","Add new customer");
    }

    @FXML
    void initialize() {
        placeOrderBtn.setDisable(true);
        TimeController.timeNow(orderTime, orderDate);
        generateNextOrderId();
        loadCustId();
        loadItemId();
        setCellValueFactory();

    }


}