package postTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_semerenko_" + Util.getDateAndTimeFormatted();
    @Test
    public void TC_001_createNewPost() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("New Post Body")
                //.selectTextInDropDown("Привтне повідомлення")
                .selectValueInDropDown("One Person")
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.postPage().getHeader().clickOnButtonProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent (POST_TITLE);
    }
}
