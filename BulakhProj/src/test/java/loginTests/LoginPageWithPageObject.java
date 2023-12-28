package loginTests;

import baseTast.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginPageWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qaauto");
        pageProvider.loginPage().enterTextInToInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("invalid_user");
        pageProvider.loginPage().enterTextInToInputPassword("invalid_password");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().isButtonSingOutVisible());
        Assert.assertTrue("Button SignIn is visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Alert Danger is not visible", pageProvider.loginPage().isInvalidLoginMessageDisplayed());

    }


}
