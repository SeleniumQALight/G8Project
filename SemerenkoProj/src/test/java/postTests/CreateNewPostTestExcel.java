package postTests;

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

import static data.TestData.*;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostTestExcel extends BaseTest {


    @Test
    @Parameters(method = "parametersForCreateNewPost")
    public void createNewPost(String postTitle, String postBody, String dropDownValue, String stateOfCheckBox, String massage, String stateOfUniquePost) {
        String postTitles = postTitle + Util.getDateAndTimeFormatted();
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(postTitles)
                .enterTextIntoInputBody(postBody)
                .setStateOfCheckBox(stateOfCheckBox)
                .selectValueInDropDown(dropDownValue)
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(massage)
                .checkIsTitleVisible(postTitles)
                .checkIsBodyVisible(postBody)
                .checkNoteCreatePostMessage(dropDownValue)
                .checkStateOfUniquePost(stateOfUniquePost)
                .getHeader().clickOnButtonProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(postTitles);
        deletePost(postTitles);
    }


    public Collection parametersForCreateNewPost() throws IOException {
        final String pathToFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToFile), sheetName, skipFirstRow).getData();
    }

    public void deletePost(String postTitle) {
        pageProvider.homePage().openHomePageLoginIfNeeded()
                .getHeader().clickOnButtonProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(postTitle);
    }
}
