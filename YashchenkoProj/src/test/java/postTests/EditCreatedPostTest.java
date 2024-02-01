package postTests;

import baseTest.BaseTest;
import dataBaseTest.DataBaseTest;
import junitparams.JUnitParamsRunner;
import libs.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class EditCreatedPostTest extends BaseTest{
    private Database database;
    private Logger logger = Logger.getLogger(getClass());
    final String POST_TITLE = "TC_001-1_Yashchenko" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "New Post Body Yashchenko" + Util.getDateAndTimeFormatted();

    @Before
    public void setUp () throws SQLException, ClassNotFoundException {
        database = MySQL_Database.getDataBase();
    }

    @After
    public void tearDown() throws SQLException{
        database.quit();
    }

    @Test
    public void editCreatedPost() throws SQLException, ClassNotFoundException, IOException {
        final String LOGIN = "newqaauto";
//        ArrayList<Map<String, String>> allDataFromSeleniumUsers =
//                database.selectTableAsMap("SELECT * FROM seleniumUsers");
//        logger.info(allDataFromSeleniumUsers);
        DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
        Map<String, String> dataForCreatingPost = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "newPost");

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(dbUtilSeleniumUsers.getPassForLogin(LOGIN));
        pageProvider.getLoginPage().clickOnButtonSignIn()
                .checkIsRedirectedToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectedToCreatePostPage()
                .enterTextInToInputTitle(String.format(dataForCreatingPost.get("title"), POST_TITLE))
                .enterTextInToInputBody(String.format(dataForCreatingPost.get("body"), POST_BODY))
                .selectValueInDropDown(dataForCreatingPost.get("option"))
                .selectIsUniqueCheckboxUsingStringValue(dataForCreatingPost.get("checkBoxStatus"))
                .clickOnSaveNewPostButton()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed();
        String createdPostTitle = pageProvider.getPostPage().getPostName();

    }

}
