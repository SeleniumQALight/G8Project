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
public class CreateNewPostTestWithExcelData extends BaseTest {

    String POST_TITLE;
    String POST_BODY;

    @Test
    @Parameters(method = "valuesForCreateNewPostWithExcelData")
    public void createNewPostWithExcelData(String title, String body, String checkboxValue, String dropdownValue, String CheckBoxIsThisPostUniqueOnPostPage, String uniquePostMessage) {
        POST_TITLE = String.format(title + " Myhashko " + Util.getDateAndTimeFormatted());
        POST_BODY = String.format(body + " Myhashko");

        pageProvider.loginPage().openLoginPageAndFillLoginFormWhithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleField(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .selectValueInDropDown(dropdownValue)
                .setStatusOfCheckBoxIsThisPostUnique(checkboxValue)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTitleOnPostPageEqualsTileOnCreatePostPage()
                .checkBodyOnPostPageEqualsTileOnCreatePostPage()
                .chekStatusOfCheckBoxIsThisPostUniqueOnPostPage(CheckBoxIsThisPostUniqueOnPostPage)
                .checkOfNoteTextValue(dropdownValue)
                  ;
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }

    public Collection valuesForCreateNewPostWithExcelData() throws IOException {
        final String pathToExcelFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "Sheet1";
        final boolean skipFirstRow = true;
        logger.info("Data file path: " + pathToExcelFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToExcelFile), sheetName, skipFirstRow).getData();
    }


}

