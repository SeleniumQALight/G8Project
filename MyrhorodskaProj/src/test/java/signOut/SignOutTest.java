package signOut;

import BaseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.ErrorMessage.*;
import static data.TestData.INVALID_TEXT;

public class SignOutTest extends BaseTest {
    @Test
    public void signOut() {
            pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred();
            pageProvider.homePage().checkIsredirectToHomePage();
            Assert.assertTrue("Button Sign Out is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
            Assert.assertTrue("Button MyProfile is not visible", pageProvider.homePage().getHeader().isButtonMyProfileVisible());
            Assert.assertTrue("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
            Assert.assertTrue("Button Chat is not visible", pageProvider.homePage().getHeader().isButtonChatVisible());
            Assert.assertTrue("Button Search is not visible", pageProvider.homePage().getHeader().isButtonSearchVisible());

            pageProvider.loginPage().isInputUsernameNotVisible();
            pageProvider.loginPage().isInputPasswordNotVisible();
            pageProvider.loginPage().isButtonSignInNotVisible();
            pageProvider.homePage().getHeader().clickOnButtonSignOut().checkIsRedirectToLoginPage();
            Assert.assertTrue("Button Sign In is not visible", pageProvider.loginPage().isButtonSignInVisible());
            pageProvider.homePage().getHeader().isButtonSearchNotVisible();
            pageProvider.homePage().getHeader().isButtonChatNotVisible();
            pageProvider.homePage().getHeader().isButtonMyProfileNotVisible();
            pageProvider.homePage().getHeader().isButtonCreatePostNotVisible();
            pageProvider.homePage().getHeader().isButtonSignOutNotVisible();
            Assert.assertTrue("Input Username is not visible", pageProvider.loginPage().isInputUsernameVisible());
            Assert.assertTrue("Input Password is not visible", pageProvider.loginPage().isInputPasswordVisible());
        }
    @Test
    public void validationMessagesInRegistrationForm() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().inputTextIntoRegistrationLogin(INVALID_TEXT);
        pageProvider.loginPage().inputTextIntoRegistrationPassword(INVALID_TEXT);
        pageProvider.loginPage().inputTextIntoRegistrationEmail(INVALID_TEXT);
        pageProvider.loginPage().clickOnButtonSignUp();
        Assert.assertTrue("Validation Message For User Name Register Input is not displayed",
                pageProvider.loginPage().isValidationMessageForRegistrationInputLoginDisplayed());
        pageProvider.loginPage().checkInputInRegistrationLogin(USER_NAME_MESSAGE);
        Assert.assertTrue("Validation Message For Email Register Input is not displayed",
                pageProvider.loginPage().isValidationMessageForRegistrationInputEmailDisplayed());
        pageProvider.loginPage().checkInputInRegistrationEmail(EMAIL_MESSAGE);
        Assert.assertTrue("Validation Message For Password Register Input is not displayed",
                pageProvider.loginPage().isValidationMessageForRegistrationInputPasswordDisplayed());
        pageProvider.loginPage().checkInputInRegistrationPassword(PASSWORD_MESSAGE);
    }
}
