package apiTests;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static java.lang.Math.log;

public class ApiTests {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());
    @Test
    public void getAllPostsByUser() {
        given().contentType(ContentType.JSON)
                .when()
                .get(EndPoints.POSTS_BY_USER,USER_NAME) //URL
                .then()
                .log().all()
                .statusCode(200);
    }
}
