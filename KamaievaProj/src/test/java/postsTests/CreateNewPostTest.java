package postsTests;

import libs.Util;
import baseTest1.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    final String POST_TITLE = "TC_001_Kamaieva" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Body text" + Util.getDateAndTimeFormatted();

    @Test
    public void TC_001_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCreate()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                //.selectTextInDropdown("Приватне повідомлення")
                .checkboxUniquePost("check")
                .selectValueInDropdown("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUniqueStateConfirmExpectedValue("yes")
                .checkPostTitleIsPresentOnPostPage(POST_TITLE)
                .checkPostBodyIsPresentOnPostPage(POST_BODY)
                .checkIsPostNoteStateConfirmExpectedValue("One Person");

        pageProvider.getPostPage().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE);
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostWhilePresent(POST_TITLE);
    }
}
