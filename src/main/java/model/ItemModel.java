package model;

import db.DBConnection;
import dto.Item;
import dto.tm.ItemTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemModel {
    public static boolean save(Item item) throws SQLException {
        String sql="INSERT INTO item(ItemId,ItemName,ItemQuantity" +
                ",ItemUnitPrice,ItemCategory)VALUE(?,?,?,?,?)";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,item.getId());
            pstm.setString(2,item.getName());
            pstm.setInt(3,item.getQuantity());
            pstm.setDouble(4,item.getPrice());
            pstm.setString(5,item.getCategory());

            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    public static ObservableList<ItemTM> getAll() throws SQLException {
        String sql="SELECT * FROM item";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet=pstm.executeQuery();
            ObservableList<ItemTM> itemData= FXCollections.observableArrayList();

            while (resultSet.next()){
                itemData.add(new ItemTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                ));
            }
            return itemData;
        }
    }

    public static Item searchById(String text) throws SQLException {
        String sql="SELECT * FROM item WHERE itemId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,text);
            ResultSet resultSet=pstm.executeQuery();

            if(resultSet.next()){
                return  new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                        );
            }
        }
        return null;
    }

    public static boolean update(Item item) throws SQLException {
        String sql="UPDATE item SET itemName=?,itemQuantity=?,itemUnitPrice=?,ItemCategory=? " +
                "WHERE itemId=? ";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,item.getName());
            pstm.setInt(2, item.getQuantity());
            pstm.setDouble(3, item.getPrice());
            pstm.setString(4, item.getCategory());
            pstm.setString(5,item.getId());

            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }
}
