package api.demoQA;

import api.demoQA.requestDTO.AddBookForUserDto;
import api.demoQA.requestDTO.IsbnDTO;
import api.demoQA.responseDTO.BooksDto;
import api.demoQA.responseDTO.GetAllBooksDto;
import api.demoQA.responseDTO.ResponseLoginDto;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.HashMap;

import static data.TestData.DEMO_QA_VALID_LOGIN;
import static data.TestData.DEMO_QA_VALID_PASSWORD;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class DemoQaApiHelper {

    public HashMap<String, String> getTokenAndUserId() {
        return getTokenAndUserId(DEMO_QA_VALID_LOGIN, DEMO_QA_VALID_PASSWORD);
    }

    public HashMap<String, String> getTokenAndUserId(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        HashMap<String, String> tokenAndId = new HashMap<>() ;

        ResponseLoginDto responseLoginDto =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(requestBody.toMap())
                        .when()
                        .post(DemoQaEndPoints.DEMO_QA_LOGIN) //URL
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response().as(ResponseLoginDto.class);

        tokenAndId.put("token", responseLoginDto.getToken());
        tokenAndId.put("userId", responseLoginDto.getUserId());
        return tokenAndId;
    }

    public void deleteAllBooksForUser(String userId, String token) {
        given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(DemoQaEndPoints.DEMO_QA_DELETE_ALL_USER_BOOKS)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    public void addBookForUser(String userId, String token, String isbn){
        IsbnDTO[] requestIsbn = {IsbnDTO.builder().isbn(isbn).build()};
        AddBookForUserDto addBookForUserRequestBodyDto =
                AddBookForUserDto.builder()
                        .userId(userId)
                        .collectionOfIsbns(requestIsbn)
                        .build()
                ;
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization", "Bearer " + token)
                .body(addBookForUserRequestBodyDto)
                .when()
                .post(DemoQaEndPoints.DEMO_QA_ADD_BOOK)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .body("books[0].isbn", equalTo(isbn));
    }

    public BooksDto[] getAllAvailableBooks(){
        GetAllBooksDto allBooksAsDto =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(DemoQaEndPoints.DEMO_QA_GET_ALL_BOOKS) //URL
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response().as(GetAllBooksDto.class);
        return allBooksAsDto.getBooks();
    }

}
