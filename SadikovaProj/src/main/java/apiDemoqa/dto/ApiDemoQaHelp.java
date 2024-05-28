package apiDemoqa.dto;


import apiDemoqa.dto.requestDTO.AddBooksDTO;
import apiDemoqa.dto.requestDTO.BookStoreDTO;
import apiDemoqa.dto.responceDTO.BookDTO;
import apiDemoqa.dto.responceDTO.LoginDTO;
import data.TestData;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class ApiDemoQaHelp {

    Logger logger = Logger.getLogger(getClass());

    static {
        // Устанавливаем парсер по умолчанию
        RestAssured.defaultParser = Parser.JSON;
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public LoginDTO login(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        Response response = given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPointsDemoqa.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response();

        // Логирование статуса ответа и тела
        logger.info("Response status code: " + response.getStatusCode());
        logger.info("Response body: " + response.getBody().asString());

        // Проверка, что тело ответа не пустое
        if (response.getBody().asString().isEmpty()) {
            throw new RuntimeException("Ответ сервера пустой");
        }

        return response.as(LoginDTO.class);
    }

    public String getToken() {
        LoginDTO loginDTO = login(TestData.LOGIN_DEMO_QA, TestData.VALID_PASSWORD_API_DEMO_QA);
        return loginDTO.getToken();
    }

    public String getUserId() {
        LoginDTO loginDTO = login(TestData.LOGIN_DEMO_QA, TestData.VALID_PASSWORD_API_DEMO_QA);
        return loginDTO.getUserId();
    }

    public String getUserName() {
        LoginDTO loginDTO = login(TestData.LOGIN_DEMO_QA, TestData.VALID_PASSWORD_API_DEMO_QA);
        return loginDTO.getUsername();
    }

    public ValidatableResponse deleteAllBooks(String token, String userId) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(EndPointsDemoqa.DELETE_BOOKS, userId)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_NO_CONTENT));

    }

    public BookStoreDTO getAllBooksStore(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        Response response = given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .get(EndPointsDemoqa.BOOKS_IN_BOOKSTORE)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK))
                .extract().response();
        return response.as(BookStoreDTO.class);
    }

    public String getIsbn() {
        BookStoreDTO bookStore = getAllBooksStore(TestData.LOGIN_DEMO_QA, TestData.VALID_PASSWORD_API_DEMO_QA);
        if (bookStore.getBooks() != null && !bookStore.getBooks().isEmpty()) {
            return bookStore.getBooks().get(0).getIsbn();
        }
        return null;
    }

    /**
     * Добавляет книгу в коллекцию пользователя.
     *
     * <p>Этот метод принимает токен для авторизации, идентификатор пользователя и номер ISBN,
     * чтобы добавить указанную книгу в коллекцию пользователя. Метод создает необходимый JSON-запрос
     * и отправляет его на сервер.
     *
     * @param token  Токен авторизации пользователя.
     * @param userId Идентификатор пользователя.
     * @param isbn   Номер ISBN книги, которую нужно добавить в коллекцию.
     * @return Объект ValidatableResponse, который позволяет выполнять дополнительные проверки ответа.
     */
    public ValidatableResponse addBook(String token, String userId, String isbn) {
        // Создаем объект BookDTO с указанным ISBN
        BookDTO bookDTO = BookDTO.builder().isbn(isbn).build();

        // Создаем объект AddBooksDTO с идентификатором пользователя и коллекцией ISBN
        AddBooksDTO addBooksDTO = AddBooksDTO.builder()
                .userId(userId)
                .collectionOfIsbns(new ArrayList<>(Collections.singletonList(bookDTO)))
                .build();

        // Отправляем POST-запрос для добавления книги в коллекцию пользователя
        Response response = given()
                .spec(requestSpecification) // Устанавливаем спецификацию запроса
                .header("Authorization", "Bearer " + token) // Добавляем заголовок авторизации
                .body(addBooksDTO) // Устанавливаем тело запроса
                .when()
                .post(EndPointsDemoqa.BOOKS_IN_BOOKSTORE) // Указываем endpoint для добавления книги
                .then()
                .spec(responseSpecification.statusCode(201)) // Устанавливаем спецификацию ответа
                .extract().response(); // Извлекаем и возвращаем ответ

        // Логируем статус ответа и тело ответа для отладки
        logger.info("Response status code: " + response.getStatusCode());
        logger.info("Response body: " + response.getBody().asString());

        // Возвращаем объект ValidatableResponse для дополнительных проверок
        return response.then();
    }

    public Response getAddedUserPost(String userId, String token) {
        Response response = given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token) // Добавляем заголовок авторизации
                .when()
                .get(EndPointsDemoqa.BOOKS_BY_USER, userId)
                .then()
                .spec(responseSpecification.statusCode(200))
                .extract().response();

        return response;

    }

    public void verifyIsbnInResponse(Response response, String expectedIsbn) {
        ValidatableResponse validatableResponse = response.then();
        validatableResponse
                .body("books.size()", equalTo(1))
                .body("books[0].isbn", equalTo(expectedIsbn));
        logger.info("Response ISBN matches expected ISBN: " + expectedIsbn);
    }
}

