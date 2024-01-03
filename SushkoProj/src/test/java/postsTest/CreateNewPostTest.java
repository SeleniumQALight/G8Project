package postsTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost(){
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .checkIsRedirectToHimePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle("Sushko post title")
                .enterTextIntoInputBody("body text")
                //.selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;
    }
}
