package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_tepliuk" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Tepliuk body";
    final String POST_BODY_NOTE = "One Person";
    final String STATE_CHECK = "Check";

    @Test
    public void TC_001_createNewPost() {

        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                //.selectTextInDropDown("Приватне повідомлення");
                .setCheckBoxUniquePostChosen(STATE_CHECK)
                .selectValueInDropDown(POST_BODY_NOTE)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextInTitleAfterCreatePost(POST_TITLE)
                .checkTextInBodyAfterCreatePost(POST_BODY)
                .checkPostUniqueStateAfterCreatePost("yes")
                .checkOfNoteTextValue(POST_BODY_NOTE)
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(POST_TITLE)
        ;
    }
}