package lk.ijse.cafe_au_lait.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CheckOrders {
    private String orderId;
    private String custId;
    private String orderDate;
    private String orderTime;
    private String orderPayment;
    private String delivery;
}
