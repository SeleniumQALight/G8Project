package loginTests;

import baseTest.BaseTest;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.junit.Assert;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

@RunWith(JUnitParamsRunner.class)

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
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
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
        pageProvider.loginPage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.homePage().openNewTabInBrowser();
        pageProvider.homePage().switchToTabInBrowser(1);
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.loginPage().switchToTabInBrowser(0);
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.homePage().switchToTabInBrowser(1);
        pageProvider.loginPage().closeTabAndSwitchToMainPage();
        pageProvider.loginPage().getHeader().checkIsButtonSignOutVisible();
    }

    @Test
    public void checkInputsAreClearAfterRefresh () {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().refreshPage();
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonSignOutNotVisible();
    }

    @Test
    public void validLoginUsingKeyboard() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().pressTabKey(2);
        pageProvider.loginPage().enterTextIntoInputActions(VALID_LOGIN_UI);
        pageProvider.loginPage().pressTabKey(1);
        pageProvider.loginPage().enterTextIntoInputActions(VALID_PASSWORD_UI);
        pageProvider.loginPage().pressEnterKey();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
    }

    @Test
    @Parameters(method = "parametersForInValidLoginWithParamsTest")
    public void inValidLoginWithParams(String userName, String password) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(userName);
        pageProvider.loginPage().enterTextIntoInputPassword(password);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.loginPage().checkIsInvalidUsernameOrPasswordMessageVisible();
    }

    public Object[][] parametersForInValidLoginWithParamsTest() {
        return new Object[][]{
                {VALID_LOGIN_UI, "invalid_password"},
                {"invalid_username", VALID_PASSWORD_UI},
                {"!@#(*#&*#&^&#&^%#&", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuio"},
                {"тест", "тест"}
        };
    }
}
