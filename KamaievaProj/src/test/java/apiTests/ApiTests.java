package apiTests;

import api.EndPoints;
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
                .log().all()
                .when()
                .get(EndPoints.POSTS_BY_USER, USER_NAME)
                .then()
                .log().all()
                .statusCode(200);
    }
}
