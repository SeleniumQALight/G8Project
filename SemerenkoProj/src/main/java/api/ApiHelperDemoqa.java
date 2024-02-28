package api;

import api.dto.requestDto.AddBookDemoqaDto;
import api.dto.requestDto.CollectionOfIsbnDemoqaDto;
import api.dto.responseDto.UserInfoDemoqaDto;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelperDemoqa {
    private String userName = "NickSem";
    private String password = "1qazXSW@";
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse login() {
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("userName", userName);
        requestBody.put("password", password);
        return given()
                .spec(requestSpecification)
                .body(requestBody)
                .when()
                .post(EndPointsDemoqa.LOGIN_LOCATOR)
                .then()
                .spec(responseSpecification);
    }

    public List<Map> getBooksList() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsDemoqa.BOOK_STORE_LOCATOR)
                .then()
                .spec(responseSpecification)
                .extract().response().jsonPath().getList("books", Map.class);

    }

    public UserInfoDemoqaDto getLinkedBook(String userId, String token) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                //.queryParam("UserId", userId)
                .get(EndPointsDemoqa.USER_LOCATOR, userId)
                .then()
                .spec(responseSpecification)
                .extract().body().as(UserInfoDemoqaDto.class);

    }

    public void deleteAllBooksByUser(String userId, String token) {
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .queryParam("UserId", userId)
                .delete(EndPointsDemoqa.BOOK_STORE_LOCATOR)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .log().all();

    }

    public List<String> getBooksIsbn() {
        List<String> isbnList = new ArrayList<>();
        List<Map> booksList = getBooksList();
        for (int i = 0; i < booksList.size(); i++) {
            System.out.println(booksList.get(i).get("isbn"));
            isbnList.add(booksList.get(i).get("isbn").toString());
        }
        return isbnList;
    }

    public void addBooks(String userId, String token, List<String> isbnList) {
        List<CollectionOfIsbnDemoqaDto> collectionOfIsbnDemoqaDtosList = new ArrayList<>();
        for (int i = 0; i < isbnList.size(); i++) {
            collectionOfIsbnDemoqaDtosList.add(CollectionOfIsbnDemoqaDto.builder()
                    .isbn(isbnList.get(i))
                    .build()
            );
        }

        AddBookDemoqaDto bookRequestDto = AddBookDemoqaDto.builder()
                .userId(userId)
                .collectionOfIsbns(collectionOfIsbnDemoqaDtosList)
                .build();

        String response =
                given()
                        .spec(requestSpecification)
                        .body(bookRequestDto)
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .post(EndPointsDemoqa.BOOK_STORE_LOCATOR)
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .log().all()
                        .extract().body().toString();
    }


}
