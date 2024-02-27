package apiTests;

import api.ApiHelperDemoqa;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiTestsDemoqa {
    ApiHelperDemoqa apiHelperDemoqa = new ApiHelperDemoqa();
    private String userId;
    private String token;

    @Test
    public void getBookList(){
        apiHelperDemoqa.getLinkedBook(userId, token);
    }

    @Test
    public void LoginTest() {
        ValidatableResponse loginResponse = apiHelperDemoqa.login();
        userId = loginResponse.extract().body().jsonPath().get("userId");
        token = loginResponse.extract().body().jsonPath().get("token");
        System.out.println("userID: " + userId);
        System.out.println("token: " + token);
        apiHelperDemoqa.deleteAllBooksByUser(userId, token);
        apiHelperDemoqa.getLinkedBook(userId, token);
    }

    @Test
    public void addBooksForUser() {

        ValidatableResponse loginResponse = apiHelperDemoqa.login();
        userId = loginResponse.extract().body().jsonPath().get("userId");
        token = loginResponse.extract().body().jsonPath().get("token");
        System.out.println("userID: " + userId);
        System.out.println("token: " + token);

        List<String> isbnList = apiHelperDemoqa.getBooksIsbn();
        List<String> addBookListWithOneBook = new ArrayList<>();
        addBookListWithOneBook.add(isbnList.get(0));

        List<String> addBookListWithFewBook = new ArrayList<>();
        addBookListWithFewBook.add(isbnList.get(1));
        addBookListWithFewBook.add(isbnList.get(3));
        addBookListWithFewBook.add(isbnList.get(4));

        apiHelperDemoqa.addBooks(userId, token, addBookListWithFewBook);

        apiHelperDemoqa.getLinkedBook(userId,token);
    }
}
