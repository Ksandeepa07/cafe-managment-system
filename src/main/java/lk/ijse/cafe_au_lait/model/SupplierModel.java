package lk.ijse.cafe_au_lait.model;

import lk.ijse.cafe_au_lait.db.DBConnection;
import lk.ijse.cafe_au_lait.dto.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.dto.tm.SupplierTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierModel {
    public static boolean save(Supplier supplier) throws SQLException {
        String sql="INSERT INTO supplier(SupplierId,SupplierName,SupplierContact" +
                ",SupplierAddress,SupplierEmail)VALUES (?,?,?,?,?)";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,supplier.getId());
            pstm.setString(2,supplier.getName());
            pstm.setString(3,supplier.getContact());
            pstm.setString(4,supplier.getAddress());
            pstm.setString(5,supplier.getEmail());

            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    public static ObservableList<SupplierTM> getAll() throws SQLException {
        String sql="SELECT * FROM Supplier";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            ObservableList<SupplierTM> supplierData= FXCollections.observableArrayList();
            ResultSet resultSet =pstm.executeQuery();

            while(resultSet.next()){
                supplierData.add(new SupplierTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
            return supplierData;
        }
    }

    public static Supplier searchById(String text) throws SQLException {
        String sql="SELECT * FROM supplier WHERE SupplierId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,text);
            ResultSet resultSet=pstm.executeQuery();

            if(resultSet.next()){
                return new Supplier(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            }
            return null;
        }
    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql="UPDATE supplier SET supplierName=?,supplierContact=?,supplierAddress=?" +
                ",supplierEmail=? WHERE supplierId=?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,supplier.getName());
            pstm.setString(2,supplier.getContact());
            pstm.setString(3,supplier.getAddress());
            pstm.setString(4,supplier.getEmail());
            pstm.setString(5,supplier.getId());
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    public static boolean delete(String text) throws SQLException {
        String sql="DELETE FROM supplier WHERE supplierId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,text);
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }
}
