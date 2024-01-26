package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_storozhuk " + Util.getDateAndTimeFormatted();
    final String POST_BODY = "check testbody" + Util.getDateAndTimeFormatted();
    final String UNIQUECHECKBOX = "check"; //possible values: check, uncheck
    final String POST_VISIBILITY = "One Person"; //possible values: One Person, All Users, Group Message

    @Test
    public void TC_001_createNewPost() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .tickCheckbox(UNIQUECHECKBOX)
                .selectValueInDropDown(POST_VISIBILITY)
                .clickOnSaveNewPostButton()
                //-----CHECKS
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextInPostTitle(POST_TITLE)
                .checkTextInPostBody(POST_BODY)
                .checkTextInUniquePostInfoMessage(UNIQUECHECKBOX)
                .checkTextInPostNote(POST_VISIBILITY);


        pageProvider.getPostPage().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)

        ;
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(POST_TITLE);
    }

}
