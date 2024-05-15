package apiDemoqaTest;

import apiDemoqa.ApiDemoqaHelper;
import apiDemoqa.responseDTO.LoginDTO;
import data.TestDataDemoqa;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiDemoqaTests {
    private String userId;
    private String token;
    private final ApiDemoqaHelper apiDemoqaHelper = new ApiDemoqaHelper();

    @Before
    public void setUp() {
        LoginDTO login = apiDemoqaHelper.loginAsDTO(
                TestDataDemoqa.VALID_LOGIN_API_DEMOQA,
                TestDataDemoqa.VALID_PASSWORD_API_DEMOQA
        );
        token = login.getToken();
        userId = login.getUserId();
        apiDemoqaHelper.deleteAllBooksByUser(token, userId);
    }

    @Test
    public void addBookForUserTest() {
        String isbn = apiDemoqaHelper.getAllBooksInStoreRequest()
                .extract().response().jsonPath().getString("books[0].isbn");

        apiDemoqaHelper.addBookForUserRequest(token, userId, isbn);

        List<String> addedBooksForUser = apiDemoqaHelper.getAllBooksByUserRequest(token, userId)
                .extract().response().jsonPath().getList("books.isbn");

        assertThat(addedBooksForUser)
                .as("Number of books for the user should be 1.")
                .hasSize(1);
        assertThat(addedBooksForUser.get(0))
                .as("The ISBN of the added book should match the retrieved ISBN.")
                .isEqualTo(isbn);
    }
}