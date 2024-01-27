package postsTests;

import baseTest.BaseTest;
import libs.*;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class EditPostTest extends BaseTest {
    final String LOGIN = "newqaauto";
    final String TIME_STAMP = Util.getDateAndTimeFormatted();
    final String POST_CREATED_SUCCESS_MESSAGE = "New post successfully created.";
    final String NOTE_TEXT = "Note: This post was written for ";
    String postTitle;
    String updatedPostTitle;

    public void createNewPost(String postTitle, String postBody, String dropDownOption, String checkBoxStatus, String checkBoxValue) {
        pageProvider.loginPage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(postTitle)
                .enterTextIntoInputBody(postBody)
                .selectValueInDropDown(dropDownOption);
        pageProvider.getCreatePostPage()
                .setCheckBoxUniquePost(checkBoxStatus)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(POST_CREATED_SUCCESS_MESSAGE)
                .checkTextInPostTitle(postTitle)
                .checkTextInPostBody(postBody)
                .checkTextInUniquePostInfoMessage(checkBoxValue)
                .checkTextInPostNote(NOTE_TEXT + dropDownOption);
    }

    @Test
    public void TC_003_editPost() throws SQLException, ClassNotFoundException, IOException {
        Map<String, String> dataForNewPost = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(),
                "newPost");

        DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
        logger.info(dbUtilSeleniumUsers.getPassForLogin(LOGIN));

        postTitle = String.format(dataForNewPost.get("title"), "TC_003_" + TIME_STAMP);
        String postBody = String.format(dataForNewPost.get("body"), "TC_003");
        String postOption = dataForNewPost.get("option");
        String postCheckBoxStatus = dataForNewPost.get("checkBoxStatus");
        String postCheckBoxValue = dataForNewPost.get("checkBoxValue");
        updatedPostTitle = postTitle + dbUtilSeleniumUsers.getAliasForLogin(LOGIN);

        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(LOGIN);
        pageProvider.loginPage().enterTextIntoInputPassword(dbUtilSeleniumUsers.getPassForLogin(LOGIN));
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage().checkIsRedirectToHomePage();

        createNewPost(postTitle, postBody, postOption, postCheckBoxStatus, postCheckBoxValue);

        pageProvider.getPostPage()
                .clickOnEditButton()
                .enterTextIntoInputTitle(updatedPostTitle);
        pageProvider.getPostPage()
                .clickOnSaveUpdatesButton();
        pageProvider.getPostPage().getHeader()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(updatedPostTitle);
    }
    @After
    public void deletePosts() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(updatedPostTitle)
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(postTitle);
    }
}
