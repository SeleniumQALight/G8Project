package postsTests;

import baseTest.BaseTest;
import libs.Util;
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
                .checkIsRedirectToPstPage()
                .checkIsSuccesMassegeDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkPostWithTitlesIsPresent(POST_TITLE)
                ;
    }
}
