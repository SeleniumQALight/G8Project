package postsTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

import static libs.Util.getDateAndTimeFormatted;

public class CreateNewPostTest extends BaseTest {

    final String POST_TITLE = "TC_001_pavlyk-" + getDateAndTimeFormatted();

    @Test
    public void TC_001_createNewPost() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextIntoInputBody("body text")
//                .selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .chckIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE);

    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .chckIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)
        ;
    }

}
