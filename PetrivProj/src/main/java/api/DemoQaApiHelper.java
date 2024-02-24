package api;

import api.dto.requestDto.AddListOfBooksDto;
import api.dto.requestDto.CollectionOfIsbnsDto;
import api.dto.requestDto.CreatePostDto;
import api.dto.responseDto.BooksDto;
import api.dto.responseDto.UsersBooksDto;
import data.TestData;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DemoQaApiHelper {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();
    public JsonPath getToken() {
        return getToken(TestData.VALID_LOGIN_DEMO_QA_API, TestData.VALID_PASSWORD_DEMO_QA_API);
    }
    public JsonPath getToken(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("password", password);
        requestBody.put("userName", userName);
        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestBody.toMap())
                        .when()
                        .post(DemoQaEndPoints.LOGIN)
                        .then()
                        .spec(responseSpecification)
                        .extract().response().getBody();
        return responseBody.jsonPath();
    }

    public void deleteAllUserBooksTillPresent(String token, String userId) {
        deleteBooksByUserIdRequest(token, userId);
        Assert.assertEquals("Number of books ", 0, getAllUserBooksAsDto(token, userId).getBooks().length);
    }

    public ValidatableResponse getAllBooksRequest(String token) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(DemoQaEndPoints.ALL_BOOKS)
                .then()
                .spec(responseSpecification);
    }

    public BooksDto getAllBooksAsDto(String token) {
        return getAllBooksRequest(token).extract().response().getBody().as(BooksDto.class);
    }

    private ValidatableResponse deleteBooksByUserIdRequest(String token, String userId) {
        return given()
                        .spec(requestSpecification)
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .delete(DemoQaEndPoints.DELETE_ALL_USER_BOOKS, userId)
                        .then()
                        .spec(responseSpecification.statusCode(HttpStatus.SC_NO_CONTENT));
    }

    public void addBookToUser(String userId, String isbn, String token) {
        int initialNumberOfBooks = getAllUserBooksAsDto(token, userId).getBooks().length;

        AddListOfBooksDto addListOfBooksDto = AddListOfBooksDto.builder()
                .userId(userId)
                .collectionOfIsbns(new CollectionOfIsbnsDto[]{CollectionOfIsbnsDto.builder()
                        .isbn(isbn)
                        .build()})
                .build();

        UsersBooksDto actualResponseAsDto =
                given()
                        .header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(addListOfBooksDto)
                        .when()
                        .post(DemoQaEndPoints.ALL_BOOKS)
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract().response().getBody().as(UsersBooksDto.class);

        Assert.assertEquals("Number of books", initialNumberOfBooks + 1, actualResponseAsDto.getBooks().length);
    }

    public ValidatableResponse getAllUserBooksRequest(String token, String userId) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(DemoQaEndPoints.ALL_USER_BOOKS, userId)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    public UsersBooksDto getAllUserBooksAsDto(String token, String userId) {
        return getAllUserBooksRequest(token, userId).extract().response().getBody().as(UsersBooksDto.class);
    }
}
