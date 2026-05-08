package tests;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import models.BookingDates;
import models.BookingRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class UpdateBookingTest {

    @Epic("Booking API")
    @Feature("Update Booking")
    @Story("PUT request")
    @Test(enabled = false, description = "Update booking test")
    public void updateBookingTest() {

        BookingDates dates = BookingDates.builder()
                .checkin("2026-05-01")
                .checkout("2026-05-10")
                .build();

        BookingRequest booking = BookingRequest.builder()
                .firstname("Nino")
                .lastname("Beridze")
                .totalprice(250)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("Breakfast")
                .passportNo(null)
                .build();

        BookingRequest response =
                given()
                        .baseUri("https://restful-booker.herokuapp.com")
                        .filter(new AllureRestAssured())
                        .contentType("application/json")
                        .accept("application/json")
                        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                        .body(booking)
                        .when()
                        .put("/booking/1")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(BookingRequest.class);

        Assert.assertEquals(response.getFirstname(), "Nino");
    }
}