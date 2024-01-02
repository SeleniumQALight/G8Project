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

        // check button Create Post, MyProfile, UserName are not visible
        Assert.assertTrue("Button Create Post is not visible",
                pageProvider.homePage().isButtonCreatePostVisible());
        Assert.assertTrue("Button My Profile is not visible",pageProvider.homePage().isButtonMyProfileVisible());
        Assert.assertTrue("User name is not visible",pageProvider.homePage().isUserNameVisible());

        // check input Login and Password are not visible
        Assert.assertFalse("Input Login is visible",pageProvider.loginPage().isInputLoginVisible());
        Assert.assertFalse("Input Password is visible",pageProvider.loginPage().isInputPasswordVisible());

    }
}
