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
public class CreateNewPostTestWithExcel extends BaseTest {
    String POST_TITLE;
    String POST_BODY;
    final String TIME = Util.getDateAndTimeFormatted();

    @Test
    @Parameters(method = "parametersForCreateNewPostWithExcel")
    public void createNewPostWithExcel(String title, String body, String dropDownValue, String checkBoxValue, String successMessage, String uniquePostMessage) {
        POST_TITLE = String.format(title, "Tepliuk", TIME);
        POST_BODY = String.format(body, "Tepliuk");

        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .setCheckBoxUniquePostChosen(checkBoxValue)
                .selectValueInDropDown(dropDownValue)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(successMessage)
                .checkTextInTitleAfterCreatePost(POST_TITLE)
                .checkTextInBodyAfterCreatePost(POST_BODY)
                .checkPostUniqueStateAfterCreatePost(uniquePostMessage);


        pageProvider.getpostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)
        ;
    }

    public Collection parametersForCreateNewPostWithExcel() throws IOException {
        final String pathToExcelFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToExcelFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToExcelFile), sheetName, skipFirstRow).getData();
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(POST_TITLE)
        ;
    }
}
