package loginTests;

import BaseTest.BaseTest;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.*;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Description("Check that user can login with valid login")
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button 'Sign Out' is not visible", pageProvider.getHomePage().isButtonSignOutVisible());
    }

    @Test
    @Description("Check that user cannot login with invalid login")
    public void invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaautoInvalid");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button 'Sign Out' is visible", pageProvider.getHomePage().isButtonSignOutVisible());
        Assert.assertTrue("Button 'Sign In' is not visible", pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertTrue("Alert is not visible", pageProvider.getLoginPage().isAlertInvalidUsernamePasswordVisible());
    }
}
