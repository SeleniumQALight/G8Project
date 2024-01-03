package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Button Sign Out is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

    @Test
    public void inValidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("invalid_username");
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Button Sign In is not visible", pageProvider.homePage().isButtonSignInVisible());
        Assert.assertTrue("Error message is not visible", pageProvider.homePage().isInvalidUsernameOrPasswordMessageVisible());
        Assert.assertFalse("Button Sign Out is visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }
}
