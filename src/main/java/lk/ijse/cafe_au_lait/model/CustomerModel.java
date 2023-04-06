package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.dto.Customer;
import lk.ijse.cafe_au_lait.dto.tm.CustomerTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;
import lk.ijse.cafe_au_lait.util.NotificationController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class CustomerModel {
    public static boolean save(Customer customer) {
        String sql = "INSERT INTO customer (custId,custName,phoneNumber,email)" +
                "VALUES(?,?,?,?)";

        try {
            return CrudUtil.execute(sql,
                    customer.getCustId(),
                    customer.getCustName(),
                    customer.getCustContact(),
                    customer.getCustEmail());
        } catch (SQLIntegrityConstraintViolationException throwables) {
            NotificationController.ErrorMasseage("This Customer Id is Already Exsits");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static ObservableList<CustomerTM> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";

        ObservableList<CustomerTM> customerData = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            customerData.add(new CustomerTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));
        }
        return customerData;
    }

    public static Customer searchById(String text) {
        String sql = "SELECT * FROM customer WHERE custId=?";
        ResultSet resultSet = null;
        try {
            resultSet = CrudUtil.execute(sql, text);
            if (resultSet.next()) {
                return new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET custName=?,phoneNumber=?,email=?" +
                "WHERE custId=? ";

        return CrudUtil.execute(sql,

                customer.getCustName(),
                customer.getCustContact(),
                customer.getCustEmail(),
                customer.getCustId());
    }

    public static boolean delete(String text) throws SQLException {
        String sql = "DELETE FROM customer WHERE custId=?";
        return CrudUtil.execute(sql, text);
    }

    public static ObservableList<String> loadCustId() {
        String sql = "SELECT * FROM customer";
        ObservableList<String> custData = FXCollections.observableArrayList();
        try {

            ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()) {
                custData.add(
                        resultSet.getString(1)
                );
            }
            return custData;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


}

