package postsTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostWithExcel  extends BaseTest {
    String TIME_STAMP = Util.getDateAndTimeFormatted();
    String POST_TITLE;
    String POST_BODY;

    @Test
    @Parameters(method = "parametersForCreateNewPostWithExcelTest")
    public void TC_002_createNewPostWithExcel(String postTitle, String postBody, String dropDownValue, String checkBoxValue, String successMessage, String uniquePostInfoMessage) {
        String METHOD_NAME = Util.getMethodName();
        POST_TITLE = String.format(postTitle, METHOD_NAME, TIME_STAMP);
        POST_BODY = String.format(postBody, TIME_STAMP);

        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .setIsThisPostUniqueCheckboxState(checkBoxValue)
                .selectValueInDropDown(dropDownValue)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(successMessage)
                .checkPostWithTitleIsPresent(POST_TITLE)
                .checkPostWithContentIsPresent(POST_BODY)
                .checkIsThisPostUniqueTextPresent(uniquePostInfoMessage)
                .checkNoteTextPresent(dropDownValue)
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .chckIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE);
    }

    public Collection parametersForCreateNewPostWithExcelTest() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
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
        ;
    }

}
