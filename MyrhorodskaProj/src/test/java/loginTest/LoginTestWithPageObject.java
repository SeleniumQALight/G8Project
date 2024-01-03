package loginTest;

import BaseTest.BaseTest;
import libs.TestData;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertTrue("Button SignOut is visible", pageProvider.homePage().isButtonSignOutVisible());
    }
    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qaauto1");

        pageProvider.loginPage().enterTextInToInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertFalse("Button 'Sign Out' is visible", pageProvider.homePage().isButtonSignOutVisible());
        Assert.assertTrue("Button 'Sign In' is not visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Alert is not visible", pageProvider.loginPage().isInvalidUserNamePasswordMessageVisible());
        Assert.assertTrue("Button sign out is not visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

    @Test
    @Ignore
    public void unValidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qalogin");
        pageProvider.loginPage().enterTextInToInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSingIn();


      //  Assert.assertFalse("Button SignOut is not visible",
      //          pageProvider.loginPage().getHeader().isButtonSignOutVisible());
    }
}
