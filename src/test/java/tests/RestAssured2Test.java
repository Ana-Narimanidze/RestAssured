package tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.BookingSteps;
import steps.BookStoreSteps;

public class RestAssured2Test extends BaseTest {

    BookingSteps bookingSteps = new BookingSteps();
    BookStoreSteps bookStoreSteps = new BookStoreSteps();

    @Epic("API Tests")
    @Feature("Booking API")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Update booking with JSONObject and validate response")
    @Test
    public void updateBookingTest() {

        JSONObject body = bookingSteps.createBookingBody();

        int bookingId = bookingSteps.createBooking(body);

        body.put("firstname", "UpdatedNino");
        body.put("lastname", "UpdatedTester");

        Response response = bookingSteps.updateBooking(bookingId, body);

        bookingSteps.logIf201(response);

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Epic("API Tests")
    @Feature("Books API")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate books pages < 1000 and authors")
    @Test
    public void booksPagesAndAuthorsTest() {

        Response response = bookStoreSteps.getBooks();

        Assert.assertEquals(response.path("books[0].author"), "Richard E. Silverman");
        Assert.assertEquals(response.path("books[1].author"), "Addy Osmani");
    }
}