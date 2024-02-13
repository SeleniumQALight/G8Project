package postTests;

import baseTest.BaseTest;
import data.TestData;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.DB_Util_seleniumUsers;
import libs.ExcelSpreadsheetData;
import libs.Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import static data.TestData.NEW_LOGIN_UI;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostAndModifyTest extends BaseTest {
    private DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
    private String postTitles;

    //@Before
    @Test
    @Parameters(method = "parametersForCreateNewPost")
    public void createNewPost(String postTitle, String postBody, String dropDownValue, String stateOfCheckBox, String massage, String stateOfUniquePost) throws SQLException, ClassNotFoundException {
        postTitles = postTitle + Util.getDateAndTimeFormatted();
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred(NEW_LOGIN_UI, dbUtilSeleniumUsers.getPassForLogin(NEW_LOGIN_UI))
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
                .getHeader().clickOnButtonProfile(NEW_LOGIN_UI)
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(postTitles);
    }


    @Test
    @Parameters(method = "parametersForSearchAndModifyPost")
    public void SearchAndModifyPost(String postTitle, String postBody, String dropDownValue, String stateOfCheckBox, String massage, String stateOfUniquePost) throws SQLException, ClassNotFoundException {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred(NEW_LOGIN_UI, dbUtilSeleniumUsers.getPassForLogin(NEW_LOGIN_UI))
                .checkIsRedirectToHomePage()
                .getHeader()
                .clickOnButtonProfile()
                .checkPostWithTitleIsPresent(postTitles)
                .clickOnPostWithTitle(postTitles)
                .clickOnEditButton()
                .enterTextIntoInputTitle(postTitles + dbUtilSeleniumUsers.getAliasForLogin(NEW_LOGIN_UI))
                .clickOnSaveUpdates()
                .checkIsSuccessPostUpdateMessageVisible();

    }


    public Collection parametersForCreateNewPost() throws IOException {
        final String pathToFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostAndModify";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToFile), sheetName, skipFirstRow).getData();
    }

    public Collection parametersForSearchAndModifyPost() throws IOException {
        final String pathToFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostAndModify";
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
