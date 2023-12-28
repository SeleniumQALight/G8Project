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

        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("NotValidLogin");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Warning message is visible", pageProvider.loginPage().isWarningMessageVisible());
        Assert.assertTrue("Button Sign In is visible", pageProvider.loginPage().isButtonSignInVisible());
    }
}
