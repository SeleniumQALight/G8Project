package demoQaTests;

import api.demoQA.DemoQaApiHelper;
import api.demoQA.DemoQaEndPoints;
import api.demoQA.responseDTO.BooksDto;
import io.restassured.http.ContentType;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class AddingBookTest {
    String token;
    String userId;
    Map<String,String> tokenAndUserId = new HashMap<>();
    DemoQaApiHelper demoQaApiHelper = new DemoQaApiHelper();

    @Before
    public void deletePosts() {
        tokenAndUserId = demoQaApiHelper.getTokenAndUserId();
        token = tokenAndUserId.get("token");
        userId = tokenAndUserId.get("userId");
        demoQaApiHelper.deleteAllBooksForUser(userId, token);
    }

    @Test
    public void addBookTest(){
        BooksDto[] allAvailableBooks = demoQaApiHelper.getAllAvailableBooks();

        String isbn = allAvailableBooks[0].getIsbn();

        demoQaApiHelper.addBookForUser(userId, token, isbn);

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(DemoQaEndPoints.DEMO_QA_GET_USER_BOOKS, userId) //URL
                .then()
                .log().all()
                .statusCode(SC_OK)
                .assertThat()
                .body("books[0].isbn", equalTo(isbn));
    }
}
