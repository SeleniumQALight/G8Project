package apiTests;

import apiDemoQa.ApiHelperDemoQa;
import apiDemoQa.responseDto.BooksDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import io.restassured.response.Response;

import static data.TestData.*;

public class DemoQaApiTest {
    String token;
    String userId;
    ApiHelperDemoQa apiHelper = new ApiHelperDemoQa();

    @Before
    public void deleteUsersBook(){
        token = apiHelper.getToken();
        userId = apiHelper.getUserId(VALID_LOGIN_API_DEMOQA, VALID_PASSWORD_API_DEMOQA);
        apiHelper.deleteAllBooksTillPresent(userId, token);

        BooksDto[] actualBooksByUser = apiHelper.getAllBooksByUserAsDto(userId, token);
        Assert.assertEquals("The number of books is not as expected", 0, actualBooksByUser.length);
    }

    @Test
    public void addBookToUserBookListByIsbnTest() {
        Response listOfBooksResponse = apiHelper.getAllBooksInListRequest().extract().response();
        String isbn = listOfBooksResponse.jsonPath().getString("books[0].isbn");

        apiHelper.addBookToUserBookListByIsbn(userId, isbn, token);

        Response actualListOfBooksByUser =
                apiHelper.getAllBooksByUserIdResponse(userId, token).then().extract().response();

        String isbnOfCurrentBook = actualListOfBooksByUser.jsonPath().getString("books[0].isbn");

        BooksDto[] actualBooksByUser = apiHelper.getAllBooksByUserAsDto(userId, token);

        Assert.assertEquals("The number of books is not as expected", 1, actualBooksByUser.length);
        Assert.assertEquals("Book has another isbn", isbn, isbnOfCurrentBook);
    }
}
