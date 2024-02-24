package apiDemoqa;

import apiDemoqa.dto.requestDto.AddBooksDTO;
import apiDemoqa.dto.requestDto.CollectionOfIsbnsDto;
import apiDemoqa.dto.requestDto.DeleteBookDTO;
import apiDemoqa.dto.responseDto.LoginDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class ApiDemoqaHelper {

    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = responseSpecificationForStatusCode(SC_OK);

    public ResponseSpecification responseSpecificationForStatusCode(int statusCode) {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(statusCode)
                .build();
    }

    public String getToken(String userName, String password) {
        return loginAndGetDto(userName, password).getToken().replace("\"", "");
    }

    public String getUserId(String userName, String password) {
        return loginAndGetDto(userName, password).getUserId().replace("\"", "");
    }

    public LoginDTO loginAndGetDto(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPointsDemoqa.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().as(LoginDTO.class);
    }

    public void deleteAllBooks(String userId, String token) {
        Response allBooksByUserResponse = getAllBooksByUser(userId, token);
        List<String> books = allBooksByUserResponse.jsonPath().getList("books.isbn");

        for (int i = 0; i < books.size(); i++) {
            deleteBookById(token, books.get(i), userId);
            logger.info(String.format("Book with ID='%s' deleted", books.get(i)));
        }
        logger.info("Все книги пользователя удалены");
    }

    private void deleteBookById(String token, String isbn, String userId) {
        DeleteBookDTO deleteBookDTO = DeleteBookDTO.builder()
                .userId(userId)
                .isbn(isbn)
                .build();

        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(deleteBookDTO)
                .delete(EndPointsDemoqa.DELETE_BOOKS)
                .then()
                .spec(responseSpecificationForStatusCode(SC_NO_CONTENT));
    }

    public Response getAllBooksByUser(String userId, String token) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPointsDemoqa.BOOKS_BY_USER, userId)
                .then()
                .spec(responseSpecification)
                .extract().response();
    }

    public Response getListOfBooksFromBookstore() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsDemoqa.BOOKS_IN_BOOKSTORE)
                .then()
                .spec(responseSpecification)
                .extract().response();
    }

    public Response addBookForUser(String userId, String token, String isbn) {
        ArrayList<CollectionOfIsbnsDto> isbnList = new ArrayList<>();
        isbnList.add(CollectionOfIsbnsDto.builder().isbn(isbn).build());

        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(AddBooksDTO.builder()
                        .userId(userId)
                        .collectionOfIsbns(isbnList)
                        .build())
                .post(EndPointsDemoqa.BOOKS_IN_BOOKSTORE)
                .then()
                .spec(responseSpecificationForStatusCode(SC_CREATED))
                .extract()
                .response();
    }
}