package postsTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.DB_Util_seleniumUsers;
import libs.ExcelSpreadsheetData;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;


@RunWith(JUnitParamsRunner.class)
public class UpdatePostTest extends BaseTest {
    final String LOGIN_DB = "newqaauto";

    @Rule

    public void createNewPost(String POST_TITLE, String POST_BODY, String DROPDOWN_VALUE,
                              String CHECKBOX_VALUE, String expectedMessage, String checkboxOption) throws SQLException, ClassNotFoundException {
        pageProvider.loginPage().openLoginPage();
        DB_Util_seleniumUsers db_util = new DB_Util_seleniumUsers();
        pageProvider.loginPage().enterTextIntoInputLogin(LOGIN_DB);
        pageProvider.loginPage().enterTextIntoInputPassword(db_util.getPassForLogin(LOGIN_DB));
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY.formatted(Util.getDateAndTimeFormatted()))
                .selectValueInDropDown(DROPDOWN_VALUE)
                .setCheckbox(CHECKBOX_VALUE)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(expectedMessage)
                .checkIsCreatedPostHasTitle(POST_TITLE)
                .checkIsCreatedPostHasBody(POST_BODY)
                .checkIsCreatedPostHasValueInDropDown(DROPDOWN_VALUE)
                .checkCheckboxStatus(checkboxOption)
        ;
    }

    @Rule
    public void deleteCreatedPost(String POST_TITLE, String NEW_POST_TITLE) {
        pageProvider.homePage()
                .getHeader().clickOnButtonMyProfile()
                .deletePostTillPresent(NEW_POST_TITLE);
        pageProvider.homePage().
                getHeader().clickOnButtonMyProfile()
                .deletePostTillPresent(POST_TITLE)
        ;
    }

    @Test
    //precondition - post should be created
    @Parameters(method = "parametersForNewPost")
    public void TC_001_updatePost(String POST_TITLE_XL, String POST_BODY, String DROPDOWN_VALUE,
                                  String CHECKBOX_VALUE, String expectedMessage, String checkboxOption) throws SQLException, ClassNotFoundException {
        String POST_TITLE = POST_TITLE_XL + "_" + Util.getDateAndTimeFormatted();

        DB_Util_seleniumUsers db_util = new DB_Util_seleniumUsers();
        String NEW_POST_TITLE = POST_TITLE + db_util.getAliasForPost(LOGIN_DB);

        createNewPost(POST_TITLE, POST_BODY, DROPDOWN_VALUE, CHECKBOX_VALUE, expectedMessage, checkboxOption);

        pageProvider.getPostPage().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE).clickOnPostWithTitle(POST_TITLE);

        pageProvider.getPostPage().clickOnEditPostButton();
        pageProvider.getCreatePostPage().enterTextIntoInputTitle(NEW_POST_TITLE);
        pageProvider.getCreatePostPage().clickOnSaveUpdatesButton();
        pageProvider.getPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("Post successfully updated.");

        pageProvider.getPostPage().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(NEW_POST_TITLE)  //checkIsPostWasAdded(POST_TITLE)
        ;
        deleteCreatedPost(POST_TITLE, NEW_POST_TITLE);
    }


    public Collection parametersForNewPost() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testData.xls";
        final String sheetName = "post_data";
        final boolean skipFirstRow = false; // skip first row in excel file
        logger.info("Path to file: " + pathToDataFile + " Sheet name: " + sheetName + " Skip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }


}
