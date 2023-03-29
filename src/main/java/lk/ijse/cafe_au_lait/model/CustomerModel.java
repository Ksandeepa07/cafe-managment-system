package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.db.DBConnection;
import lk.ijse.cafe_au_lait.dto.Customer;
import lk.ijse.cafe_au_lait.dto.Event;
import lk.ijse.cafe_au_lait.dto.tm.CustomerTM;
import lk.ijse.cafe_au_lait.dto.tm.EventTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {
    public static boolean save(Customer customer) throws SQLException {
        String sql="INSERT INTO customer (custId,custName,phoneNumber,email)" +
                "VALUES(?,?,?,?)";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,customer.getCustId());
            pstm.setString(2,customer.getCustName());
            pstm.setString(3,customer.getCustContact());
            pstm.setString(4,customer.getCustEmail());

            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    public static ObservableList<CustomerTM> getAll() throws SQLException {
        String sql="SELECT * FROM customer";
        PreparedStatement pstm=DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet=pstm.executeQuery();
        ObservableList<CustomerTM> customerData= FXCollections.observableArrayList();

        while(resultSet.next()){
            customerData.add(new CustomerTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));
        }
        return customerData;
    }

    public static Customer searchById(String text) throws SQLException {
        String sql="SELECT * FROM customer WHERE custId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,text);
            ResultSet resultSet=pstm.executeQuery();

            if(resultSet.next()){
                return new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
        }
        return null;
    }

    public static boolean update(Customer customer) throws SQLException {
        String sql="UPDATE customer SET custName=?,phoneNumber=?,email=?" +
                "WHERE custId=? ";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1,customer.getCustName());
            pstm.setString(2,customer.getCustContact());
            pstm.setString(3,customer.getCustEmail());
            pstm.setString(4,customer.getCustId());

            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    public static boolean delete(String text) throws SQLException {
        String sql="DELETE FROM customer WHERE custId=?";
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

