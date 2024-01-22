package postsTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import org.junit.After;
import org.junit.Test;
import libs.Util;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Svyrydiuk" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "New Post Body Svyrydiuk" + Util.getDateAndTimeFormatted();
    final String DROPDOWN_VALUE = "One Person"; // "Приватне повідомлення"


    @Test

    public void TC_001_createNewPostTest() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                //.selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown(DROPDOWN_VALUE)
                .setCheckbox("check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsCreatedPostHasTitle(POST_TITLE)
                .checkIsCreatedPostHasBody(POST_BODY)
                .checkIsCreatedPostHasValueInDropDown(DROPDOWN_VALUE)
                .checkCheckboxStatus("yes")
        ;
        pageProvider.getPostPage().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)  //checkIsPostWasAdded(POST_TITLE)
        ;
    }


    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void TC_002_createNewPostTestWithDataFromExcel(String POST_TITLE_XL, String POST_BODY_XL, String DROPDOWN_VALUE,
                                                          String CHECKBOX_VALUE, String expectedMessage, String checkboxOption) {
        String POST_TITLE = POST_TITLE_XL.formatted("TC_002_Svyrydiuk", Util.getDateAndTimeFormatted());
        String POST_BODY = POST_BODY_XL.formatted("New Post Body Svyrydiuk", Util.getDateAndTimeFormatted());
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY.formatted(Util.getDateAndTimeFormatted()))
                //.selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown(DROPDOWN_VALUE)
                .setCheckbox(CHECKBOX_VALUE)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(expectedMessage)
                .checkIsCreatedPostHasTitle(POST_TITLE)
                .checkIsCreatedPostHasBody(POST_BODY)
                .checkIsCreatedPostHasValueInDropDown(DROPDOWN_VALUE)
                .checkCheckboxStatus(checkboxOption)
        ;
        pageProvider.getPostPage().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)  //checkIsPostWasAdded(POST_TITLE)
        ;
    }

    public Collection parametersForValidationMessagesTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false; // skip first row in excel file
        logger.info("Path to file: " + pathToDataFile + " Sheet name: " + sheetName + " Skip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnButtonMyProfile()
                .deletePostTillPresent(POST_TITLE)
        ;
    }

}
