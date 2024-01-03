package loginTests;

import baseTest.BaseTest;
import org.example.util.ExtraOptions;
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

        pageProvider.homePage().getHeader().isButtonSignOutVisible();
        pageProvider.homePage().getHeader().isButtonCreatePostVisible();
        pageProvider.homePage().getHeader().isMyProfileLinkVisible();
        pageProvider.homePage().getHeader().isUserNameVisible();
        pageProvider.homePage().getHeader().checkTextInUsername(VALID_LOGIN_UI);
        pageProvider.loginPage().isInputLoginNotVisible();
        pageProvider.loginPage().isInputPasswordNotVisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI + ExtraOptions.getFormattedDate());
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutDisplayed());
        Assert.assertTrue("Button 'Sign In' is visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Warning message Invalid username/password is visible", pageProvider.loginPage().isWarningMessageVisible());
    }
}
