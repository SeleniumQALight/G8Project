package apiDemoqa;

import api.EndPoints;
import apiDemoqa.responseDTO.AddBooksForUserDTO;
import apiDemoqa.responseDTO.IsbnDTO;
import apiDemoqa.responseDTO.LoginDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiDemoqaHelper {

    private final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private final ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public LoginDTO loginAsDTO(String username, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", username);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPoints.DEMOQA_LOGIN)
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .as(LoginDTO.class);
    }

    public ValidatableResponse getAllBooksByUserRequest(String token, String userId, int statusCode) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.BOOKS_BY_USER, userId)
                .then()
                .spec(responseSpecification.statusCode(statusCode));
    }

    public ValidatableResponse getAllBooksByUserRequest(String token, String userId) {
        return getAllBooksByUserRequest(token, userId, HttpStatus.SC_OK);
    }

    public ValidatableResponse deleteAllBooksByUser(String token, String userId) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(EndPoints.BOOK_STORE_LIST_OF_BOOKS_BY_USER, userId)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_NO_CONTENT));
    }

    public ValidatableResponse getAllBooksInStoreRequest() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.BOOKS)
                .then()
                .spec(responseSpecification);
    }

    public ValidatableResponse addBookForUserRequest(String token, String userId, String isbn) {
        IsbnDTO isbnDTO = IsbnDTO.builder().isbn(isbn).build();
        List<IsbnDTO> collectionOfIsbns = new ArrayList<>();
        collectionOfIsbns.add(isbnDTO);

        AddBooksForUserDTO requestBody = AddBooksForUserDTO.builder()
                .userId(userId)
                .collectionOfIsbns(collectionOfIsbns)
                .build();

        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .post(EndPoints.BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_CREATED));
    }
}
