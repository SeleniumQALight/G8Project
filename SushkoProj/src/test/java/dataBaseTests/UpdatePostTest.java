package dataBaseTests;

import baseTest.BaseTest;
import libs.*;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class UpdatePostTest extends BaseTest {
    final String LOGIN = "newqaauto";
    final String TIME_STAMP = Util.getDateAndTimeFormatted();

    DB_Util_seleniumUsersTable dbUtilSeleniumUsersTable = new DB_Util_seleniumUsersTable();
    String postTitle, postBody, postOptionForDropdown, postTitleUpdated;

    public void loginAndCreatePost(String postTitle, String postBody, String postOptionForDropdown) throws SQLException, ClassNotFoundException {
        String password = dbUtilSeleniumUsersTable.getPasswordForLogin(LOGIN);

        pageProvider.loginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(LOGIN)
                .enterTextIntoInputPassword(password).clickOnButtonSingIn();

        pageProvider.homePage().checkIsRedirectToHomePage()
                .getHeader()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(postTitle)
                .enterTextIntoInputBody(postBody)
                .setUniquePostCheckboxSelected()
                .selectValueInDropDown(postOptionForDropdown)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTitleIsPresentInPost(postTitle)
                .checkBodyContentIsPresentInPost(postBody)
                .checkCorrectNoteInPost(postOptionForDropdown)
                ;
    }

    @Test
    public void updatePost() throws SQLException, ClassNotFoundException, IOException {
        Map<String, String> dataForCreatingPost = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE_PATH() + "testData-updated.xls",
                "newPost");

        postTitle = String.format(dataForCreatingPost.get("title"), TIME_STAMP);
        postBody = String.format(dataForCreatingPost.get("body"), TIME_STAMP);
        postOptionForDropdown = dataForCreatingPost.get("option");

        postTitleUpdated = postTitle + dbUtilSeleniumUsersTable.getAlias(LOGIN);

        loginAndCreatePost(postTitle, postBody, postOptionForDropdown);

        pageProvider.getPostPage()
                .clickOnEditPostButton()
                .enterTitleInToInputTitle(postTitleUpdated)
                .clickOnSaveUpdatesPostButton()
                .checkIsSuccessMessageDisplayed()
                .getHeader()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(postTitleUpdated)
                ;
    }

    @After
    public void deletePost(){
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(postTitleUpdated)
                .deletePostsTillPresent(postTitle)
        ;
    }
}
