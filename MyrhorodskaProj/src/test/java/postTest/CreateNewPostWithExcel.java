package postTest;

import BaseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostWithExcel extends BaseTest {

    String POST_TITLE;
    String POST_BODY;
    final String TIME_STAMP = Util.getDateAndTimeFormatted();
    final String DROPDOWN_VALUE = "One Person";

    @Test
    @Parameters(method = "parametersForCreateNewPostWithExcelTest")
    public void TC_002_createNewPostWithExcelTest(String postTitle, String postBody, String dropDownValue, String checkBoxValue, String successMessage, String uniquePostInfoMessage) {
        POST_TITLE = String.format(postTitle, "TC_002_Myrhorodska", TIME_STAMP);
        POST_BODY = String.format(postBody, "TC_002");
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsredirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
                .enterTextInToInputTitle(POST_TITLE)
                .enterTextInToInputBody("Myrhorodska Body")
                .selectValueInDropDown("One Person")
                .setIsThisPostUniqueCheckBoxState("check")
                .clickOnSaveNewButton()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .chekStatusOfCheckBoxIsThisPostUniqueOnPostPage("yes")
                .checkTitleOnPostPageEqualsTileOnCreatePostPage()
                .checkBodyOnPostPageEqualsTileOnCreatePostPage()
                .checkPostWithTitleIsPresent(POST_TITLE)
                .checkPostWithContentIsPresent(POST_BODY)
                .checkIsThisPostUniqueTextPresent("yes")
                .checkOfNoteTextValue("This post was written for " + DROPDOWN_VALUE)
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)
        ;
    }
    @After
    public void deletePost() {
        pageProvider.homePage().openHomePageAndLoginIfNeed()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }

    public Collection parametersForCreatePost() throws IOException, FileNotFoundException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls" ;
        final String sheetName = "myrhorodska";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile + "Sheet name: " + sheetName + "Skip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();

    }


}