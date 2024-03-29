package com.example.cloudbees.acceptance;

import com.example.cloudbees.dto.BookingDetails;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest {

    static final String SERVICE_URL = "http://localhost:8080";

    @Test
    public void testTicketBook() {

        BookingDetails body = new BookingDetails("First", "Name", "mail@gmail.com", "London", "Paris", 20);

        BookingDetails bookingDetails = given()
                .relaxedHTTPSValidation()
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(body)
                .post(SERVICE_URL + "/book")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().as(BookingDetails.class);
        assertEquals("mail@gmail.com", bookingDetails.getEmailId());
    }

    protected RequestSpecification given() {

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation().log().all();
    }
}
