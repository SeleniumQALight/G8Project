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
        Assert.assertTrue("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("Button MyProfile is not visible", pageProvider.homePage().getHeader().isButtonMyProfileVisible());
        Assert.assertTrue("Username is not visible", pageProvider.homePage().getHeader().isUserNameVisible());
        pageProvider.homePage().getHeader().checkTextInUserName(VALID_LOGIN_UI);
        Assert.assertFalse("Input Username is visible", pageProvider.loginPage().isInputUserNameVisible());
        Assert.assertFalse("Input Password is visible", pageProvider.loginPage().isInputPasswordVisible());
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

    @Test
    public void checkLoggedInUserSessionInNewTab () {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage();
        Assert.assertTrue("Button Sign Out is not visible", pageProvider.loginPage().getHeader().isButtonSignOutVisible());
        pageProvider.homePage().openNewTabInBrowser();
        pageProvider.homePage().switchToTabInBrowser(1);
        pageProvider.loginPage().openLoginPage();
        Assert.assertTrue("Button Sign Out is not visible", pageProvider.loginPage().getHeader().isButtonSignOutVisible());
        pageProvider.loginPage().switchToTabInBrowser(0);
        Assert.assertTrue("Button Sign Out is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        pageProvider.homePage().switchToTabInBrowser(1);
        pageProvider.loginPage().closeTabAndSwitchToMainPage();
        Assert.assertTrue("Button Sign Out is not visible", pageProvider.loginPage().getHeader().isButtonSignOutVisible());
    }

    @Test
    public void checkInputsAreClearAfterRefresh () {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().refreshPage();
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertFalse("Button Sign Out is visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

    @Test
    public void validLoginUsingKeyboard() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().pressTabKey(2);
        pageProvider.loginPage().enterTextIntoInputActions(VALID_LOGIN_UI);
        pageProvider.loginPage().pressTabKey(1);
        pageProvider.loginPage().enterTextIntoInputActions(VALID_PASSWORD_UI);
        pageProvider.loginPage().pressEnterKey();
        Assert.assertTrue("Button Sign Out is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }
}
