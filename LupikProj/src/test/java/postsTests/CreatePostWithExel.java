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
public class CreatePostWithExel extends BaseTest {

    @Test
    @Parameters(method = "parameterForCreateNewPost")
    public void createPostWithExel(String title, String body, String visibility, String uniquePost,  String message, String expectedResult) {
       String fullTitle = String.format(title, "Lupik Title", Util.getDateAndTimeFormatted());
       String fullBody = String.format(body, "Lupik" + Util.getDateAndTimeFormatted());
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInToInputTitle(fullTitle)
                .enterTextInToInputBody(fullBody)
                .setUniquePostCheckboxSelected(uniquePost)
                .selectValueInDropDown(visibility)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(message)
                .checkTitleTextInCreatedPost(fullTitle)
                .checktextOfVisibilityOfMessage(visibility)
                .checkTextThisPostUnique(expectedResult)
                .checkBodyTextInCreatedPost(fullBody)


        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(fullTitle);

        logger.info("Delete post with title: " + fullTitle +" started");
        pageProvider.homePage().openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(fullTitle)
        //я вставив сюди цей метод, щоб видалити пост після створення,
        // так як "After" не запускається, пише
        //що java.lang.IllegalArgumentException: array is not of length 6
        // може це якась особливість роботи  @Parameters чи  @RunWith?

        ;

    }


/*@After
   // @Parameters(method = "parameterForCreateNewPost")
    public void deletePost(String title, String body, String visibility, String uniquePost,  String message, String expectedResult) {
        String fullTitle = String.format(title, "Lupik","Title");
        logger.info("Delete post with title: " + fullTitle +"started");
        pageProvider.homePage().openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(fullTitle)

        ;
    }*/

    public Collection parameterForCreateNewPost() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH()+  "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false; //if true - first row will be skipped in excel file
        logger.info("Path to data file: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}