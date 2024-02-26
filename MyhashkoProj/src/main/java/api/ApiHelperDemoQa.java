package api;

import api.dto.demoQa.requestDto.AddBookToUserDto;
import api.dto.demoQa.requestDto.CollectionDto;
import api.dto.demoQa.requestDto.DeleteBookDto;
import api.dto.demoQa.responseDto.LoginDto;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class ApiHelperDemoQa {

    RequestSpecification requestSpecification = new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    ResponseSpecification responseSpecification(int statusCode) {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(statusCode)
                .build();
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

    public String getToken(String login, String password) {
        LoginDto loginResponseBody = loginAsDto(login, password);
        return loginResponseBody.getToken().replace("\"", "");
    }

    public String getUserId(String login, String password) {
        LoginDto loginResponseBody = loginAsDto(login, password);
        return loginResponseBody.getUserId().replace("\"", "");
    }

    private void deleteBookById(String token, String isbn, String userId) {
        DeleteBookDto deleteBookDto = DeleteBookDto
                .builder()
                .userId(userId)
                .isbn(isbn)
                .build();
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(deleteBookDto)
                .delete(EndPointsDemoQa.DELETE_BOOKS)
                .then()
                .spec(responseSpecification(HttpStatus.SC_NO_CONTENT));
    }

    public Response getAllBooksByUserId(String userId, String token) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPointsDemoQa.GET_BOOKS_BY_USER_ID, userId)
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }

    public void deleteAllBooksOfUser(String userId, String token) {
        Response response = getAllBooksByUserId(userId, token);

        List<String> isbnList = response.jsonPath().getList("books.isbn");
        for (int i = 0; i < isbnList.size(); i++) {
            deleteBookById(token, isbnList.get(i), userId);
        }
    }

    public ValidatableResponse getListOfAllBooks(){
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsDemoQa.All_Books)
                .then()
                .spec(responseSpecification);
    }

    public Response addBookToUserByIsbn(String userId, String isbn, String token){

        ArrayList<CollectionDto> isbnList = new ArrayList<CollectionDto>();
        isbnList.add(CollectionDto.builder().isbn(isbn).build());
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(AddBookToUserDto.builder().userId(userId).collectionOfIsbns(isbnList).build())
                .post(EndPointsDemoQa.All_Books)
                .then()
                .spec(responseSpecification(HttpStatus.SC_CREATED))
                .extract()
                .response();
    }
}
