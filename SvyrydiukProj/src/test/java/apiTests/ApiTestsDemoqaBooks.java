package apiTests;

import apiBooks.ApiHelperBooks;
import apiBooks.dto.responsetDTO.BooksDTO;
import apiBooks.dto.responsetDTO.UsersBooksDTO;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static data.TestData.VALID_LOGIN_API_BOOKS;
import static data.TestData.VALID_PASSWORD_API_BOOKS;

public class ApiTestsDemoqaBooks {


    String token;
    String userId;
    ApiHelperBooks apiHelper = new ApiHelperBooks();

    @Before
    public void deleteBooks() {
        token = apiHelper.getToken(VALID_LOGIN_API_BOOKS, VALID_PASSWORD_API_BOOKS);
        userId = apiHelper.getUserId(VALID_LOGIN_API_BOOKS, VALID_PASSWORD_API_BOOKS);


    }

    @Test
    public void AddBookForUserTest() {
        apiHelper.deleteAllBooksTillPresent(userId, token);


        Response booksResponse = apiHelper.getFullListOfBooks().extract().response();

        String isbn = booksResponse.jsonPath().getString("books[0].isbn");

        apiHelper.addBookForUser(userId, token, isbn);
        UsersBooksDTO actualBooksResponse = apiHelper.getAllBooksByUserAsDTO(userId, token);


        Response booksofUser = apiHelper.getAllBooksByUser(userId, token);

        List<String> actualListOfBooksOfUser = booksofUser.jsonPath().getList("books.isbn", String.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualListOfBooksOfUser.size()).isEqualTo(1);
        for (int i = 0; i < actualListOfBooksOfUser.size(); i++) {

            softAssertions.assertThat(actualListOfBooksOfUser.get(i)).isEqualTo(isbn);
            softAssertions.assertAll();
        }


        UsersBooksDTO expectedUserBooksDto = UsersBooksDTO.builder()
                .userId(userId)
                .username(VALID_LOGIN_API_BOOKS)
                .books(new ArrayList<BooksDTO>() {
                           {
                               add(BooksDTO.builder()
                                       .isbn(isbn)
                                       .title(booksResponse.jsonPath().getString("books[0].title"))
                                       .subTitle(booksResponse.jsonPath().getString("books[0].subTitle"))
                                       .author(booksResponse.jsonPath().getString("books[0].author"))
                                       .publish_date(booksResponse.jsonPath().getString("books[0].publish_date"))
                                       .publisher(booksResponse.jsonPath().getString("books[0].publisher"))
                                       .pages(booksResponse.jsonPath().getString("books[0].pages"))
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


    @Test
    public void AddBookForUserThatNotExistTest() {

        Response allBooksResponse = apiHelper.getFullListOfBooks().extract().response();

        List<String> isbnBooks = allBooksResponse.jsonPath().getList("books.isbn");

        Response actualUserBooks = apiHelper.getAllBooksByUser(userId, token);
        List<String> actualListOfBooksOfUser = actualUserBooks.jsonPath().getList("books.isbn");

        List<String> isbnBooksThatNotExist = new ArrayList<>();
        for (int i = 0; i < isbnBooks.size(); i++) {
            if (!actualListOfBooksOfUser.contains(isbnBooks.get(i))) {
                isbnBooksThatNotExist.add(isbnBooks.get(i));
            }
        }
        if (isbnBooksThatNotExist.isEmpty()) {
            apiHelper.logger.info("All books are already added");
        } else {
            apiHelper.addBookForUser(userId, token, isbnBooksThatNotExist.get(0));
        }


        Response booksofUser = apiHelper.getAllBooksByUser(userId, token);

        List<String> actualListOfBooksOfUserAfterAdd = booksofUser.jsonPath().getList("books.isbn", String.class);

        SoftAssertions softAssertions = new SoftAssertions();
        if (isbnBooks.size() == actualListOfBooksOfUser.size()) {
            softAssertions.assertThat(actualListOfBooksOfUserAfterAdd.size()).isEqualTo(actualListOfBooksOfUser.size());
        } else {
            softAssertions.assertThat(actualListOfBooksOfUserAfterAdd.size()).isEqualTo(actualListOfBooksOfUser.size() + 1);
        }
        for (int i = 0; i < actualListOfBooksOfUser.size(); i++) {
            softAssertions.assertThat(actualListOfBooksOfUserAfterAdd.get(i)).isIn(actualListOfBooksOfUser);
            softAssertions.assertAll();
        }
    }

}
