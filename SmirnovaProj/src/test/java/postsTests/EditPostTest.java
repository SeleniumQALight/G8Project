package postsTests;

import baseTest.BaseTest;
import libs.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


public class EditPostTest extends BaseTest {
    private Database database;
    Logger logger = Logger.getLogger(getClass());
    final String LOGIN = "newqaauto";
    String POST_TITLE, NEW_POST_TITLE;
    DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();


    @Before
    public void createPost() throws SQLException, ClassNotFoundException, IOException {
        database = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        Map<String, String> dataForPostFromExcel = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE_PATH() + "/testData-updated.xls", "newPost");
        POST_TITLE = String.format(dataForPostFromExcel.get("title"),"Smirnova");

        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().fillLoginFormAndSubmit(LOGIN,
                        database.selectValue("select password from seleniumUsers where login = 'newqaauto'"))
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleIntoInput(POST_TITLE)
                .enterTextIntoInputBody(String.format(dataForPostFromExcel.get("body"),"Smirnova"))
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;
    }

    @Test
    public void editPost() throws SQLException, ClassNotFoundException {
        NEW_POST_TITLE = POST_TITLE + dbUtilSeleniumUsers.getAliasForLogin(LOGIN);

        pageProvider.getMyProfilePage()
                .clickOnPostWithTitle(POST_TITLE)
                .checkIsRedirectToPostPage()
                .clickOnButtonEditPost();
        pageProvider.getCreatePostPage()
                .enterTitleIntoInput(NEW_POST_TITLE)
                .clickOnSaveUpdatesButton()
                .checkTextInSuccessMessage("Post successfully updated.")
        ;
        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(NEW_POST_TITLE)
        ;
    }

    @After
    public void deletePost() throws SQLException {
        pageProvider.homePage()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWhilePresent(NEW_POST_TITLE)
                .deletePostWhilePresent(POST_TITLE)
        ;
        database.quit();
        logger.info("--- Disconnected from DB -------");
    }
}

