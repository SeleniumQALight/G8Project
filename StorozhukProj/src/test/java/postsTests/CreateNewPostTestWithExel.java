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
public class CreateNewPostTestWithExel  extends BaseTest {

    String title;

    @Test
    @Parameters(method = "parametersForCreateNewPostWithExcelTest")

    public void TC_002_createNewPostWithExel(String postTitle, String postBody, String postVisibility, String uniqueCheckbox, String status, String uniqueMessage) {

        title = String.format(postTitle, "TC_002_Storozhuk", Util.getDateAndTimeFormatted());
        String body = String.format(postBody, "Storozhuk");

        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(title)
                .enterTextIntoInputBody(body)
                .tickCheckbox(uniqueCheckbox)
                .selectValueInDropDown(postVisibility)
                .clickOnSaveNewPostButton()
                //-----CHECKS
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(status)
                .checkTextInPostTitle(title)
                .checkTextInPostBody(body)
                .checkTextInUniquePostInfoMessageByText(uniqueMessage)
                .checkTextInPostNote(postVisibility);

    }
    public Collection parametersForCreateNewPostWithExcelTest() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false; // skip first row in file (header)
        logger.info("Data file path: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(title);
    }
}

