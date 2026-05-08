package steps;

import config.SpecBuilder;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.lessThan;

public class BookStoreSteps {

    @Step("Get all books and validate pages are less than 1000")
    public Response getBooks() {
        return given()
                .spec(SpecBuilder.getBookStoreSpec())
                .filter(new AllureRestAssured())
                .log().all()
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .log().all()
                .statusCode(200)
                .body("books.pages", everyItem(lessThan(1000)))
                .extract()
                .response();
    }
}