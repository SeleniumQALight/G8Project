package postsTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Myhashko " + Util.getDateAndTimeFormatted();
    final String Value_In_DropDown = "One Person";

    @Test
    public void createNewPost() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWhithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleField(POST_TITLE)
                .enterTextIntoInputBody("Myhashko body")
                //.selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown(Value_In_DropDown)
                .setStatusOfCheckBoxIsThisPostUnique("check")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .chekStatusOfCheckBoxIsThisPostUniqueOnPostPage("yes")
                .checkTitleOnPostPageEqualsTileOnCreatePostPage()
                .checkBodyOnPostPageEqualsTileOnCreatePostPage()
                .checkOfNoteText(Value_In_DropDown)

        ;

        pageProvider.getPostPage().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
        ;
    }
}
