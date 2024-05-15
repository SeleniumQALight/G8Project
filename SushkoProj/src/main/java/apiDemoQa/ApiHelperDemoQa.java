package apiDemoQa;

import apiDemoQa.responseDto.*;
import data.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class ApiHelperDemoQa {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public String getToken() {
        LoginDto loginDto = loginAsDto(TestData.VALID_LOGIN_API_DEMOQA, TestData.VALID_PASSWORD_API_DEMOQA);
        return loginDto.getToken();
    }

    public String getUserId(String login, String password) {
        LoginDto loginResponseBody = loginAsDto(login, password);
        return loginResponseBody.getUserId().replace("\"", "");
    }

    public LoginDto loginAsDto(String login, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", login);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPointsDemoQa.LOGIN)
                .then().
                spec(responseSpecification)
                .extract()
                .response()
                .as(LoginDto.class);
    }

    public void deleteAllBooksTillPresent(String userId, String token) {
        BooksDto[] listOfBooks = getAllBooksByUserAsDto(userId, token);

        for (int i = 0; i < listOfBooks.length; i++) {
            deleteBookById(token, userId);
            logger.info(String.format("Post with id %s and 'title' %s was deleted", listOfBooks[i].getIsbn(), listOfBooks[i].getTitle()));
        }
        getAllBooksByUserRequest(userId, token);
    }

    private void deleteBookById(String token, String userId) {
        String actualResponse =
                given()
                        .spec(requestSpecification)
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .delete(EndPointsDemoQa.BOOKS_BY_USER, userId) //URL with id
                        .then()
                        .spec(responseSpecification.statusCode(HttpStatus.SC_NO_CONTENT))
                        .extract().response().body().asString();
    }

    public BooksDto[] getAllBooksByUserAsDto(String userId, String token) {
        return getAllBooksByUserIdRequest(userId, token)
                .extract()
                .response()
                .getBody()
                .jsonPath()
                .getList("books", BooksDto.class)
                .toArray(new BooksDto[0]);
    }

    public ValidatableResponse getAllBooksByUserRequest(String userId,  String token){
        return getAllBooksByUserIdRequest(userId, token);
    }

    public ValidatableResponse getAllBooksByUserIdRequest(String userId, String token) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPointsDemoQa.BOOKS_BY_USER, userId)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    public Response getAllBooksByUserIdResponse(String userId, String token) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPointsDemoQa.BOOKS_BY_USER, userId)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK))
                .extract()
                .response();
    }

    public ValidatableResponse getAllBooksInListRequest() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsDemoQa.LIST_OF_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    public Response addBookToUserBookListByIsbn(String userId, String isbn, String token){

        ArrayList<IsbnDTO> isbnList = new ArrayList<>();
        isbnList.add(IsbnDTO.builder().isbn(isbn).build());

        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(AddBooksForUserDTO.builder().userId(userId).collectionOfIsbns(isbnList).build())
                .post(EndPointsDemoQa.LIST_OF_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_CREATED))
                .extract()
                .response();
    }
}
