package postTest;

import BaseTest.BaseTest;

import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsredirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectedOnCreatePostPage()
                .enterTextInToInputTitle("Myrhorodska Title")
                .enterTextInToInputBody("Myrhorodska Body")
                .selectValueInDropDown("приватне повідомлення")
                .selectTextInDropDown("One person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;
    }
}
