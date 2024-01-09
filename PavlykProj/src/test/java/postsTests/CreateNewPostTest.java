package postsTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

import static libs.Util.getDateAndTimeFormatted;

public class CreateNewPostTest extends BaseTest {

    final String POST_TITLE = "TC_001_pavlyk-" + getDateAndTimeFormatted();
    final String POST_BODY = "body text";
    final String SUCCESS_MESSAGE = "New post successfully created.";
    final String DROPDOWN_VALUE = "One Person";


    @Test
    public void TC_001_createNewPost() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
//                .selectTextInDropDown("Приватне повідомлення")
                .setIsThisPostUniqueCheckboxState("check")
                .selectValueInDropDown(DROPDOWN_VALUE)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(SUCCESS_MESSAGE)
                .checkPostWithTitleIsPresent(POST_TITLE)
                .checkPostWithContentIsPresent(POST_BODY)
                .checkIsThisPostUniqueTextPresent("yes")
                .checkNoteTextPresent(DROPDOWN_VALUE)
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .chckIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE);

    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
        ;
    }

}
