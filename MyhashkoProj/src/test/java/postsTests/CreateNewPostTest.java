package postsTests;

import baseTest.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    @Ignore
    public void createNewPost() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWhithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoTitleField("Myhashko title")
                .enterTextIntoInputBody("Myhashko body")
                //.selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")

        ;



    }

}
