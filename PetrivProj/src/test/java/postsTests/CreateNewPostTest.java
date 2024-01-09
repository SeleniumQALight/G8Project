package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Petriv" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Post body text " + Util.getDateAndTimeFormatted();
    final String DROP_DOWN_VALUE = "One Person";
    @Test
    public void TC_001_createNewPost() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                //.selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown(DROP_DOWN_VALUE);
        pageProvider.getCreatePostPage()
                .setCheckBoxUniquePost("check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextInPostTitle(POST_TITLE)
                .checkTextInPostBody(POST_BODY)
                .checkTextInUniquePostInfoMessage("yes")
                .checkTextInPostNote("Note: This post was written for " + DROP_DOWN_VALUE)
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
        ;
    }
}
