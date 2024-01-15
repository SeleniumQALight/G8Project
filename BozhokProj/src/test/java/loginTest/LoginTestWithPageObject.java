package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonCreatePostVisible();
        pageProvider.homePage().getHeader().isButtonProfileVisible();
        pageProvider.homePage().getHeader().checkIsProfileNameVisible(VALID_LOGIN_UI);
        pageProvider.loginPage().checkIsInputLoginNotVisible();
        pageProvider.loginPage().checkIsInputPasswordNotVisible();

        Assert.assertTrue("Button SignOut is not visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

    @Test
    public void unValidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(INVALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button SignIn is visible",
                pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Message is not visible",
                pageProvider.loginPage().isMessageInvalidUsernamePasswordInVisible());
    }

    // check enty of short data in the registration form
    @Test
    public void shortDataInRegistrationForm() {
        pageProvider.loginPage().fillOutTheLoginFormRegistration();
    }

    // check Sign Out
    @Test
    public void checkSignOutTest() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred();
        pageProvider.homePage().getHeader().checkIsButtonSearchVisible();
        pageProvider.homePage().getHeader().checkIsButtonChatVisible();
        pageProvider.homePage().getHeader().checkIsButtonMyProfileVisible();
        pageProvider.homePage().getHeader().checkIsButtonCreatePostVisible();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.loginPage().checkIsButtonSignInNotVisible();
        pageProvider.loginPage().checkIsInputPasswordNotVisible();
        pageProvider.loginPage().checkIsInputLoginNotVisible();
        pageProvider.homePage().getHeader().clickOnButtonSignOut();
        pageProvider.loginPage().checkIsRedirectOnLoginPage();
        pageProvider.homePage().getHeader().checkIsButtonSearchNotVisible();
        pageProvider.homePage().getHeader().checkIsButtonChatNotVisible();
        pageProvider.homePage().getHeader().checkIsButtonMyProfileNotVisible();
        pageProvider.homePage().getHeader().checkIsButtonCreatePostNotVisible();
        pageProvider.homePage().getHeader().checkIsButtonSignOutNotVisible();
        pageProvider.loginPage().checkIsButtonSignInVisible();

        // input Login visible
        Assert.assertTrue("Input Login is not visible",
                pageProvider.loginPage().isInputLoginVisible());
        // input Password visible
        Assert.assertTrue("Input Password is not visible",
                pageProvider.loginPage().isInputPasswordVisible());
    }
}
