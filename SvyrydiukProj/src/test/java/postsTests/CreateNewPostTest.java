package postsTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import libs.Util;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Svyrydiuk" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "New Post Body Svyrydiuk" + Util.getDateAndTimeFormatted();
    final String DROPDOWN_VALUE = "One Person"; // "Приватне повідомлення"


    @Test

    public void TC_001_createNewPostTest() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                //.selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown(DROPDOWN_VALUE)
                .setCheckbox("checked")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsCreatedPostHasTitle(POST_TITLE)
                .checkIsCreatedPostHasBody(POST_BODY)
                .checkIsCreatedPostHasValueInDropDown(DROPDOWN_VALUE)
                .checkCheckboxStatus("yes")
        ;
        pageProvider.getPostPage().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)  //checkIsPostWasAdded(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnButtonMyProfile()
                .deletePostTillPresent(POST_TITLE)
        ;
    }

}
