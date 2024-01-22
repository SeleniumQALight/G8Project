package LoginTests;

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
        Assert.assertTrue("Button Create Post is visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("Button My Profile is visible", pageProvider.homePage().getHeader().isButtonMyProfileVisible());
        Assert.assertTrue("Name of user is visible", pageProvider.homePage().getHeader().isNameOfUserVisible());
        Assert.assertFalse("The fiels for login is not visible", pageProvider.loginPage().isInputLoginVisible());
        Assert.assertFalse("The fiels for password is not visible", pageProvider.loginPage().isInputPasswordVisible());

    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("invalidLogin");
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Button SignIn is visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Error message is visible", pageProvider.loginPage().isErrorMessageVisible());
    }

    @Test
    public void checkCorrectStateOfUserLogin() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().checkIsButtonSignOutVisible();
        pageProvider.homePage().openNewTabInBrowser();
        pageProvider.homePage().switchToNewTab(1);
        pageProvider.loginPage().openLoginPage();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.homePage().switchToNewTab(0);
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.loginPage().closeTab(1);
        pageProvider.homePage().switchToNewTab(0);
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
    }

    @Test
    public void checkMissedDataLoginPasswordAfterRefreshPage() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.homePage().refreshPage();
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonSignOutNotVisible();
    }

}