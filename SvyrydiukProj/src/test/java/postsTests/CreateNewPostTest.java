package postsTests;

import baseTest.BaseTest;
import org.junit.Test;
import libs.Util;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Svyrydiuk"+Util.getDateAndTimeFormatted();

    @Test

    public void TC_001_createNewPostTest() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("New Body Svyrydiuk")
                //.selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;
        pageProvider.getPostPage().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)  //checkIsPostWasAdded(POST_TITLE)
        ;
    }


}
