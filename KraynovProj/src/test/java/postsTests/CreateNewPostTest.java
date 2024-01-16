package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_kraynov" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "body text";
    final String SUCCESS_MESSAGE = "New post successfully created.";
    final String DROPDOWN_VALUE = "One Person";
    @Test
    public void TC_001_createNewPost() {
            pageProvider.loginPage()
                    .openLoginPageFillLoginFormWithValidCred()
                    .checkIsRedirectToHomePage()
                    .getHeader().clickOnButtonCreatePost()
                    .checkIsRedirectToCreatePostPage()
                    .enterTitleInToInputTitle(POST_TITLE)
                    .enterTextIntoInputBody(POST_BODY)
                    //.selectTextInDropDown("Частное сообщение")
                    .setIsThisPostUniqueCheckboxState("check")
                    .selectValueInDropDown(DROPDOWN_VALUE)
                    .clickOnSaveNewPostButton()
                    .checkIsRedirectToPostPage()
                    .checkIsSuccessMessageDisplayed()
                    .checkTextInSuccessMessage(SUCCESS_MESSAGE)
                    .checkPostWithTitleIsPresent(POST_TITLE)
                    .checkPostWithContentIsPresent(POST_BODY)
                    .checkIsThisPostUniqueTextPresent("yes")
                    .checkNoteTextPresent(DROPDOWN_VALUE)
            ;

            pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkPostWithTitleIsPresent(POST_TITLE)
            ;
    }

    @After
    public void deletePosts() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostWhilePresent(POST_TITLE)
        ;
    }

}
