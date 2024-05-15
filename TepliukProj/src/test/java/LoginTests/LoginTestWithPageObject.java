package LoginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelDriver;
import libs.Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

@RunWith(JUnitParamsRunner.class)

@Epic("Allure examples")
@Feature("Junit 4 support")

public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Category(SmokeTestFilter.class)
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")

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

        Util.waitABit(15);
    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
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
    @Parameters(method = "parametersForInvalidLoginWithParams")
    public void invalidLoginWithParams(String login, String password) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(login);
        pageProvider.loginPage().enterTextIntoInputPassword(password);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.loginPage().checkIsInvalidUsernameOrPasswordMessageVisible();
    }
    public Object[][] parametersForInvalidLoginWithParams() {
        return new Object[][]{
                {VALID_LOGIN_UI, "wrong_password"},
                {"wrong_username", VALID_PASSWORD_UI},
                {"VeryLongLogin_VeryLongLogin_VeryLongLogin", "VeryLongPassword_VeryLongPassword_VeryLongPassword_VeryLongPassword"},
                {"кирилиця", "кирилиця"}
        };
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