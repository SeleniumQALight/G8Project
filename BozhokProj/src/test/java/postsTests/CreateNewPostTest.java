package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Bozhok" + Util.getDateAndTimeFormatted();

    @Test
    public void TC_001_createNewPost() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage().getHeader().clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage().enterTitleInToInputTitle(POST_TITLE)
                .enterTextInToInputBody("Body text")
//                .selectTextInToDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .clickOnSaveNewButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccesMassegeDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkPostWithTitlesIsPresent(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        pageProvider.homePage().openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectOnMyProfilePage()
                .deletePostTillPresent(POST_TITLE)
        ;
    }
}