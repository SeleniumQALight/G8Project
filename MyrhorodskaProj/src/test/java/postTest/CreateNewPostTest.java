package postTest;

import BaseTest.BaseTest;

import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_myrhorodska " + Util.getDateAndTimeFormatted();
    @Test
    public void createNewPost() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsredirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
                .enterTextInToInputTitle(POST_TITLE)
                .enterTextInToInputBody("Myrhorodska Body")
                .selectValueInDropDown("One Person")
                .setStatusOfCheckBoxIsThisPostUnique("check")
                .clickOnSaveNewButton()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .chekStatusOfCheckBoxIsThisPostUniqueOnPostPage("yes")
                .checkTitleOnPostPageEqualsTileOnCreatePostPage()
                .checkBodyOnPostPageEqualsTileOnCreatePostPage()
                .checkOfNoteTextValue("VALUE_IN_DROPDOWN")
        ;
        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE );
    }
    @After
    public void deletePost() {
        pageProvider.homePage().openHomePageAndLoginIfNeed()
                        .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }
}
