package lk.ijse.cafe_au_lait.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeliveryTM {
    private String deliveryId;
    private String location;
    private String orderId;
    private String employeeId;


}
