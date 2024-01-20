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

public class CreateNewPostWithExcel extends BaseTest {
    final String TIME_STAMP = Util.getDateAndTimeFormatted();
    String POST_TITLE;
    String POST_BODY;
    @Test
    @Parameters(method = "parametersForCreateNewPostWithExcelTest")
    public void TC_002_createNewPostWithExcelTest(String postTitle, String postBody, String dropDownValue, String checkBoxValue, String successMessage, String uniquePostInfoMessage) {
        POST_TITLE = String.format(postTitle, "TC_002_Petriv", TIME_STAMP);
        POST_BODY = String.format(postBody, "TC_002");
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .selectValueInDropDown(dropDownValue);
        pageProvider.getCreatePostPage()
                .setCheckBoxUniquePost(checkBoxValue)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(successMessage)
                .checkTextInPostTitle(POST_TITLE)
                .checkTextInPostBody(POST_BODY)
                .checkTextInUniquePostInfoMessage(uniquePostInfoMessage)
                .checkTextInPostNote("Note: This post was written for " + dropDownValue)
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)
        ;
    }

    public Collection parametersForCreateNewPostWithExcelTest() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

    @After
    public void deletePosts() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)
        ;
    }

}
