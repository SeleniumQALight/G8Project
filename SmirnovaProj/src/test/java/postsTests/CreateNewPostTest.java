package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Smirnova" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Post body";
    final String POST_BODY_NOTE = "Note: This post was written for One Person";
    @Test
    public void TC_001_createNewPost() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleIntoInput(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                // .selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;
        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)
        ;
        pageProvider.getMyProfilePage().clickOnPostWithTitle(POST_TITLE)
                .checkTextInPostTitle(POST_TITLE)
                .checkTextInPostBody(POST_BODY)
                .checkTextInPostBodyNote(POST_BODY_NOTE)
                .checkPostUniqueState("no")
        ;
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded();
    }
}
