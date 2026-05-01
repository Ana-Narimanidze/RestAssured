# RestAssured API Tests

This project contains API automation tests using Java, Maven, TestNG, RestAssured, and Allure Report.

## Technologies Used

- Java 17
- Maven
- TestNG
- RestAssured
- Allure Report
- JSONObject
- Hamcrest Matchers

## Test Cases

### Test 1: Booking Update API

API: `https://restful-booker.herokuapp.com`

Steps:
- Create booking body using `JSONObject`
- Create a booking and extract `bookingid`
- Update booking using `PUT /booking/{id}`
- Extract status code using `extract()`
- Log response body only if status code is `201`
- Validate update response status code is `200`

### Test 2: BookStore API

API: `https://bookstore.toolsqa.com/BookStore/v1/Books`

Validations:
- Verify that all books have less than 1000 pages
- Verify the first book author is `Richard E. Silverman`
- Verify the second book author is `Addy Osmani`

## Project Structure

```text
src/test/java
├── config
│   └── SpecBuilder.java
├── steps
│   ├── BookingSteps.java
│   └── BookStoreSteps.java
└── tests
    ├── BaseTest.java
    └── RestAssured2Test.java
