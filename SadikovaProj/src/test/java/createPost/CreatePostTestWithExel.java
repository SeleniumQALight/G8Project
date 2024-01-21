package createPost;

import baseTest.BaseTest;
import data.TestData;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CreatePostTestWithExel extends BaseTest {


    @Test
    @Parameters(method = "parametersForCreatePost")
    public void createPost() {
        pageProvider.mainPage()
                .loginToProfile(TestData.VALID_LOGIN_UI, TestData.VALID_PASSWORD);
        pageProvider.homePage().checkIsRedirectToHomePage();
        pageProvider.headerElement().clickCreatePostButton();
        pageProvider.createPostPage().checkIsRedirectToCreatePostPage();
        pageProvider.createPostPage().enterTextInTitleInInputTitle(postTitle);
        pageProvider.createPostPage().enterTextInBodyContentField(TEXT_BODY);
        pageProvider.createPostPage().selectValueInDropdown(ONE_PERSON);
        pageProvider.createPostPage().checkSetStateCheckbox("check");
        pageProvider.createPostPage().clickSaveNewPostButton();
        pageProvider.postPage().checkIsSuccessMessageDisplayes();
        pageProvider.postPage().checkTextPresent("New post successfully created.")
                .checkTitleTextIsEquals(postTitle)
                .checkBodyTextIsEquals(TEXT_BODY)
                .checkThisIsPostUniqueWebElementIsPresent("yes")
                .checkNoteBlockWebElementIsPresent(ONE_PERSON);


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
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = true; //if true - skip first row inn excel file
        logger.info("Data file patch" + pathToDataFile + "\nSheet name: " + sheetName + "\n Skip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();

    }


}
