package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_kraynov" + Util.getDateAndTimeFormatted();
    @Test
    public void TC_001_createNewPost() {
            pageProvider.loginPage()
                    .openLoginPageFillLoginFormWithValidCred()
                    .checkIsRedirectToHomePage()
                    .getHeader().clickOnButtonCreatePost()
                    .checkIsRedirectToCreatePostPage()
                    .enterTitleInToInputTitle(POST_TITLE)
                    .enterTextIntoInputBody("body text")
                    //.selectTextInDropDown("Частное сообщение")
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
    }

    @After
    public void deletePosts() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded();
    }

}
