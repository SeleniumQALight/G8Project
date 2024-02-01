package loginTests;

import baseTest.BaseTest;

import io.qameta.allure.*;
import libs.ConfigProvider;
import libs.ExcelDriver;
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
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInput(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOutTest is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button CreatePost is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("Button MyProfile is not visible", pageProvider.homePage().getHeader().isButtonMyProfileVisible());
        Assert.assertTrue("Username is not visible", pageProvider.homePage().getHeader().isUserNameVisible());
        pageProvider.homePage().getHeader().checkTextInUserName(VALID_LOGIN_UI);
        Assert.assertFalse("Input Username is visible", pageProvider.loginPage().isInputUserNameVisible());
        Assert.assertFalse("Input Password is visible", pageProvider.loginPage().isInputPasswordVisible());

    }

    @Test
    public void invalidLogin() {

        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInput("quytto");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignIn is not visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertFalse("Button SignOutTest is visible", pageProvider.homePage().isButtonSignOutVisible());
        Assert.assertTrue("Alert is not visible", pageProvider.loginPage().isAlertTextVisible());
    }
    @Test
    public void validLoginWithExel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInput(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOutTest is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

    @Test
    public void checkLoggedUserInNewTab(){
        pageProvider.loginPage().openLoginPage();
        String mainPageHandler = pageProvider.loginPage().getPageHandler();
        pageProvider.loginPage().enterTextIntoInput(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage().checkIsRedirectToHomePage();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        String newTabHandler = pageProvider.openNewTab();
        pageProvider.loginPage().openLoginPage();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.switchToTab(mainPageHandler);
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.switchToTab(newTabHandler);
        pageProvider.closeNewTab();
        pageProvider.switchToTab(mainPageHandler);
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
    }

    @Test
    public void checkToRefreshPage(){
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInput(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.refreshPage();
        pageProvider.loginPage().checkIsFieldLoginEmpty();
        pageProvider.loginPage().checkIsFieldPasswordEmpty();
        pageProvider.loginPage().checkIsButtonSignInVisible();

    }

}
