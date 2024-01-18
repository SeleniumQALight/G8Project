package postsTests;

import baseTest.BaseTest;

import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Lupik" + Util.getDateAndTimeFormatted();
    final String POST_UNIQUE_CHECKBOX_STATUS = "uncheck";
    final String POST_BODY = "Lupik body";

    @Test

    public void TC_001_createNewPost() {

        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInToInputTitle(POST_TITLE)
                .enterTextInToInputBody(POST_BODY)
                .setUniquePostCheckboxSelected("uncheck")
                //.selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTitleTextInCreatedPost(POST_TITLE)
                .checktextOfVisibilityOfMessage("One Person")
                .checkTextThisPostUnique("no")
                .checkBodyTextInCreatedPost(POST_BODY)


        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)

        ;


    }

    @After
    public void deletePost() {
        pageProvider.homePage().openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(POST_TITLE)

        ;
    }

}
