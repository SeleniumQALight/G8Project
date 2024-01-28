package dataBaseTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.DB_Util_seleniumUsers;
import libs.ExcelSpreadsheetData;
import libs.Util;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class UpdatePostTest extends BaseTest {

    private Logger logger = Logger.getLogger(getClass());

    final String LOGIN_DB = "newqaauto";
    String TIME_STAMP = Util.getDateAndTimeFormatted();
    String POST_TITLE;
    String NEW_POST_TITLE;
    String POST_BODY;
    String SUCCESS_MESSAGE;
    String UNIQUE_POST_INFO_MESSAGE;
    String DROP_DOWN_VALUE;

    public void createNewPostWithExcel(String postTitle, String postBody, String dropDownValue, String checkBoxValue, String successMessage, String uniquePostInfoMessage) throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers db_util = new DB_Util_seleniumUsers();
        String PASSWORD =  db_util.getPassByLogin(LOGIN_DB);
        String METHOD_NAME = testName.getMethodName();
        POST_TITLE = String.format(postTitle, METHOD_NAME, TIME_STAMP);
        POST_BODY = String.format(postBody, TIME_STAMP);
        SUCCESS_MESSAGE = successMessage;
        UNIQUE_POST_INFO_MESSAGE = uniquePostInfoMessage;
        DROP_DOWN_VALUE = dropDownValue;

        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(LOGIN_DB);
        pageProvider.loginPage().enterTextIntoInputPassword(PASSWORD);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage().checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .setIsThisPostUniqueCheckboxState(checkBoxValue)
                .selectValueInDropDown(dropDownValue)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(SUCCESS_MESSAGE)
                .checkPostWithTitleIsPresent(POST_TITLE)
                .checkPostWithContentIsPresent(POST_BODY)
                .checkIsThisPostUniqueTextPresent(UNIQUE_POST_INFO_MESSAGE)
                .checkNoteTextPresent(DROP_DOWN_VALUE)
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .chckIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE);
    }

    @Test
    @Parameters(method = "parametersForCreateNewPostWithExcelTest")
    public void TC_001_updatePost(String postTitle, String postBody, String dropDownValue, String checkBoxValue, String successMessage, String uniquePostInfoMessage) throws SQLException, ClassNotFoundException {
        createNewPostWithExcel(postTitle, postBody, dropDownValue,  checkBoxValue,  successMessage,  uniquePostInfoMessage);
        DB_Util_seleniumUsers db_util = new DB_Util_seleniumUsers();
        NEW_POST_TITLE = db_util.getAliasByLogin(LOGIN_DB) + "-" + TIME_STAMP;
        System.out.println(NEW_POST_TITLE);
        pageProvider.getMyProfilePage()
                .clickOnPostWithTitle(POST_TITLE)
                .clickOnEditButton()
                .enterTitleInToInputTitle(NEW_POST_TITLE)
                .clickOnSaveUpdatesButton()
                .checkIsRedirectToEditPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("Post successfully updated.")
                .clickLinkBackToPostPermalink()
                .checkIsRedirectToPostPage()
                .checkPostWithTitleIsPresent(NEW_POST_TITLE)
                .checkPostWithContentIsPresent(POST_BODY)
                .checkIsThisPostUniqueTextPresent("no")
                .checkNoteTextPresent(DROP_DOWN_VALUE)
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .chckIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(NEW_POST_TITLE);
    }

    public Collection parametersForCreateNewPostWithExcelTest() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testData.xls";
        final String sheetName = "post_data";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .chckIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)
                .deletePostsTillPresent(NEW_POST_TITLE)
        ;
    }
}
