package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qaauto");
        pageProvider.loginPage().enterTextInToInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qaauto1");
        pageProvider.loginPage().enterTextInToInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Text 'Invalid username/password' is not visible", pageProvider.loginPage().isTextInvalidLoginOrPasswordDisplayed());
        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().isButtonSignOutVisible());
    }
}