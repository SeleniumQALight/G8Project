package apiDemoqaTests;

import apiDemoqa.ApiDemoqaHelper;
import apiDemoqa.responseDto.LoginDTO;
import data.TestDataDemoqa;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ApiDemoqaTests {

    String token;
    String userId;

    @Before
    public void deleteBooksForUser() {
        ApiDemoqaHelper apiDemoqaHelper = new ApiDemoqaHelper();
        LoginDTO login = apiDemoqaHelper.loginAsDTO(TestDataDemoqa.VALID_LOGIN_API_DEMOQA, TestDataDemoqa.VALID_PASSWORD_API_DEMOQA);
        token = login.getToken();
        userId = login.getUserId();
        apiDemoqaHelper.deleteAllBooksByUser(token, userId);
    }

    @Test
    public void addBookForUserTest() {
        ApiDemoqaHelper apiDemoqaHelper = new ApiDemoqaHelper();
        Response response = apiDemoqaHelper.getAllBooksInStoreRequest().extract().response();
        String isbn = response.jsonPath().getString("books[0].isbn");

        apiDemoqaHelper.addBookForUserRequest(token, userId, isbn);
        List<String> addedBooksForUser = apiDemoqaHelper.getAllBooksByUserRequest(token, userId)
                .extract().response().jsonPath().getList("books.isbn");

        assertThat(addedBooksForUser).as("Size list of the book for user ").hasSize(1);
        assertThat(addedBooksForUser.get(0)).as("isbn of the book for user").isEqualTo(isbn);
    }
}
