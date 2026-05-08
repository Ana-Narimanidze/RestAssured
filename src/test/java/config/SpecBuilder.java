package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

    public static RequestSpecification getBookingSpec() {

        return new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .setContentType("application/json")
                .build();
    }

    public static RequestSpecification getBookStoreSpec() {

        return new RequestSpecBuilder()
                .setBaseUri("https://bookstore.toolsqa.com")
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }
}
