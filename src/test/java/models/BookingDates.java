package models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"checkin", "checkout"})
public class BookingDates {

    private String checkin;
    private String checkout;
}
