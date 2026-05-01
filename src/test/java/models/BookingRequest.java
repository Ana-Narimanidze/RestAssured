package models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "firstname",
        "lastname",
        "totalprice",
        "depositpaid",
        "bookingdates",
        "additionalneeds",
        "passportNo"
})
public class BookingRequest {

    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    @JsonIgnore // არ უნდა გაიგზავნოს
    private Double saleprice;

    private String passportNo;
}