package com.example.apiauto;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetCreatedUser {

    @Test(dependsOnMethods = {"com.example.apiauto.CreateUserTest.createUser_shouldReturn201_andHaveId"})
    public void getUser_shouldReturn200_andCorrectName() {
        // Base URI of the API
        RestAssured.baseURI = CreateUserTest.BASE_MOCK_URI;

        // Send GET request for the dynamically created user
        Response response = given()
                .header("Accept", "application/json")
                .get(CreateUserTest.USERS_PATH + "/" + CreateUserTest.createdUserId);

        // Print response
        response.prettyPrint();

        // Assert status code 200
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to retrieve created user!");

        // Assert name is "morpheus" and ID matches the created one
        Assert.assertEquals(response.jsonPath().getString("name"), "morpheus", "User name mismatch!");
        Assert.assertEquals(response.jsonPath().getString("id"), CreateUserTest.createdUserId, "User ID mismatch!");
    }
}
