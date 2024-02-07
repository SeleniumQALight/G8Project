package postsTest;

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
public class CreateNewPostWithExcelFileTest extends BaseTest {
    final String TIME_STAMP = Util.getDateAndTimeFormatted();

    String POST_TITLE, POST_BODY;

    @Test
    @Parameters(method = "parametersForCreateNewPostWithExcelTest")
    public void createNewPost(String postTitle, String postBody, String postOptionForDropdown, String checkBoxValue, String successMessage, String uniquePostInfoMessage){
        POST_TITLE = String.format(postTitle, "Sushko", TIME_STAMP);
        POST_BODY = String.format(postBody, TIME_STAMP);

        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .setUniquePostCheckboxSelected(checkBoxValue)
                .selectValueInDropDown(postOptionForDropdown)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(successMessage)
                .checkTextThisPostUnique(uniquePostInfoMessage)
                .checkTitleIsPresentInPost(POST_TITLE)
                .checkBodyContentIsPresentInPost(POST_BODY)
                .checkCorrectNoteInPost(postOptionForDropdown)
        ;
    }

    @After
    public void deletePost(){
        logger.info("post title = " + POST_TITLE);
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)
        ;
    }

    public Collection parametersForCreateNewPostWithExcelTest() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}
