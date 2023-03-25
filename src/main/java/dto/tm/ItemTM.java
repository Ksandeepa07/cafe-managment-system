package dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemTM {
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
    private String category;
}
