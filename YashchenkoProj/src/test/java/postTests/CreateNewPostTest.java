package postTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Yashchenko" + Util.getDateAndTimeFormatted();
    @Test
    public void TC_001_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .checkIsRedirectedToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectedToCreatePostPage()
                .enterTextInToInputTitle(POST_TITLE)
                .enterTextInToInputBody("New Post Body Yashchenko")
//                .selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .selectIsUniqueCheckboxUsingStringValue("check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsThisPostUniqueValuePresent("yes")
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
