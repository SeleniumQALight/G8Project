package loginTest;

import BaseTest.BaseTest;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;
import static libs.ConfigProvider.configProperties;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSingIn();
        Assert.assertTrue("Button Sign Out is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Username is not visible", pageProvider.homePage().getHeader().isUserNameVisible());
        Assert.assertTrue("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("Button MyProfile is not visible", pageProvider.homePage().getHeader().isButtonMyProfileVisible());
        pageProvider.homePage().getHeader().checkInputInUserName(VALID_LOGIN_UI);
        Assert.assertFalse("Input Username is visible", pageProvider.loginPage().isInputUsernameVisible());
        Assert.assertFalse("Input Password is visible", pageProvider.loginPage().isInputPasswordVisible());
    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(configProperties.DATA_FILE(),"validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("login");
        pageProvider.loginPage().enterTextInToInputPassword("pass");
        pageProvider.loginPage().clickOnButtonSingIn();
       }
    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qaauto1");

        pageProvider.loginPage().enterTextInToInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertFalse("Button 'Sign Out' is visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button 'Sign In' is not visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Alert is not visible", pageProvider.loginPage().isInvalidUserNamePasswordMessageVisible());

    }

    @Test
    @Ignore
    public void unValidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qalogin");
        pageProvider.loginPage().enterTextInToInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSingIn();


      //  Assert.assertFalse("Button SignOut is not visible",
      //          pageProvider.loginPage().getHeader().isButtonSignOutVisible());
    }
}
