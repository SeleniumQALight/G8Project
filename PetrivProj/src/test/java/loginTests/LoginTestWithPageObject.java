package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Button Sign Out is not visible", pageProvider.homePage().isButtonSignOutVisible());
    }

    @Test
    public void inValidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("invalid_username");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Button Sign In is not visible", pageProvider.homePage().isButtonSignInVisible());
        Assert.assertTrue("Error message is not visible", pageProvider.homePage().isInvalidUsernameOrPasswordMessageVisible());
        Assert.assertFalse("Button Sign Out is visible", pageProvider.homePage().isButtonSignOutVisible());
    }
}
