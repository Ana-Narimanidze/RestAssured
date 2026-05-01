package tests;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.lessThan;

public class RestAssured2Test extends BaseTest {

    @Step("Create booking body with JSONObject")
    public JSONObject createBookingBody() {
        JSONObject dates = new JSONObject();
        dates.put("checkin", "2026-05-01");
        dates.put("checkout", "2026-05-10");

        JSONObject body = new JSONObject();
        body.put("firstname", "Nino");
        body.put("lastname", "Tester");
        body.put("totalprice", 200);
        body.put("depositpaid", true);
        body.put("bookingdates", dates);
        body.put("additionalneeds", "Breakfast");

        return body;
    }

    @Epic("API Tests")
    @Feature("Booking API")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Update booking with JSONObject and validate response")
    @Test
    public void updateBookingTest() {
        JSONObject body = createBookingBody();

        int bookingId =
                given()
                        .baseUri("https://restful-booker.herokuapp.com")
                        .filter(new AllureRestAssured())
                        .contentType("application/json")
                        .accept("application/json")
                        .body(body.toString())
                        .log().all()
                        .when()
                        .post("/booking")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .path("bookingid");

        body.put("firstname", "UpdatedNino");
        body.put("lastname", "UpdatedTester");

        Response response =
                given()
                        .baseUri("https://restful-booker.herokuapp.com")
                        .filter(new AllureRestAssured())
                        .contentType("application/json")
                        .accept("application/json")
                        .auth()
                        .preemptive()
                        .basic("admin", "password123")
                        .body(body.toString())
                        .log().all()
                        .when()
                        .put("/booking/" + bookingId)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        int statusCode = response.then().extract().statusCode();

        if (statusCode == 201) {
            response.then().log().body();
        }

        Assert.assertEquals(statusCode, 200);
    }

    @Epic("API Tests")
    @Feature("Books API")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate books pages < 1000 and authors")
    @Test
    public void booksPagesAndAuthorsTest() {
        Response response =
                given()
                        .baseUri("https://bookstore.toolsqa.com")
                        .filter(new AllureRestAssured())
                        .accept("application/json")
                        .log().all()
                        .when()
                        .get("/BookStore/v1/Books")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("books.pages", everyItem(lessThan(1000)))
                        .extract()
                        .response();

        Assert.assertEquals(response.path("books[0].author"), "Richard E. Silverman");
        Assert.assertEquals(response.path("books[1].author"), "Addy Osmani");
    }
}