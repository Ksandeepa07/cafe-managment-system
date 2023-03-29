package lk.ijse.cafe_au_lait.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EventTM {
    private String empId;
    private String eventId;
    private String eventName;
    private String eventType;
    private String eventDate;
    private String eventTime;
}
