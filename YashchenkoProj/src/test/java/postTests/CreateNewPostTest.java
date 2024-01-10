package postTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Yashchenko" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "New Post Body Yashchenko" + Util.getDateAndTimeFormatted();
    @Test
    public void TC_001_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .checkIsRedirectedToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectedToCreatePostPage()
                .enterTextInToInputTitle(POST_TITLE)
                .enterTextInToInputBody(POST_BODY)
//                .selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .selectIsUniqueCheckboxUsingStringValue("check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsThisPostUniqueValuePresent("yes")
                .checkCreatedPostTitle(POST_TITLE)
                .checkCreatedPostBody(POST_BODY)
                .checkIsNoteAndNoteValuePresent()
                .checkValueOfNote("One Person")
        ;

        pageProvider.getPostPage().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)
        ;


    }
    @After
    public void deletePosts() {
        pageProvider.homePage()
                .openHomePageAndLogInIfNeeded()

        ;
    }

}
