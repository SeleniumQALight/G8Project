package postTests;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;

import static libs.TestData.*;

public class CreateNewPostTest extends BaseTest {


    @Test
    public void TC_001_createNewPost() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .setStateOfCheckBox("check")
                //.selectTextInDropDown("Привтне повідомлення")
                .selectValueInDropDown(DROPDOWN_VALUE)
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsTitleVisible(POST_TITLE)
                .checkIsBodyVisible(POST_BODY)
                .checkNoteCreatePostMessage(DROPDOWN_VALUE)
                .checkStateOfUniquePost("yes")
                .getHeader().clickOnButtonProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE);
    }

    @After
    public void deletePost() {
        pageProvider.homePage().openHomePageLoginIfNeeded()
                .getHeader().clickOnButtonProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(POST_TITLE);
    }
}
