package postsTests;

import BaseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    @Test
    public void createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCreat()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle("Kamaieva title")
                .enterTextIntoInputBody("Body text")
                //.selectTextInDropdown("Приватне повідомлення")
                .selectValueInDropdown("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.");
    }
}
