package postsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage().getHeader().clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage().enterTitleInToInputTitle("Bozhok Title")
                .enterTextInToInputBody("Body text")
//                .selectTextInToDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .clickOnSaveNewButton()
                .checkIsRedirectToPstPage()
                .checkIsSuccesMassegeDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                ;
    }
}
