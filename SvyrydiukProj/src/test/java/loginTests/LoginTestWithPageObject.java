package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
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


public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        pageProvider.loginPage().checkUsernameFieldNotVisible();
        pageProvider.loginPage().checkPasswordFieldNotVisible();
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Username is not visible", pageProvider.homePage().getHeader().isUsernameVisible());
        pageProvider.homePage().getHeader().checkTextInUsername(VALID_LOGIN_UI);
        Assert.assertTrue("Profile image is not visible", pageProvider.homePage().getHeader().isProfileButtonVisible());
        Assert.assertTrue("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
    }


    @Test
    public void validLoginWhitExcel() throws IOException {
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
        pageProvider.loginPage().enterTextIntoInputLogin("NotValidLogin");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Warning message is visible", pageProvider.loginPage().isWarningMessageVisible());
        Assert.assertTrue("Button Sign In is visible", pageProvider.loginPage().isButtonSignInVisible());
    }

    @Test
    public void validLoginWithSendKeys() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().pressTabKey(2);
        pageProvider.loginPage().enterTextIntoInputWithActions(VALID_LOGIN_UI);
        pageProvider.loginPage().pressTabKey(1);
        pageProvider.loginPage().enterTextIntoInputWithActions(VALID_PASSWORD_UI);
        pageProvider.loginPage().pressEnterKey();
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }


    @Test
    public void checkLogoutFromAllPages() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred();
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        pageProvider.loginPage().redirectToNewTab();
        pageProvider.loginPage().openLoginPage();
        pageProvider.homePage().checkIsRedirectToHomePage();
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        pageProvider.loginPage().switchToTabByIndex(0);
        pageProvider.homePage().getHeader().clickOnButtonSignOut();
        Util.waitABit(3);
        Assert.assertTrue("Button Sign In is visible", pageProvider.loginPage().isButtonSignInVisible());
        pageProvider.loginPage().checkUsernameFieldVisible();
        pageProvider.loginPage().switchToTabByIndex(1);
        pageProvider.loginPage().refreshPage();
        Assert.assertTrue("Button Sign In is visible", pageProvider.loginPage().isButtonSignInVisible());
        pageProvider.loginPage().checkUsernameFieldVisible();
    }

    @Test
    public void checkClearingEnteredDataInLoginAndPasswordFieldsAfterRefresh() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().pressTabKey(2);
        pageProvider.loginPage().enterTextIntoInputWithActions(VALID_LOGIN_UI);
        pageProvider.loginPage().pressTabKey(1);
        pageProvider.loginPage().enterTextIntoInputWithActions(VALID_PASSWORD_UI);
        pageProvider.loginPage().refreshPage();
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }


}
