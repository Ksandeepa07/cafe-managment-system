package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.dto.CheckOrders;
import lk.ijse.cafe_au_lait.dto.tm.CheckOrdersTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckOrdersModel {
    public static ObservableList<CheckOrdersTM> getAll() throws SQLException {
        String sql="SELECT * FROM orders";
        ResultSet resultSet=CrudUtil.execute(sql);
        ObservableList<CheckOrdersTM> ordersData= FXCollections.observableArrayList();

        while (resultSet.next()){
            ordersData.add(new CheckOrdersTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

                    ));

        }
        return ordersData;
    }

//    public static CheckOrders searchBtId(String text) {
//    }
}
