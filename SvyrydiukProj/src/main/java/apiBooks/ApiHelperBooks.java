package apiBooks;

import apiBooks.dto.requestDTO.AddBookToUserDTO;
import apiBooks.dto.requestDTO.CollectionDTO;
import apiBooks.dto.requestDTO.DeleteBookDTO;
import apiBooks.dto.responsetDTO.BooksDTO;
import apiBooks.dto.responsetDTO.LoginDTO;
import apiBooks.dto.responsetDTO.UsersBooksDTO;
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
import org.apache.log4j.Logger;
import org.json.JSONObject;



import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperBooks {

    public Logger logger = Logger.getLogger(getClass());

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
                .extract()
                .response()
                .as(LoginDTO.class);
    }

    public void deleteAllBooksTillPresent(String userId, String token) {
        Response response = getAllBooksByUser(userId, token);

        List<String> books = response.jsonPath().getList("books.isbn");

        for (int i = 0; i < books.size(); i++) {
            deleteBookById(token, books.get(i), userId);
            logger.info(String.format("Book with id %s was deleted", books.get(i)));
        }
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
                .delete(EndPointsBooks.DELETE_BOOKS)
                .then()
                .spec(responseSpecification(HttpStatus.SC_NO_CONTENT));
    }

    public Response getAllBooksByUser(String userId, String token) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPointsBooks.BOOKS_BY_USER, userId)
                .then()
                .spec(responseSpecification)
                .extract()
                .response();

    }

    public ValidatableResponse getFullListOfBooks() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsBooks.BOOKS)
                .then()
                .spec(responseSpecification);
    }

    public UsersBooksDTO getAllBooksByUserAsDTO(String userId, String token) {
        return getAllBooksByUser(userId, token).as(UsersBooksDTO.class);
    }

    public Response addBookForUser(String userId, String token, String isbn) {
//add isbn as a array
        ArrayList<CollectionDTO> isbnList = new ArrayList<CollectionDTO>();
        isbnList.add(CollectionDTO.builder().isbn(isbn).build());


        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(AddBookToUserDTO.builder()
                        .userId(userId)
                        .collectionOfIsbns(isbnList)
                        .build())
                .post(EndPointsBooks.BOOKS)
                .then()
                .spec(responseSpecification(HttpStatus.SC_CREATED))
                .extract()
                .response();
    }

    public BooksDTO[] getFullListOfBooksAsDto() {
        return getFullListOfBooks().extract().response().getBody().as(BooksDTO[].class);
    }
}
