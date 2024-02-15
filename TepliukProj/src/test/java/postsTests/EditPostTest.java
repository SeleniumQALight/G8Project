package postsTests;

import baseTest.BaseTest;
import libs.ConfigProvider;
import libs.DB_Util_seleniumUsers;
import libs.ExcelDriver;
import libs.Util;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class EditPostTest extends BaseTest {
    final String LOGIN = "newqaauto";
    final String TIME = Util.getDateAndTimeFormatted();
    final String POST = "New post successfully created.";
    String postTitle;
    String updatedPostTitle;


    public void createNewPost(String postTitle, String postBody, String dropDown, String checkBoxStatus, String checkBoxvalue) throws SQLException, ClassNotFoundException {
        pageProvider.homePage().getHeader()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleIntoInputTitle(postTitle)
                .enterTextIntoInputBody(postBody)
                .selectValueInDropDown(dropDown)
                .setCheckBoxUniquePostChosen(checkBoxStatus)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(POST)
                .checkTextInTitleAfterCreatePost(postTitle)
                .checkTextInBodyAfterCreatePost(postBody)
                .checkPostUniqueStateAfterCreatePost(checkBoxvalue)

        ;

    }

    @Test
    public void TC_Tepliuk_editPost() throws SQLException, ClassNotFoundException, IOException {
        Map<String, String> dataForNewPost = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(),
                "newPost");
        DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
        logger.info(dbUtilSeleniumUsers.getPassForLogin(LOGIN));
        postTitle = String.format(dataForNewPost.get("title"), "TC_Tepliuk_" + TIME);
        String postBody = String.format(dataForNewPost.get("body"), "TC_Tepliuk");
        String postOption = dataForNewPost.get("option");
        String postCheckBoxStatus = dataForNewPost.get("checkBoxStatus");
        String postCheckBoxValue = dataForNewPost.get("checkBoxValue");
        updatedPostTitle = postTitle + dbUtilSeleniumUsers.getAliasForLogin(LOGIN);
        pageProvider.loginPage()
                .openLoginPage();
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
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(updatedPostTitle)
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(postTitle)
        ;
    }
}
