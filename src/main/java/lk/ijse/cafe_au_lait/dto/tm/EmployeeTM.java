package lk.ijse.cafe_au_lait.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeTM {
    private String id;
    private String name;
    private String address;
    private String dob;
    private String nic;
    private String jobTitle;
    private String contact;
    private String email;

}
