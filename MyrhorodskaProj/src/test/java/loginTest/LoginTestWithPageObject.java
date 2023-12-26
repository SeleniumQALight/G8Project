package loginTest;

import BaseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qaauto");
        pageProvider.loginPage().enterTextInToInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertFalse("Button sign out is not visible", pageProvider.homePage().isButtonSignOutVisible());
        Assert.assertTrue("Button Sign In is visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Button SignOut is visible", pageProvider.homePage().isButtonSignOutVisible());

    }
    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qaauto1");

        pageProvider.loginPage().enterTextInToInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().isButtonSignOutVisible());
        Assert.assertTrue("Button SignIn is visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Alert is visible", pageProvider.loginPage().isInvalidUserNamePasswordMessageVisible());

    }
}
