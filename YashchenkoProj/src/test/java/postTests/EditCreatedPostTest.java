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

    @Test
    public void editCreatedPost() throws SQLException, ClassNotFoundException, IOException {
        final String LOGIN = "newqaauto";

        DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
        Map<String, String> dataForCreatingPost = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "newPost");

        String postTitleWithExcel = String.format(dataForCreatingPost.get("title"), POST_TITLE);
        String postBodyWithExcel = String.format(dataForCreatingPost.get("body"), POST_BODY);
        String changedPostTitle = postTitleWithExcel + "-" + dbUtilSeleniumUsers.getAliasForLogin(LOGIN);

        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithCredsAndCreatePostWithParams
                        (LOGIN, dbUtilSeleniumUsers.getPassForLogin(LOGIN), postTitleWithExcel, postBodyWithExcel,
                                dataForCreatingPost.get("option"), dataForCreatingPost.get("checkBoxStatus"))
                .clickOnEitIcon()
                .checkIsRedirectedToEditPostPage()
                .enterTextInToInputTitle(changedPostTitle)
                .clickOnSaveUpdated()
                .getHeader().clickOnButtonMyProfile()
                .checkPostWithTitleIsPresent(changedPostTitle)
                .deletePostsTillPresent(postTitleWithExcel)
                .deletePostsTillPresent(changedPostTitle)

        ;

    }

    @After
    public void tearDown() throws SQLException{
        database.quit();
    }
}
