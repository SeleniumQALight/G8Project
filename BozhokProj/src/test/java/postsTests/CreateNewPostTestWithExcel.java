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

    @Test
    @Parameters(method = "parametersForCreatePostTests")
    public void TC_001_createNewPostWithExcelTest(String postTitle, String postBody, String dropdownValue, String checkboxValue, String expectedMessage, String expectedStatus) {

         POST_TITLE = String.format(postTitle, "Bozhok ", Util.getDateAndTimeFormatted());
         POST_BODY = String.format(postBody, Util.getDateAndTimeFormatted());

        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextInToInputBody(POST_BODY)
                .selectValueInDropDown(dropdownValue)
                .checkBoxUniquePost(checkboxValue)
                .clickOnSaveNewButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMassageDisplayed()
                .checkTextInSuccessMessage(expectedMessage)
                .checkPostTitleIsPresentOnPostPage(POST_TITLE)
                .checkPostBodyIsPresentOnPostPage(POST_BODY)
                .checkDropdownValueIsPresentOnPostPage(dropdownValue)

//
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkPostWithTitlesIsPresent(POST_TITLE)
                .clickOnPostWithTitle(POST_TITLE)

        ;
    }

    public Collection parametersForCreatePostTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false;
        logger.info("Data file: " + pathToDataFile + ", Sheet name: " + sheetName + ", Skip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData( new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectOnMyProfilePage()
                .deletePostTillPresent(POST_TITLE)
        ;
    }

}