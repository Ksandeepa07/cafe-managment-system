package dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplierTM {
    private String id;
    private String name;
    private String contact;
    private String address;
    private String email;
}
