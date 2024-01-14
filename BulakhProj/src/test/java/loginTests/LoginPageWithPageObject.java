package loginTests;

import baseTast.BaseTest;
import libs.Util;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class LoginPageWithPageObject extends BaseTest {
    WebDriver webDriver;

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
                pageProvider.homePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("Button My Profile is not visible",pageProvider.homePage().getHeader().isButtonMyProfileVisible());
        Assert.assertTrue("User name is not visible",pageProvider.homePage().getHeader().isUserNameVisible());

        // check input Login and Password are not visible
        Assert.assertFalse("Input Login is visible",pageProvider.loginPage().isInputLoginVisible());
        Assert.assertFalse("Input Password is visible",pageProvider.loginPage().isInputPasswordVisible());

    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("invalid_user");
        pageProvider.loginPage().enterTextInToInputPassword("invalid_password");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button SignIn is visible", pageProvider.loginPage().isButtonSingInVisible());
        Assert.assertTrue("Alert Danger is not visible", pageProvider.loginPage().isInvalidLoginMessageDisplayed());

    }

    @Test
    public void loginAndVerifyInNewTab() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());


        Util.openNewTab(webDriver);
        Util.switchToNextTab(webDriver);

        pageProvider.homePage().openHomePageAndLoginIfNeeded();

        Assert.assertTrue("Button SignOut is not visible in the new tab", pageProvider.homePage().getHeader().isButtonSignOutVisible());

        Util.switchToMainTab(webDriver);

        Assert.assertTrue("Button SignOut is not visible in the main tab", pageProvider.homePage().getHeader().isButtonSignOutVisible());

        Util.closeNewTab(webDriver);

        Assert.assertTrue("Button SignOut is not visible after closing the new tab", pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

    @Test
    public void LoginPageRefreshTest(){
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnRefreshPage();
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());


    }




}
