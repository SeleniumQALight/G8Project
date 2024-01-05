package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost() {
            pageProvider.loginPage()
                    .openLoginPageFillLoginFormWithValidCred()
                    .checkIsRedirectToHomePage()
                    .getHeader().clickOnButtonCreatePost()
                    .checkIsRedirectToCreatePostPage()
                    .enterTitleInToInputTitle("kraynov title")
                    .enterTextIntoInputBody("body text")
                    //.selectTextInDropDown("Частное сообщение")
                    .selectValueInDropDown("One Person")
                    .clickOnSaveNewPostButton()
                    .checkIsRedirectToPostPage()
                    .checkIsSuccessMessageDisplayed()
                    .checkTextInSuccessMessage("New post successfully created.")
            ;
    }

}
