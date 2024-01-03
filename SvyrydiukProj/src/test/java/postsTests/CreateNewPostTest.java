package postsTests;

import baseTest.BaseTest;
import org.junit.Test;


public class CreateNewPostTest extends BaseTest {

    @Test

    public void createNewPostTest() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle("New Post Svyrydiuk")
                .enterTextIntoInputBody("New Body Svyrydiuk")
                //.selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;
    }


}
