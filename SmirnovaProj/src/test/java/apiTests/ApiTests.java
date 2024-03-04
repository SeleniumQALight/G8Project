package apiTests;

import api.Endpoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {
    given()
            .contentType(ContentType.JSON)
            .when()
            .get(Endpoints.POST_BY_USER, USER_NAME)
            .then()
            .statusCode(200);


    }
}
