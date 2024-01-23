package postsTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import libs.Util;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostWithExcelDataTest extends BaseTest {

    @Test
    @Parameters(method = "parametersForCreatePostTests")
    public void TC_001_createNewPostWithDataFromExcel(String postTitle,
                                                      String postBody,
                                                      String dropdownValue,
                                                      String checkBoxValue,
                                                      String expectedMessage,
                                                      String checkBoxState) {
        String POST_TITLE = postTitle.formatted("TC_001_Kamaieva", Util.getDateAndTimeFormatted());
        String POST_BODY = postBody.formatted("Body author Kamaieva" + Util.getDateAndTimeFormatted());
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCreate()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .selectValueInDropdown(dropdownValue)
                .checkboxUniquePost(checkBoxValue)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(expectedMessage)
                .checkPostTitleIsPresentOnPostPage(POST_TITLE)
                .checkPostBodyIsPresentOnPostPage(POST_BODY)
                .checkIsPostNoteStateConfirmExpectedValue(dropdownValue)
                .checkIsPostUniqueStateConfirmExpectedValue(checkBoxState);
    }

    public Collection parametersForCreatePostTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstLine = false; // skip first line in Excel file
        logger.info("Data file: " + pathToDataFile + ", Sheet name: " + sheetName + ", Skip first row: " + skipFirstLine);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstLine).getData();
    }
}
