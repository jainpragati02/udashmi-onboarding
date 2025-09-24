package com.example.apiauto;

import com.example.apiauto.util.Config;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUserTest {

    @Test
    public void getUserById_shouldReturn200_andCorrectId() {
        given()
                .header("x-api-key", Config.API_KEY)
                .baseUri(Config.BASE_URL)
                .basePath("/users/2")
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", containsString("@"));
    }
}
