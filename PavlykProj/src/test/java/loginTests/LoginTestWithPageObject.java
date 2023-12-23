package loginTests;

import baseTest.BaseTest;
import org.example.util.ExtraOptions;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty123" + ExtraOptions.getFormattedDate());
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().isButtonSignOutVisible());
        Assert.assertTrue("Button 'Sign In' is visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Warning message Invalid username/password is visible", pageProvider.loginPage().isWarningMessageVisible());
    }
}
