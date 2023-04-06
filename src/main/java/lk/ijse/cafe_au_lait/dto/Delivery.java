package lk.ijse.cafe_au_lait.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Delivery {
    private String deliverId;
    private String location;
    private String orderId;
    private String empId;
}
