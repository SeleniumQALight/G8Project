package apiTests;


import apiBooksStore.ApiHelperBooks;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;

import java.util.List;

import static data.TestData.VALID_LOGIN_API_BOOK;
import static data.TestData.VALID_PASSWORD_API_BOOK;

public class ApiTestBookStore {

    String token;
    String userId;
    ApiHelperBooks apiHelper = new ApiHelperBooks();
    Logger logger = Logger.getLogger(getClass());


    @Before
    public void deleteAllBooks() {
        token = apiHelper.getToken(VALID_LOGIN_API_BOOK, VALID_PASSWORD_API_BOOK);
        userId = apiHelper.getUserId(VALID_LOGIN_API_BOOK, VALID_PASSWORD_API_BOOK);

        apiHelper. deleteBooksBeforeCurrentDate(token, userId);

    }

    @Test
    public void addBookToUserTest() {

        Response listOfBooksFromBookstore = apiHelper.getAllBooksOfUser(token, userId); // get the full list of books using
        logger.info("List of books)");

        List<String> isbnList = listOfBooksFromBookstore.jsonPath().getList("books.isbn");
        logger.info("ISBN List of books: " + isbnList);

        String isbn = isbnList.get(0); // get isnb of the first book
        logger.info("ISBN of the first book: " + isbn);

        apiHelper.addBookToUser(userId, token, isbn);
        logger.info(String.format("Book with isbn='%s' was added", isbn));

        Response actualListOfBooksOFUser = apiHelper.getAllBooksOfUser(token, userId);
        List<String> updatedIsbnList = actualListOfBooksOFUser.jsonPath().getList("books.isbn");
        logger.info("Actual List of books after adding: " + updatedIsbnList);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(updatedIsbnList.size()).isEqualTo(1);
        logger.info("1 book was added to the list of books");
        softAssertions.assertThat(updatedIsbnList.get(0)).isEqualTo(isbn);
        logger.info("ISBN of the added book matches the one we added");
        softAssertions.assertAll();
    }
    }




