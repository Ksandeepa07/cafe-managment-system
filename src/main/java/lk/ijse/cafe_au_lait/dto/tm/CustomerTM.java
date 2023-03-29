package lk.ijse.cafe_au_lait.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CustomerTM {
    private String custId;
    private String custName;
    private String custContact;
    private String custEmail;
}
