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
                .checkIsRedirectOnCreatePostPage()
                .enterTextInToInputTitle("Myrhorodska Title")
                .enterTextInToInputBody("Myrhorodska Body")
                .selectValueInDropDown("One Person")
                .clickOnSaveNewButton()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;
    }
}
