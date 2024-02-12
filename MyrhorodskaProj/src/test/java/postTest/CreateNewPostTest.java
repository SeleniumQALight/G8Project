package postTest;

import BaseTest.BaseTest;

import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_myrhorodska " + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Myrhorodska Body";
    final String DROPDOWN_VALUE = "One Person";
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
                .setIsThisPostUniqueCheckBoxState("check")
                .clickOnSaveNewButton()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .chekStatusOfCheckBoxIsThisPostUniqueOnPostPage("yes")
                .checkTitleOnPostPageEqualsTileOnCreatePostPage()
                .checkBodyOnPostPageEqualsTileOnCreatePostPage()
                .checkPostWithTitleIsPresent(POST_TITLE)
                .checkPostWithContentIsPresent(POST_BODY)
                .checkIsThisPostUniqueTextPresent("yes")
                .checkOfNoteTextValue(DROPDOWN_VALUE)
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
