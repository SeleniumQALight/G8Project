package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;

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
        pageProvider.loginPage().enterTextIntoInputLogin("quytto");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignIn is not visible", pageProvider.homePage().isButtonSignInVisible());
        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().isButtonSignOutVisible());
        Assert.assertTrue("Alert is not visible", pageProvider.loginPage().isAlertTextVisible());
    }
}
