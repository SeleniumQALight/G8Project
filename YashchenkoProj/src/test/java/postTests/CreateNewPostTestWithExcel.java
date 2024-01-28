package postTests;

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
    final String POST_TITLE_TEXT = "TC_001-1_Yashchenko";
    final String POST_TITLE_TIME = Util.getDateAndTimeFormatted();
    final String POST_BODY = "New Post Body Yashchenko" + Util.getDateAndTimeFormatted();

    @Test
    @Parameters(method = "parametersForCreateNewPostTest")
    public void createNewPostWithExcelParams
            (String title, String body, String privateOrNot,  String checkValue, String message, String yesOrNo){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .checkIsRedirectedToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectedToCreatePostPage()
                .enterTextInToInputTitle(String.format(title, POST_TITLE_TEXT, POST_TITLE_TIME))
                .enterTextInToInputBody(String.format(body, POST_BODY))
                .selectValueInDropDown(privateOrNot)
                .selectIsUniqueCheckboxUsingStringValue(checkValue)
                .clickOnSaveNewPostButton()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(message)
                .checkIsThisPostUniqueValuePresent(yesOrNo)
                .checkCreatedPostTitle(String.format(title, POST_TITLE_TEXT, POST_TITLE_TIME))
                .checkCreatedPostBody(String.format(body, POST_BODY))
                .checkIsNoteAndNoteValuePresent()
                .checkValueOfNote(privateOrNot)
        ;

        pageProvider.getPostPage().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .checkPostWithTitleIsPresent(String.format(title, POST_TITLE_TEXT, POST_TITLE_TIME))
        ;
    }

    public Collection<Object[]> parametersForCreateNewPostTest() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile + ",\n Sheet name: " + sheetName + ", \nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}
