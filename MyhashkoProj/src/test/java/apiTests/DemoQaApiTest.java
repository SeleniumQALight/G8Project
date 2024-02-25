package apiTests;

import api.ApiHelperDemoQa;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_API_DEMOQA;
import static data.TestData.VALID_PASSWORD_API_DEMOQA;

public class DemoQaApiTest {
    String token;
    String userId;
    ApiHelperDemoQa apiHelperDemoQa = new ApiHelperDemoQa();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deleteAllUsersBooks() {
        token = apiHelperDemoQa.getToken(VALID_LOGIN_API_DEMOQA, VALID_PASSWORD_API_DEMOQA);//3
        userId = apiHelperDemoQa.getUserId(VALID_LOGIN_API_DEMOQA, VALID_PASSWORD_API_DEMOQA);//3
        apiHelperDemoQa.deleteAllBooksOfUser(userId, token);//4
    }

    @Test
    public void addBookToUserByIsbnTest() {
        Response responseListOfBooks = apiHelperDemoQa.getListOfAllBooks().extract().response();//5.1
        logger.info("Got List Of All Books");

        String isbn = responseListOfBooks.jsonPath().getString("books[0].isbn");//5.2
        logger.info("Got ISBN of the first book" + "ISBN: " + isbn);

        apiHelperDemoQa.addBookToUserByIsbn(userId, isbn, token);//6
        logger.info("Added book to user by ISBN: " + isbn);

        Response responseListOfBooksAfterAdd =
                apiHelperDemoQa.getAllBooksByUserId(userId, token).then().extract().response();
        logger.info("Got List Of All Books isbn after adding book to user" +
                responseListOfBooksAfterAdd.jsonPath().getString("books.isbn"));

        Assert.assertEquals("Added book is not equal to the book that user has",
                responseListOfBooks.jsonPath().getString("books[0].isbn"),
                responseListOfBooksAfterAdd.jsonPath().getString("books.isbn").replace("[", "").replace("]", ""));//7
        logger.info("Added book is equal to the book that user has");
    }
}
