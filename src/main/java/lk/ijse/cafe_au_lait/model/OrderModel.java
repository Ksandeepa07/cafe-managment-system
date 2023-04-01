package lk.ijse.cafe_au_lait.model;

import lk.ijse.cafe_au_lait.dto.tm.CartTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderModel {
    public static String getNextOrderId() throws SQLException {
        String sql = "SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1";


        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);

    }

    private static String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("OD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit = String.format("%03d", id);
            return "OD-" + digit;

        }
        return "OD-001";
    }

//
//    public static boolean save(String oId, String customerId, Double orderPayment, LocalDate now, LocalTime now1, List<OrderDto> orderDtoList) {
//        for(OrderDto orderDto:orderDtoList){
//            if(!save(oId,customerId,orderPayment,now,now1,orderDto)){
//                return false;
//            }
//        }
//return true;
//    }

    public static boolean save(String oId, String customerId, Double orderPayment, LocalDate now, LocalTime now1, CartTM cartTM) {
        String sql = "INSERT INTO orders(orderId,custId,orderDate,orderTime,orderPayment,Delivery)VALUES(?," +
                "?,?,?,?,?)";

        try {
            return CrudUtil.execute(sql,
                    oId,
                    customerId,
                    now,
                    now1,
                    orderPayment,
                    cartTM.getDelivery()

            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }
}
