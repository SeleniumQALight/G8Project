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
                                       .title(booksResponse.jsonPath().getString("books[0].title"))
                                       .subTitle(booksResponse.jsonPath().getString("books[0].subTitle"))
                                       .author(booksResponse.jsonPath().getString("books[0].author"))
                                        .publish_date(booksResponse.jsonPath().getString("books[0].publish_date"))
                                        .publisher(booksResponse.jsonPath().getString("books[0].publisher"))
                                        .pages(String.valueOf(booksResponse.jsonPath().getInt("books[0].pages")))
                                        .description(booksResponse.jsonPath().getString("books[0].description"))
                                        .website(booksResponse.jsonPath().getString("books[0].website"))
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