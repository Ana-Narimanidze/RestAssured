# RestAssured Booking API Test

## 📌 Description

This project contains automated API tests for Booking endpoint using RestAssured and TestNG.

The main goal is to test **Update Booking (PUT request)** and validate response using serialization and deserialization.

---

## 🛠️ Technologies Used

* Java 17
* RestAssured
* TestNG
* Lombok
* Jackson (JSON processing)
* Allure Report

---

## ✅ Implemented Features

* PUT request to update booking
* POJO classes with Lombok
* Serialization (Java → JSON)
* Deserialization (JSON → Java)
* Jackson annotations:

  * Custom field names
  * Field ordering
  * Ignore fields
  * Ignore null values
* Validation:

  * Status code check
  * Response body assertion
* Allure report integration

---

## ▶️ How to Run Tests

Run tests using Maven:

```bash
mvn test
```

---

## 📊 Allure Report

Generate and open report:

```bash
allure serve allure-results
```

---

## 📁 Project Structure

```
src
 ├── main
 └── test
     ├── models
     │   ├── BookingRequest.java
     │   └── BookingDates.java
     └── tests
         └── UpdateBookingTest.java
```

---

## 👩‍💻 Author

Ana Narimanidze

