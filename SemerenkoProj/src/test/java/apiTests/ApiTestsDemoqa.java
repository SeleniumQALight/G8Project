package apiTests;

import api.ApiHelperDemoqa;
import api.dto.responseDto.UserInfoDemoqaDto;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiTestsDemoqa {
    ApiHelperDemoqa apiHelperDemoqa = new ApiHelperDemoqa();
    private String userId;
    private String token;


    @Test
    public void addBooksForUserAndCheckThis() {
        ValidatableResponse loginResponse = apiHelperDemoqa.login();
        userId = loginResponse.extract().body().jsonPath().get("userId");
        token = loginResponse.extract().body().jsonPath().get("token");

        apiHelperDemoqa.deleteAllBooksByUser(userId, token);


        List<String> bookListForAdd = getBooksForAdd(3);  // int - count of books, wich need to add

        apiHelperDemoqa.addBooks(userId, token, bookListForAdd);

        UserInfoDemoqaDto linkedBooksResponse =
                apiHelperDemoqa.getLinkedBook(userId, token);
        int passedCounter = 0;
        Assert.assertEquals("Count of linked books didn't match", linkedBooksResponse.getBooks().size(), bookListForAdd.size());
        for (int i = 0; i < linkedBooksResponse.getBooks().size(); i++) {
            for (int j = 0; j < bookListForAdd.size(); j++) {
                if (linkedBooksResponse.getBooks().get(i).getIsbn().equals(bookListForAdd.get(j))) {
                    passedCounter++;
                }
            }
        }
        Assert.assertEquals("Isbn of linked books have mismatch", linkedBooksResponse.getBooks().size(), passedCounter);

    }

    private List<String> getBooksForAdd(int count) {
        List<String> isbnList = apiHelperDemoqa.getBooksIsbn();
        List<String> bookList = new ArrayList<>();
        if (!(isbnList.isEmpty())) {
            if (count > isbnList.size()) {
                count = isbnList.size();
            }
            for (int i = 0; i < count; i++) {
                bookList.add(isbnList.get(i));
            }
        } else {
            return isbnList;
        }
        return bookList;
    }
}
