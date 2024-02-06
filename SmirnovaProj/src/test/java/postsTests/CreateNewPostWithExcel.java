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
    String postCreationTimestamp = Util.getDateAndTimeFormatted();

    @Test
    @Parameters(method = "parametersForCreatePostTests")
    public void createNewPostWithExcel(String title, String body, String option, String valueCheckbox, String expectedMessage, String postUniqueState) {

        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleIntoInput(String.format("%s-excel", postCreationTimestamp, title))
                .enterTextIntoInputBody(String.format("%s-excel", postCreationTimestamp, body))
                .setCheckBoxUniquePost(valueCheckbox)
                .selectValueInDropDown(option)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(expectedMessage)
        ;
        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(String.format("%s-excel", postCreationTimestamp, title))
        ;
        pageProvider.getMyProfilePage().clickOnPostWithTitle(String.format("%s-excel", postCreationTimestamp, title))
                .checkTextInPostTitle(String.format("%s-excel", postCreationTimestamp, title))
                .checkTextInPostBody(String.format("%s-excel", postCreationTimestamp, body))
                .checkTextInPostBodyNote("Note: This post was written for " + option)
                .checkPostUniqueState(postUniqueState)
        ;
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWhilePresent(String.format("%s-excel", postCreationTimestamp))
        ;
    }

    public Collection<Object[]> parametersForCreatePostTests() throws IOException {
        final String pathToFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "/testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstLine = false;//пропустить первую строку
        logger.info("Path to file: " + pathToFile +
                " Sheet name: " + sheetName + " Skip first line: " + skipFirstLine);
        return new ExcelSpreadsheetData(new FileInputStream(pathToFile), sheetName, skipFirstLine).getData();
    }


}
