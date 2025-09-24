package com.example.apiauto;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateUserTest {

    public static final String BASE_MOCK_URI = "https://68d0db1de6c0cbeb39a2a613.mockapi.io";
    public static final String USERS_PATH = "/users";
    public static String createdUserId; // Static variable to store the created user ID

    @Test
    public void createUser_shouldReturn201_andHaveId() {
        // JSON payload
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "leader");

        // POST request with logging
        String id = given()
                .relaxedHTTPSValidation() // in case of SSL issues
                .baseUri(BASE_MOCK_URI)
                .basePath(USERS_PATH)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all()   // log request
                .when()
                .post()
                .then()
                .log().all()   // log response
                .statusCode(201) // assert created
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .extract()
                .path("id");   // extract generated id

        createdUserId = id; // Store the created ID in the static variable
        System.out.println("Created user id = " + id);
    }

    @Test
    public void createUserAndThenRetrieveIt() {
        // 1. Create a user
        Map<String, String> createRequestBody = new HashMap<>();
        createRequestBody.put("name", "neo");
        createRequestBody.put("job", "the one");

        String createdId = given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_MOCK_URI)
                .basePath(USERS_PATH)
                .contentType(ContentType.JSON)
                .body(createRequestBody)
                .when()
                .post()
                .then()
                .statusCode(201)
                .body("name", equalTo("neo"))
                .body("job", equalTo("the one"))
                .extract()
                .path("id");

        System.out.println("Created user with ID: " + createdId);

        // 2. Retrieve the created user using the captured ID
        given()
                .relaxedHTTPSValidation()
                .baseUri(BASE_MOCK_URI)
                .basePath(USERS_PATH + "/" + createdId)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("id", equalTo(createdId))
                .body("name", equalTo("neo"))
                .body("job", equalTo("the one"));

        System.out.println("Successfully retrieved user with ID: " + createdId);
    }
}
