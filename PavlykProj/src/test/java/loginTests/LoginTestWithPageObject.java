package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ConfigProvider;
import libs.ExcelDriver;
import libs.Util;
import org.example.util.ExtraOptions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        Util.waitABit(15);

        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.homePage().getHeader().checkIsButtonCreatePostVisible();
        pageProvider.homePage().getHeader().checkIsMyProfileButtonVisible();
        pageProvider.homePage().getHeader().checkIsUserNameVisible();
        pageProvider.homePage().getHeader().checkTextInUsername(VALID_LOGIN_UI);
        pageProvider.loginPage().checkIsInputLoginNotVisible();
        pageProvider.loginPage().checkIsInputPasswordNotVisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI + ExtraOptions.getFormattedDate());
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button 'Sign In' is visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Warning message Invalid username/password is visible", pageProvider.loginPage().isWarningMessageVisible());
    }

    @Test
    public void validLoginWithExel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSignIn();

        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.homePage().getHeader().checkIsButtonCreatePostVisible();
        pageProvider.homePage().getHeader().checkIsMyProfileButtonVisible();
        pageProvider.homePage().getHeader().checkIsUserNameVisible();
        pageProvider.homePage().getHeader().checkTextInUsername(dataForValidLogin.get("login"));
        pageProvider.loginPage().checkIsInputLoginNotVisible();
        pageProvider.loginPage().checkIsInputPasswordNotVisible();
    }
}
