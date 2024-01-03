package loginTests;

import baseTast.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class LoginPageWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertTrue("Button SignOut is not visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("invalid_user");
        pageProvider.loginPage().enterTextInToInputPassword("invalid_password");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button SignIn is visible", pageProvider.loginPage().isButtonSingInVisible());
        Assert.assertTrue("Alert Danger is not visible", pageProvider.loginPage().isInvalidLoginMessageDisplayed());

    }


}
