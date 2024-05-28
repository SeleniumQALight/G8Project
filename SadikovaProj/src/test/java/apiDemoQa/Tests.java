package apiDemoQa;

import apiDemoqa.dto.ApiDemoQaHelp;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;


public class Tests {

    ApiDemoQaHelp apiDemoQaHelp = new ApiDemoQaHelp();
    String token;
    String userId;
    String userName;
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void getTokenAndDeletePosts() {
        token = apiDemoQaHelp.getToken();
        userId = apiDemoQaHelp.getUserId();
        userName = apiDemoQaHelp.getUserName();
        apiDemoQaHelp.deleteAllBooks(token, userId);

    }

    @Test
    public void addNewBook() {
        logger.info("З респонса берете першу книжку (її isbn)");
        String isbn = apiDemoQaHelp.getIsbn();
        logger.info("Додаете книжку юзеру АПІшкою POST");
        apiDemoQaHelp.addBook(token, userId, isbn);
        logger.info(" Перевіряєте що тепер у юзера є одна книжка і її номер той, що вказували при додаванні.");
        Response response = apiDemoQaHelp.getAddedUserPost(userId, token);
        apiDemoQaHelp.verifyIsbnInResponse(response, isbn);



    }
}
