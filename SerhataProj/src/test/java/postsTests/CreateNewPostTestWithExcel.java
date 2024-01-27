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
public class CreateNewPostTestWithExcel extends BaseTest {
    @Test
    @Parameters(method = "parametersForCreatePostTests")
    public void TC_001_createNewPost(String title, String body, String valueInDropDown, String state, String expectedMessages, String uniqueState) {
        String POST_TITLE = title.formatted("TC_with_excel_Serhata", Util.getDateAndTimeFormatted());
        String POST_BODY = body.formatted("Body_excel_Serhata" + Util.getDateAndTimeFormatted());
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .selectValueInDropDown(valueInDropDown)
                .selectCheckboxUniqueState(state)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkSuccessCreatePostMessages(expectedMessages)
                .checkPostTitleText(POST_TITLE)
                .checkPostBodyText(POST_BODY)
                .checkIsNoteStateExpected(valueInDropDown)
                .checkPostUniqueState(uniqueState);
    }

    public Collection parametersForCreatePostTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false; // if true - skip first row in excel file
        logger.info("data file path:" + pathToDataFile + "\n Sheet name:" + sheetName + "\n Skip first row:" + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}
