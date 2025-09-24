package com.example.apiauto;

import com.example.apiauto.util.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import com.example.apiauto.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AuthTests {

    @BeforeClass
    public void setup() {
        // Base URL of Reqres API
        RestAssured.baseURI = Config.BASE_URL;
    }

    @DataProvider(name = "usersFromJson")
    public Object[][] getUsersFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(
                new File("src/test/resources/testdata/users.json"),
                new TypeReference<List<User>>() {}
        );
        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }
        return data;
    }

    @Test(dataProvider = "usersFromJson")
    public void testLogin(User user) {
        // Make the POST request
        Response response = given()
                .header("x-api-key", Config.API_KEY)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/login")
                .then()
                .extract()
                .response();

        // Print the response
        System.out.println("Response for user " + user.getEmail() + ": " + response.asString());

        // Assert that the status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Login failed for user " + user.getEmail() + "!");
    }
}
