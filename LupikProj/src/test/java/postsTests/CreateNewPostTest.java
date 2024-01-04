package postsTests;

import baseTest.BaseTest;

import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test

    public void createNewPost() {

        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInToInputTitle("Lupik title")
                .enterTextInToInputBody("Lupik body")
                //.selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")

        ;


    }


}
