package postTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .checkIsRedirectedToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectedToCreatePostPage()
                .enterTextInToInputTitle("New Post Title " + "Yashchenko")
                .enterTextInToInputBody("New Post Body " + "Yashchenko")
//                .selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")

        ;
    }
}
