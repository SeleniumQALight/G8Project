package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Sushko " + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Sushko content body text";
    @Test
    public void TC_001_createNewPost(){
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                //.selectTextInDropDown("Приватне повідомлення")
                .setUniquePostCheckboxSelected()
                .selectValueInDropDown("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextThisPostUnique("yes")
                .checkTitleIsPresentInPost(POST_TITLE)
                .checkBodyContentIsPresentInPost(POST_BODY)
                .checkCorrectNoteInPost("One Person")
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)
        ;
    }

    @After
    public void deletePost(){
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)
        ;
    }
}
