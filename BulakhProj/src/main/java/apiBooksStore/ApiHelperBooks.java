package apiBooksStore;

import apiBooksStore.requestDTO.AddBookDTO;
import apiBooksStore.requestDTO.CollectionDTO;
import apiBooksStore.requestDTO.DeleteBookDTO;
import apiBooksStore.responsetDTO.LoginDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.*;

public class ApiHelperBooks {


    Logger logger = Logger.getLogger(getClass().getName());

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
        LoginDTO loginresponseBody = loginAsDTO(userName, password);
        return loginresponseBody.getToken().replace("\"", "");
    }

    public String getUserId(String userName, String password) {
        LoginDTO loginresponseBody = loginAsDTO(userName, password);
        return loginresponseBody.getUserId().replace("\"", "");
    }

    public LoginDTO loginAsDTO(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPointsBooks.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().as(LoginDTO.class);
    }

    public void deleteBooksBeforeCurrentDate(String token, String userId) {
        Response allBooksByUserResponse = getAllBooksOfUser(userId, token);
        List<String> books = allBooksByUserResponse.jsonPath().getList("books.isbn");

        for (int i = 0; i < books.size(); i++) {
            deleteBookByUserId(token, books.get(i), userId);
            logger.info(String.format("Book with ID='%s' deleted", books.get(i)));
        }
        logger.info("All books were deleted");
    }

    public void deleteBookByUserId(String token, String userId, String isbn) {
        DeleteBookDTO deleteBookDTO = DeleteBookDTO.builder()
                .userId(userId)
                .isbn(isbn)
                .build();

        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(deleteBookDTO)
                .delete(EndPointsBooks.DELETE_BOOKS)
                .then()
                .spec(responseSpecificationForStatusCode(SC_NO_CONTENT));
    }

    public Response getAllBooksOfUser(String token, String userId) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPointsBooks.BOOKS_BY_USER, userId)
                .then()
                .spec(responseSpecification)
                .extract().response();
    }

    public Response addBookToUser(String userId, String token, String isbn){

            ArrayList<CollectionDTO> isbnList = new ArrayList<CollectionDTO>();
            isbnList.add(CollectionDTO.builder().isbn(isbn).build());


       return given()
                    .spec(requestSpecification)
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .body(AddBookDTO.builder()
                            .userId(userId)
                            .collectionOfIsbns(isbnList)
                            .build())
                    .post(EndPointsBooks.BOOKS)
                    .then()
                    .spec(responseSpecificationForStatusCode(SC_CREATED))
                    .extract()
                    .response();
        }
    }



