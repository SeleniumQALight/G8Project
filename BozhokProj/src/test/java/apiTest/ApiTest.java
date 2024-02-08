package apiTest;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {
    final String USER_NAME = "autoapi";
    Logger logger = LogManager.getLogger(getClass());

    @Test
    public void getAllPostByUser() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.POSTS_BY_USER, USER_NAME) //URL
                .then()
                .log().all()
                .statusCode(200);
    }
}
