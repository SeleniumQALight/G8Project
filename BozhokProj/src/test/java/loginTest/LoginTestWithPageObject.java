package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.*;

public class LoginTestWithPageObject  extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().isButtonCreatePostVisible();
        pageProvider.homePage().getHeader().isButtonProfileVisible();
        pageProvider.homePage().checkIsProfileNameVisible("qaauto");

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
                pageProvider.loginPage().isButtonSignOutVisible());
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
}
