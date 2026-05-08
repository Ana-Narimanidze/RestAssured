package steps;

import config.SpecBuilder;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BookingSteps {

    @Step("Create booking body")
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

    @Step("Create booking")
    public int createBooking(JSONObject body) {
        return given()
                .spec(SpecBuilder.getBookingSpec())
                .filter(new AllureRestAssured())
                .body(body.toString())
                .log().all()
                .when()
                .post("/booking")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("bookingid");
    }

    @Step("Update booking")
    public Response updateBooking(int bookingId, JSONObject body) {
        return given()
                .spec(SpecBuilder.getBookingSpec())
                .filter(new AllureRestAssured())
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
    }

    @Step("Log response body if status code is 201")
    public void logIf201(Response response) {
        if (response.statusCode() == 201) {
            response.then().log().body();
        }
    }
}

