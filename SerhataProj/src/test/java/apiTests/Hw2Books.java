package apiTests;

import api.books.BooksApiHelper;
import api.dto.responseBookDto.AllBooksDTO;
import api.dto.responseBookDto.UserBooksDTO;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static data.TestData.VALID_LOGIN_API_BOOKS;
import static data.TestData.VALID_PASSWORD_API_BOOKS;

public class Hw2Books {

    String token;
    String userId;
    BooksApiHelper apiHelper = new BooksApiHelper();


    @Before
    public void deleteBooks() {
        token = apiHelper.getToken(VALID_LOGIN_API_BOOKS, VALID_PASSWORD_API_BOOKS);
        userId = apiHelper.getUserId(VALID_LOGIN_API_BOOKS, VALID_PASSWORD_API_BOOKS);
        apiHelper.deleteAllBooksTillPresent(userId, token);
    }

    @Test
    public void testAddNewBookForUserAndCheckThatItWasAdded() {
        Response booksResponse = apiHelper.getAllBooksInStore().extract().response();

        String isbn = booksResponse.jsonPath().getString("books[0].isbn");

        apiHelper.addBookForUser(userId, token, isbn);
        UserBooksDTO actualBooksResponse = apiHelper.getAllBooksByUserAsDTO(userId, token);


        Response booksOfUser = apiHelper.getAllBooksByUser(userId, token);

        List<String> actualListOfBooksOfUser = booksOfUser.jsonPath().getList("books.isbn", String.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualListOfBooksOfUser.size()).isEqualTo(1);
        for (int i = 0; i < actualListOfBooksOfUser.size(); i++) {

            softAssertions.assertThat(actualListOfBooksOfUser.get(i)).isEqualTo(isbn);
            softAssertions.assertAll();
        }

        UserBooksDTO expectedUserBooksDto = UserBooksDTO.builder()
                .userId(userId)
                .username(VALID_LOGIN_API_BOOKS)
                .books(new ArrayList<AllBooksDTO>() {
                           {
                               add(AllBooksDTO.builder()
                                       .isbn(isbn)
                                       .title("Git Pocket Guide")
                                       .subTitle("A Working Introduction")
                                       .author("Richard E. Silverman")
                                        .publish_date("2020-06-04T08:48:39.000Z")
                                        .publisher("O'Reilly Media")
                                        .pages("234")
                                        .description("This pocket guide is the perfect on-the-job companion to Git, "+
                                                "the distributed version control system. It provides a compact, readable introduction to Git for new users, " +
                                                "as well as a reference to common commands and procedures for those of you with Git exp")
                                        .website("http://chimera.labs.oreilly.com/books/1230000000561/index.html")
                                       .build());
                           }
                       }
                ).build();

        softAssertions.assertThat(actualBooksResponse)
                .usingRecursiveComparison()
                .ignoringFields("title", "subTitle", "author", "publish_date", "publisher", "pages", "description", "website")
                .isEqualTo(expectedUserBooksDto);
        softAssertions.assertAll();
    }

}