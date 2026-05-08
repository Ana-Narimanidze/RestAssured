package tests;

import config.SpecBuilder;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.path.json.JsonPath;
import models.AuthRequest;
import models.BookingDates;
import models.BookingRequest;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class RestAssured3Test {

    @Test
    public void createTokenAndDeleteBookingTest() {

        AuthRequest authRequest = AuthRequest.builder()
                .username("admin")
                .password("password123")
                .build();

        String token = given()
                .spec(SpecBuilder.getBookingSpec())
                .filter(new AllureRestAssured())
                .log().all()
                .body(authRequest)
                .when()
                .post("/auth")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("token");

        assertNotNull(token);

        BookingDates dates = BookingDates.builder()
                .checkin("2026-05-01")
                .checkout("2026-05-10")
                .build();

        BookingRequest bookingRequest = BookingRequest.builder()
                .firstname("Nino")
                .lastname("Tester")
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("Breakfast")
                .build();

        int bookingId = given()
                .spec(SpecBuilder.getBookingSpec())
                .filter(new AllureRestAssured())
                .log().all()
                .body(bookingRequest)
                .when()
                .post("/booking")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("bookingid");

        given()
                .spec(SpecBuilder.getBookingSpec())
                .filter(new AllureRestAssured())
                .log().all()
                .cookie("token", token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void validateBookStoreBooksTest() {

        JsonPath jsonPath = given()
                .spec(SpecBuilder.getBookStoreSpec())
                .filter(new AllureRestAssured())
                .log().all()
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath();

        List<Integer> pages = jsonPath.getList("books.pages", Integer.class);

        for (Integer page : pages) {
            assertTrue(page < 1000);
        }

        assertEquals(jsonPath.getString("books[0].author"),
                "Richard E. Silverman");

        assertEquals(jsonPath.getString("books[1].author"),
                "Addy Osmani");
    }
}