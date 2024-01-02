package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.DEFAULT_VALID_LOGIN_UI;
import static libs.TestData.DEFAULT_VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(DEFAULT_VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(DEFAULT_VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonMyProfileVisible();
        pageProvider.homePage().getHeader().checkIsProfileNameVisible();
        pageProvider.homePage().getHeader().checkIsCreatePostButtonVisible();

        Assert.assertTrue("Button SignOut is visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());

    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaavto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button SignIn is visible", pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertTrue("Alert is visible", pageProvider.getLoginPage().isInvalidUserNamePasswordAlertVisible());

    }
}
