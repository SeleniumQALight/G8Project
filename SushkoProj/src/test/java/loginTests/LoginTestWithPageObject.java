package loginTests;

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
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertTrue("Button SignOut is not visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("qaauto_1");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertTrue("Button SignIn is not visible",
                pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertFalse("Button SignOut is visible",
                pageProvider.homePage().isButtonSignOutVisible());
        Assert.assertTrue("Validation message is not displayed",
                pageProvider.loginPage().isValidationMessageIsDisplayed());
    }
}
