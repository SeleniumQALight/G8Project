package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelDriver;
import libs.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static data.TestData.DEFAULT_VALID_LOGIN_UI;
import static data.TestData.DEFAULT_VALID_PASSWORD_UI;

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
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(DEFAULT_VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(DEFAULT_VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonMyProfileIconVisible();
        pageProvider.homePage().getHeader().checkIsProfileNameVisible();
        pageProvider.homePage().getHeader().checkIsCreatePostButtonVisible();
        pageProvider.getLoginPage().checkIsLoginInputNotVisible();
        pageProvider.getLoginPage().checkIsPasswordInputNotVisible();
        Util.waitABit(15);
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();

    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonMyProfileIconVisible();
        pageProvider.homePage().getHeader().checkIsProfileNameVisible();
        pageProvider.homePage().getHeader().checkIsCreatePostButtonVisible();
        pageProvider.getLoginPage().checkIsLoginInputNotVisible();
        pageProvider.getLoginPage().checkIsPasswordInputNotVisible();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();

    }
//    Example
//    @Test
//    public void invalidLogin() {
//        pageProvider.getLoginPage().openLoginPage();
//        pageProvider.getLoginPage().enterTextIntoInputLogin("qaavto");
//        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
//        pageProvider.getLoginPage().clickOnButtonSignIn();
//
//        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
//        Assert.assertTrue("Button SignIn is visible", pageProvider.getLoginPage().isButtonSignInVisible());
//        Assert.assertTrue("Alert is visible", pageProvider.getLoginPage().isInvalidUserNamePasswordAlertVisible());
//
//    }

    @Test
    @Parameters(method = "ParametersForInvalidLoginTest")
    public void invalidLoginWithParams(String login, String password){
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(login)
                .enterTextIntoInputPassword(password)
                .clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonSignOutNotVisible();
        pageProvider.getLoginPage().checkIsButtonSignInVisible()
                .checkIsInvalidUserNamePasswordAlertVisible();
    }

    public Object[][] ParametersForInvalidLoginTest(){
        return new Object[][]{
                {"qaavto", "123456qwerty"},
                {"qaauto", "12345qwerty"},
                {" ", " "}
        };
    }

    @Test
    public void signOut() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCreds()
                .getHeader().checkIsButtonSignOutVisible()
                .checkIsProfileNameVisible()
                .checkIsButtonMyProfileIconVisible()
                .checkIsCreatePostButtonVisible()
                .checkIsButtonChatVisible()
                .checkIsButtonSearchVisible();
        pageProvider.getLoginPage().checkIsLoginInputNotVisible()
                .checkIsPasswordInputNotVisible()
                .checkIsButtonSignInNotVisible();
        pageProvider.homePage().getHeader().clickOnButtonSignOut()
                .checkIsInputLoginVisible()
                .checkIsInputPasswordVisible()
                .checkIsButtonSignInVisible();
        pageProvider.homePage().getHeader().checkIsButtonSignOutNotVisible()
                .checkIsProfileNameNotVisible()
                .checkIsButtonMyProfileIconNotVisible()
                .checkIsCreatePostButtonNotVisible()
                .checkIsButtonChatNotVisible()
                .checkIsButtonSearchNotVisible();
    }

    @Test
    public void validationMessages() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationInputLogin("ty");
        pageProvider.getLoginPage().enterTextIntoRegistrationInputPassword("ty");
        pageProvider.getLoginPage().enterTextIntoRegistrationInputEmail("ty");
        pageProvider.getLoginPage().clickOnButtonSignUp();
        pageProvider.getLoginPage().checkIsValidationMessageForRegistrationInputLoginVisible()
                .checkTextInRegistrationInputLogin("Username must be at least 3 characters.")
                .checkIsValidationMessageForRegistrationInputEmailVisible()
                .checkTextInRegistrationInputEmail("You must provide a valid email address.")
                .checkIsValidationMessageForRegistrationInputPasswordVisible()
                .checkTextInRegistrationInputPassword("Password must be at least 12 characters.");
    }
}
