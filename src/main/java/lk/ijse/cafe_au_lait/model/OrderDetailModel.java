package lk.ijse.cafe_au_lait.model;

import lk.ijse.cafe_au_lait.dto.OrderDto;
import lk.ijse.cafe_au_lait.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailModel {
    public static boolean save(String oId, List<OrderDto> orderDtoList) {
        for (OrderDto orderDto : orderDtoList) {
            if (!save(oId, orderDto)) {
                return false;
            }
        }
        return true;

    }

    public static boolean save(String oId, OrderDto orderDto) {
        String sql = "INSERT INTO orderDetail (orderId,itemId,orderQuantity)VALUES(?,?,?)";
        try {
            return CrudUtil.execute(sql,
                    oId,
                    orderDto.getItemId(),
                    orderDto.getQty());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
