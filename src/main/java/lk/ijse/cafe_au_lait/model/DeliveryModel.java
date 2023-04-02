package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import lk.ijse.cafe_au_lait.dto.NewDeliverDto;
import lk.ijse.cafe_au_lait.dto.tm.DeliveryTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;
import lk.ijse.cafe_au_lait.util.NotificationController;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryModel {

    public static ObservableList<DeliveryTM> getAll() throws SQLException {
         String sql = "SELECT * FROM delivery";
        ResultSet resultSet = CrudUtil.execute(sql);
        ObservableList<DeliveryTM> deliveryData = FXCollections.observableArrayList();

        while (resultSet.next()) {
            deliveryData.add(new DeliveryTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));



        }
        return deliveryData;
    }


    public static boolean deleteById(String text) throws SQLException {
        String sql="DELETE FROM delivery WHERE deliveryId=?";
        return CrudUtil.execute(sql,text);
    }

    public static boolean update(NewDeliverDto newDeliverDto) throws SQLException {
        String sql="UPDATE delivery SET deliveryLocation=?, orderId=?,empId=? WHERE deliveryId=? ";
        return CrudUtil.execute(sql,
                newDeliverDto.getLocation(),
                newDeliverDto.getOrderId(),
                newDeliverDto.getEmpId(),
                newDeliverDto.getDeliverId());
    }
}



