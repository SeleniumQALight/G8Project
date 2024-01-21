package createPost;

import baseTest.BaseTest;
import data.TestData;
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
public class CreatePostTestWithExel extends BaseTest {

    String POST_TITLE;
    String POST_BODY;
    String DATA = Util.getDateAndTimeFormattedOnlyNumbers();


    @Test
    @Parameters(method = "parametersForCreatePost")
    public void createPost(String postTitle, String textBody, String dropdownValue, String selectCheckbox, String notification, String webElementValue, String testNumber) {
        POST_TITLE = String.format(postTitle, DATA, testNumber);
        POST_BODY = String.format(textBody, DATA);
        pageProvider.mainPage()
                .loginToProfile(TestData.VALID_LOGIN_UI, TestData.VALID_PASSWORD);
        pageProvider.homePage().checkIsRedirectToHomePage();
        pageProvider.headerElement().clickCreatePostButton();
        pageProvider.createPostPage().checkIsRedirectToCreatePostPage();
        pageProvider.createPostPage().enterTextInTitleInInputTitle(POST_TITLE);
        pageProvider.createPostPage().enterTextInBodyContentField(POST_BODY);
        pageProvider.createPostPage().selectValueInDropdown(dropdownValue);
        pageProvider.createPostPage().checkSetStateCheckbox(selectCheckbox);
        pageProvider.createPostPage().clickSaveNewPostButton();
        pageProvider.postPage().checkIsSuccessMessageDisplayes();
        pageProvider.postPage().checkTextPresent(notification)
                .checkTitleTextIsEquals(POST_TITLE)
                .checkBodyTextIsEquals(POST_BODY)
                .checkThisIsPostUniqueWebElementIsPresent(webElementValue)
                .checkNoteBlockWebElementIsPresent(dropdownValue);


        pageProvider.postPage().getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1);
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getheaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)

        ;
    }

    public Collection parametersForCreatePost() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "sadikova";
        final boolean skipFirstRow = false; //if true - skip first row inn excel file
        logger.info("Data file patch" + pathToDataFile + "\nSheet name: " + sheetName + "\n Skip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();

    }


}
