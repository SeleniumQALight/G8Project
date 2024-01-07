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

        Assert.assertTrue("Button SignOut is not visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("qaautoinvalid");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignIn is not visible",
                pageProvider.loginPage().isButtonSignInPresent());
        Assert.assertFalse("Button SignOut is visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Invalid login message is not visible",
                pageProvider.loginPage().isInvalidLoginMessageDisplayed());
    }
}
