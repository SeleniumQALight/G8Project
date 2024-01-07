package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Sushko " + Util.getDateAndTimeFormatted();
    @Test
    public void TC_001_createNewPost(){
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .checkIsRedirectToHimePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextIntoInputBody("body text")
                //.selectTextInDropDown("Приватне повідомлення")
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
    public void deletePost(){
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
        ;
    }
}
