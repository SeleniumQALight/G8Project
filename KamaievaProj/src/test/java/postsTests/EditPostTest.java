package postsTests;

import baseTest1.BaseTest;

import libs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class EditPostTest extends BaseTest {

    final String LOGIN = "newqaauto";

    DB_Util_seleniumUser dbUtilSeleniumUser = new DB_Util_seleniumUser();

    String POST_TITLE,
            POST_BODY,
            POST_DROPDOWN_OPTION,
            POST_CHECKBOX_STATUS,
            POST_CHECKBOX_VALUE,
            POST_TITLE_UPDATED;

    @Before
    public void createPost() throws SQLException, ClassNotFoundException, IOException {

        Map<String, String> dataForCreatingPost = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(),
                "newPost");

        POST_TITLE = String.format(dataForCreatingPost.get("title"), "TC_002_Kamaieva" + Util.getDateAndTimeFormatted());
        POST_BODY = String.format(dataForCreatingPost.get("body"), "TC_002_body" + Util.getDateAndTimeFormatted());
        POST_DROPDOWN_OPTION = dataForCreatingPost.get("option");
        POST_CHECKBOX_STATUS = dataForCreatingPost.get("checkBoxStatus");
        POST_CHECKBOX_VALUE = dataForCreatingPost.get("checkBoxValue");

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(dbUtilSeleniumUser.getPassForLogin(LOGIN))
                .clickOnButtonSignIn()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .checkboxUniquePost(POST_CHECKBOX_STATUS)
                .selectValueInDropdown(POST_DROPDOWN_OPTION)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUniqueStateConfirmExpectedValue(POST_CHECKBOX_VALUE)
                .checkPostTitleIsPresentOnPostPage(POST_TITLE)
                .checkPostBodyIsPresentOnPostPage(POST_BODY)
                .checkIsPostNoteStateConfirmExpectedValue("One Person");

        pageProvider.getPostPage().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE);
    }

    @Test
    public void TC_002_updatePost() throws SQLException, ClassNotFoundException, IOException {
        POST_TITLE_UPDATED = POST_TITLE + dbUtilSeleniumUser.getAliasForLogin(LOGIN);

        pageProvider.getHomePage().getHeader().clickOnButtonMyProfile().clickOnPostWithTitle(POST_TITLE);
        pageProvider.getPostPage().clickOnEditButton()

                .enterTextIntoInputTitle(POST_TITLE_UPDATED);
        pageProvider.getPostPage()
                .clickOnSaveUpdatesButton()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("Post successfully updated.");
        pageProvider.getPostPage().getHeader()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE_UPDATED);
    }

    @After
    public void deletePosts() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostWhilePresent(POST_TITLE_UPDATED)
                .checkIsRedirectToMyProfilePage()
                .deletePostWhilePresent(POST_TITLE);
    }
}
