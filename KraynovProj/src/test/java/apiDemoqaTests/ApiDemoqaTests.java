package apiDemoqaTests;

import apiDemoqa.ApiDemoqaHelper;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static data.TestData.VALID_LOGIN_DEMOQA_API;
import static data.TestData.VALID_PASSWORD_DEMOQA_API;

public class ApiDemoqaTests {

    String token;
    String userId;

    ApiDemoqaHelper apiHelper = new ApiDemoqaHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deleteBooks() {
        // Логин и получение токена и ID
        token = apiHelper.getToken(VALID_LOGIN_DEMOQA_API, VALID_PASSWORD_DEMOQA_API);
        logger.info("Получили токен пользователя: " + token);
        userId = apiHelper.getUserId(VALID_LOGIN_DEMOQA_API, VALID_PASSWORD_DEMOQA_API);
        logger.info("Получили ID пользователя: " + userId);

        //Удаление всех книг у пользователя
        apiHelper.deleteAllBooks(userId, token);
    }

    @Test
    public void AddBookForUserTest() {
        // Получаем список книг и сохраняем isbn первой
        Response listOfBooksFromBookstore = apiHelper.getListOfBooksFromBookstore();
        logger.info("Получен список всех книг в Bookstore");
        String isbn = listOfBooksFromBookstore.jsonPath().getString("books[0].isbn");
        logger.info("Сохранен isbn первой книги из Bookstore: " + isbn);

        // Добавляем книгу с сохраненным isbn пользователю
        apiHelper.addBookForUser(userId, token, isbn);
        logger.info(String.format("Книжка с isbn='%s' добавлена пользователю", isbn));

        // Получаем актуальный список книг пользователя
        Response actualBooksByUser = apiHelper.getAllBooksByUser(userId, token);
        List<String> actualListOfBooksOfUser = actualBooksByUser.jsonPath().getList("books.isbn", String.class);
        logger.info("Получен актуальный список книг пользователя");

        // Проверяем, что теперь у пользователя есть одна книжка и ее номер совпадает с той, которую добавляли.
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualListOfBooksOfUser.size()).isEqualTo(1);
        logger.info("У пользователя добавлена только 1 книжка");
        softAssertions.assertThat(actualListOfBooksOfUser.get(0)).isEqualTo(isbn);
        logger.info("isbn книги совпадает с той, которую добавляли");
        softAssertions.assertAll();
    }
}