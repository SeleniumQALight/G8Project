package postTest;

import BaseTest.BaseTest;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.DB_Util_seleniumUser;
import libs.ExcelDriver;
import libs.Util;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class EditPostTitleTest extends BaseTest {

        final String LOGIN = "newqaauto";
        final String TIME_STAMP = Util.getDateAndTimeFormatted();
        final String POST_CREATED_SUCCESS_MESSAGE = "New post successfully created.";
        String postTitle;
        String updatedPostTitle;

        public void createNewPostWithExcelData (String title, String body, String valueInDropDown, String state) {
            pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                    .checkIsredirectToHomePage()
                    .getHeader().clickOnButtonCreatePost()
                    .checkIsRedirectOnCreatePostPage()
                    .enterTextInToInputTitle(title)
                    .enterTextInToInputBody(body)
                    .selectValueInDropDown(valueInDropDown)
                    .setIsThisPostUniqueCheckBoxState(state)
                    .clickOnSaveNewButton()
                    .checkIsRedirectedToPostPage()
                    .checkIsSuccessMessageDisplayed()
                    .checkTextInSuccessMessage(POST_CREATED_SUCCESS_MESSAGE)
                    .checkPostWithTitleIsPresent(title)
                    .checkPostWithContentIsPresent(body)
                    .checkIsThisPostUniqueTextPresent("yes")
                    .checkOfNoteTextValue(valueInDropDown);
        }
    @Test
    @Parameters(method = "parametersForCreatePostTestFromExcel()")
    public void testEditJustCreatedPost() throws SQLException, ClassNotFoundException, IOException {
        Map<String, String> dataForNewPostFromExcelFile = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(),
                "newPost");

        DB_Util_seleniumUser dbUtilSeleniumUsers = new DB_Util_seleniumUser();
        postTitle = String.format(dataForNewPostFromExcelFile.get("title"), "Myrhorodska " + TIME_STAMP);
        String postBody = String.format(dataForNewPostFromExcelFile.get("body"), "Myrhorodska" + TIME_STAMP);
        String valueInDropDown = dataForNewPostFromExcelFile.get("option");
        String state = dataForNewPostFromExcelFile.get("checkBoxStatus");
        updatedPostTitle = postTitle + dbUtilSeleniumUsers.getAliasForLogin(LOGIN);

        pageProvider.loginPage().openLoginPageAndFillLoginFormWithPasswordFromDB();
        pageProvider.homePage().checkIsredirectToHomePage();

        createNewPostWithExcelData(postTitle, postBody, valueInDropDown, state);

        pageProvider.getPostPage()
                .clickOnEditButton()
                .updateTextIntoInputTitle(updatedPostTitle)
                .clickOnSaveUpdatesButton();
        pageProvider.homePage()
                .getHeader()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(updatedPostTitle);
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeed()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(updatedPostTitle)
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(postTitle);
    }
}



