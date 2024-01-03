package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost(){
pageProvider.loginPage()
        .openLoginPageAndFillLoginFormWithValidCred()
        .checkIsRedirectToHomePage()
        .getHeader()
        .clickOnButtonCreatePost()
        .checkIsRedirectToCreatePostPage()
        .enterTextIntoTitleField("prudkyi title")
        .enterTextIntoBodyField("prudkyi body")
        //.selectTextInDropDown("Приватне повідомлення")
        .selectValueInDropDown("One Person")
        .clickOnSaveNewPostButton()
        .checkIsRedirectToPostPage()
        .checkIsSuccessMessageDisplayed()
        .checkTextInSuccessMessage("New post successfully created.");
    }

}

