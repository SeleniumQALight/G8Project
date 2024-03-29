package LoginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static data.TestData.*;
@Epic("Allure examples")
@Feature("Junit 4 support")
@RunWith(JUnitParamsRunner.class)
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
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .checkIsButtonSignOutVisible()
                .checkIsButtonCreateNewPostVisible()
                .checkIsLinkMyProfileVisible()
                .checkIsSpanUserNameVisible()
                .redirectOnLoginPage()
                .checkIsInputUsernameUnvisible()
                .checkIsInputPasswordUnvisible();

    }

    @Test
    public void validLoginWithFewTabs() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .checkIsButtonSignOutVisible()
                .openLoginPageInNewTab()
                .checkIsButtonSignOutVisible()
                .openLoginPageInNewTab()
                .switchBetweenTab(1)
                .checkIsRedirectToHomePage()
                .checkIsButtonSignOutVisible()
                .switchBetweenTab(3)
                .closeTab()
                .switchBetweenTab(1)
                .checkIsButtonSignOutVisible();

    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(INVALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPass(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is displayed", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Invalid Login massage is absent", pageProvider.loginPage().isMessageFailLogin());
    }

    @Test
    @Parameters(method = "parametersForInvalidLoginWithParams")
    public void invalidLoginWithParams(String login, String password) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(login);
        pageProvider.loginPage().enterTextIntoInputPass(password);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is displayed", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Invalid Login massage is absent", pageProvider.loginPage().isMessageFailLogin());
    }

    public Object[][] parametersForInvalidLoginWithParams(){
        return new Object[][]{
            {"invalidUsername", "qwerty"},
            {"qaauto", "qazxcvbnnm"},
            {"aqauto", "123456qwerty"}
        };
    }

    @Test
    public void invalidLoginWithRefresh() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPass(VALID_PASSWORD_UI);
        pageProvider.loginPage().refreshPage();
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is displayed", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Invalid Login massage is absent", pageProvider.loginPage().isMessageFailLogin());
    }

    @Test
    public void loginValidation() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterIntoUsernameRegistration("tr")
                .enterIntoEmailRegistration("tr")
                .enterIntoPasswordRegistration("tr")
                .clickOnButtonSignup()
                .checkIsWarningUsernameRegistrationVisible()
                .checkIsWarningEmailRegistrationVisible()
                .checkIsWarningPasswordRegistrationVisible();
    }

    @Test
    public void loginValidationWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextIntoInputPass(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSignIn();

Assert.assertTrue("Button SignOut is not displayed",
        pageProvider.homePage().getHeader().isButtonSignOutVisible());



    }
}
