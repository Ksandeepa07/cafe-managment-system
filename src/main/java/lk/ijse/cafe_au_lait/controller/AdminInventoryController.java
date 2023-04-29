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
import javafx.util.Callback;
import lk.ijse.cafe_au_lait.dto.Item;
import lk.ijse.cafe_au_lait.dto.tm.ItemTM;
import lk.ijse.cafe_au_lait.model.ItemModel;
import lk.ijse.cafe_au_lait.util.DataValidateController;
import lk.ijse.cafe_au_lait.util.NotificationController;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static lk.ijse.cafe_au_lait.util.TextFieldBorderController.txtfieldbordercolor;

public class AdminInventoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField quantityTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TableView<ItemTM> tblItem;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<ItemTM, Double> colPrice;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TextField categoryTxt;

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
    private ImageView itemCategoryIcon;

    @FXML
    private ImageView itemIdIcon;

    @FXML
    private ImageView itemNameIcon;

    @FXML
    private ImageView itemQuantityIcon;

    @FXML
    private ImageView itemUnitPriceIcon;




    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = ItemModel.delete(idTxt.getText());
            boolean result = NotificationController.confirmationMasseage("Are you sure you want delete this " +
                    "item ?");
            if (result) {
                if (isDeleted) {
                    saveBtn.setDisable(true);
                    updateBtn.setDisable(true);
                    deleteBtn.setDisable(true);
                    itemIdIcon.setVisible(false);
                    itemNameIcon.setVisible(false);
                    itemCategoryIcon.setVisible(false);
                    itemQuantityIcon.setVisible(false);
                    itemUnitPriceIcon.setVisible(false);
                    getAll();
                    NotificationController.animationMesseage("/assets/tick.gif", "Delete",
                            "Item Deleted sucessfully !!");
                    idTxt.setText("");
                    nameTxt.setText("");
                    quantityTxt.setText("");
                    priceTxt.setText("");
                    categoryTxt.setText("");
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
        Integer quantity = Integer.valueOf(quantityTxt.getText());
        Double price = Double.valueOf(priceTxt.getText());
        String category = categoryTxt.getText();

        Item item = new Item(id, name, quantity, price, category);
        try {
            boolean isSaved = ItemModel.save(item);
            if (isSaved) {
                saveBtn.setDisable(true);
                updateBtn.setDisable(true);
                deleteBtn.setDisable(true);
                itemIdIcon.setVisible(false);
                itemNameIcon.setVisible(false);
                itemCategoryIcon.setVisible(false);
                itemQuantityIcon.setVisible(false);
                itemUnitPriceIcon.setVisible(false);


                idTxt.setText("");
                nameTxt.setText("");
                quantityTxt.setText("");
                priceTxt.setText("");
                categoryTxt.setText("");
                getAll();
                NotificationController.animationMesseage("/assets/tick.gif", "Saved",
                        "Item Added sucessfully !!");
            }

        } catch (SQLIntegrityConstraintViolationException throwables) {
            NotificationController.ErrorMasseage("This Item Id is Already Exsits");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void searchIconClick(MouseEvent event) {

        Item item = ItemModel.searchById(searchIdTxt.getText());
        idTxt.setText(item.getId());
        nameTxt.setText(item.getName());
        quantityTxt.setText(String.valueOf(item.getQuantity()));
        priceTxt.setText(String.valueOf(item.getPrice()));
        categoryTxt.setText(item.getCategory());

    }


    @FXML
    void searchTable(KeyEvent event) throws SQLException {
        String searchValue = searchIdTxt.getText().trim();
        ObservableList<ItemTM> obList = ItemModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<ItemTM> filteredData = obList.filtered(new Predicate<ItemTM>() {
                @Override
                public boolean test(ItemTM itemTM) {
                    return String.valueOf(itemTM.getId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblItem.setItems(filteredData);
        } else {
            tblItem.setItems(obList);
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = idTxt.getText();
        String name = nameTxt.getText();
        Integer quantity = Integer.valueOf(quantityTxt.getText());
        Double price = Double.valueOf(priceTxt.getText());
        String category = categoryTxt.getText();
        Item item = new Item(id, name, quantity, price, category);

        try {
            boolean isUpdated = ItemModel.update(item);
            boolean result = NotificationController.confirmationMasseage("Are you sure you want update this " +
                    "item ?");
            if (result) {
                if (isUpdated) {
                    saveBtn.setDisable(true);
                    updateBtn.setDisable(true);
                    deleteBtn.setDisable(true);
                    itemIdIcon.setVisible(false);
                    itemNameIcon.setVisible(false);
                    itemCategoryIcon.setVisible(false);
                    itemQuantityIcon.setVisible(false);
                    itemUnitPriceIcon.setVisible(false);
                    idTxt.setText("");
                    nameTxt.setText("");
                    quantityTxt.setText("");
                    priceTxt.setText("");
                    categoryTxt.setText("");
                    getAll();
                    NotificationController.animationMesseage("/assets/tick.gif", "Update",
                            "Item Updated sucessfully !!");

                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    void getAll() {
        try {
            ObservableList<ItemTM> itemData = ItemModel.getAll();
            tblItem.setItems(itemData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        ////////////////////
        colPrice.setCellFactory(new Callback<TableColumn<ItemTM, Double>, TableCell<ItemTM, Double>>() {
            private final DecimalFormat format = new DecimalFormat("#.00");

            @Override
            public TableCell<ItemTM, Double> call(TableColumn<ItemTM, Double> column) {
                return new TableCell<ItemTM, Double>() {
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

        //////////////////////

    }

    public void tblClick(MouseEvent mouseEvent) {
        itemIdIcon.setVisible(true);
        itemNameIcon.setVisible(true);
        itemCategoryIcon.setVisible(true);
        itemQuantityIcon.setVisible(true);
        itemUnitPriceIcon.setVisible(true);

        saveBtn.setDisable(false);
        updateBtn.setDisable(false);
        deleteBtn.setDisable(false);
        TablePosition pos = tblItem.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<ItemTM, ?>> columns = tblItem.getColumns();

        idTxt.setText(columns.get(0).getCellData(row).toString());
        nameTxt.setText(columns.get(1).getCellData(row).toString());
        quantityTxt.setText(columns.get(2).getCellData(row).toString());
        priceTxt.setText(columns.get(3).getCellData(row).toString());
        categoryTxt.setText(columns.get(4).getCellData(row).toString());
    }

    @FXML
    void itemCategoreyKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.customerNameValidate(categoryTxt.getText());
        saveBtn.setDisable(!isValidate|nameTxt.getText().isEmpty()|quantityTxt.getText().isEmpty()
                |priceTxt.getText().isEmpty()|idTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|nameTxt.getText().isEmpty()|quantityTxt.getText().isEmpty()
                |priceTxt.getText().isEmpty()|idTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|nameTxt.getText().isEmpty()|quantityTxt.getText().isEmpty()
                |priceTxt.getText().isEmpty()|idTxt.getText().isEmpty());
        if (isValidate){
            itemCategoryIcon.setVisible(true);
        }else {
            itemCategoryIcon.setVisible(false);
        }

    }

    @FXML
    void itemIdKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.itemIdValidate(idTxt.getText());
        saveBtn.setDisable(!isValidate|nameTxt.getText().isEmpty()|quantityTxt.getText().isEmpty()
                |priceTxt.getText().isEmpty()|categoryTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|nameTxt.getText().isEmpty()|quantityTxt.getText().isEmpty()
                |priceTxt.getText().isEmpty()|categoryTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|nameTxt.getText().isEmpty()|quantityTxt.getText().isEmpty()
                |priceTxt.getText().isEmpty()|categoryTxt.getText().isEmpty());
        if (isValidate){
            itemIdIcon.setVisible(true);
        }else {
            itemIdIcon.setVisible(false);
        }

    }

    @FXML
    void itemNameKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.customerNameValidate(nameTxt.getText());
        saveBtn.setDisable(!isValidate|idTxt.getText().isEmpty()|quantityTxt.getText().isEmpty()
                |priceTxt.getText().isEmpty()|categoryTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|idTxt.getText().isEmpty()|quantityTxt.getText().isEmpty()
                |priceTxt.getText().isEmpty()|categoryTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|idTxt.getText().isEmpty()|quantityTxt.getText().isEmpty()
                |priceTxt.getText().isEmpty()|categoryTxt.getText().isEmpty());
        if (isValidate){
            itemNameIcon.setVisible(true);
        }else {
            itemNameIcon.setVisible(false);
        }

    }

    @FXML
    void itemQuantityKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.quantityValidate(quantityTxt.getText());
        saveBtn.setDisable(!isValidate|idTxt.getText().isEmpty()|nameTxt.getText().isEmpty()
                |priceTxt.getText().isEmpty()|categoryTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|idTxt.getText().isEmpty()|nameTxt.getText().isEmpty()
                |priceTxt.getText().isEmpty()|categoryTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|idTxt.getText().isEmpty()|nameTxt.getText().isEmpty()
                |priceTxt.getText().isEmpty()|categoryTxt.getText().isEmpty());
        if (isValidate){
            itemQuantityIcon.setVisible(true);
        }else {
            itemQuantityIcon.setVisible(false);
        }
    }

    @FXML
    void itemunitPriceKeyTyped(KeyEvent event) {
        boolean isValidate= DataValidateController.priceValidate(priceTxt.getText());
        saveBtn.setDisable(!isValidate|idTxt.getText().isEmpty()|nameTxt.getText().isEmpty()
                |quantityTxt.getText().isEmpty()|categoryTxt.getText().isEmpty());
        updateBtn.setDisable(!isValidate|idTxt.getText().isEmpty()|nameTxt.getText().isEmpty()
                |quantityTxt.getText().isEmpty()|categoryTxt.getText().isEmpty());
        deleteBtn.setDisable(!isValidate|idTxt.getText().isEmpty()|nameTxt.getText().isEmpty()
                |quantityTxt.getText().isEmpty()|categoryTxt.getText().isEmpty());
        if (isValidate){
            itemUnitPriceIcon.setVisible(true);
        }else {
            itemUnitPriceIcon.setVisible(false);
        }

    }


    @FXML
    void initialize() {
        saveBtn.setDisable(true);
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        txtfieldbordercolor(idTxt);
        txtfieldbordercolor(nameTxt);
        txtfieldbordercolor(categoryTxt);
        txtfieldbordercolor(quantityTxt);
        txtfieldbordercolor(priceTxt);
        getAll();
        getCellValueFactory();

    }
}
