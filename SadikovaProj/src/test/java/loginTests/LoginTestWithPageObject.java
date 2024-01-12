package loginTests;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.mainPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.mainPage().clickOnButtonSingIn();
        pageProvider.headerElement().checkSignOutButtonIsVisible();
        pageProvider.headerElement().checkCreatePostButtonIsVisible();
        pageProvider.headerElement().checkUserNameIsVisible();
        pageProvider.headerElement().checkMyProfileIconIsVisible();
        pageProvider.mainPage().checkLoginFieldNotVisible();
        pageProvider.mainPage().checkPasswordFieldNotVisible();
        pageProvider.mainPage().checkSignInButtonIsNotVisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().enterTextIntoInputLogin(WRONG_LOGIN);
        pageProvider.mainPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.mainPage().clickOnButtonSingIn();
        pageProvider.mainPage().checkSignInButtonIsVisible();
        pageProvider.mainPage().checkSignInButtonIsVisible();
        Assert.assertTrue("Warning button Invalid username/password is visible", pageProvider.mainPage().isWarningMessageVisible());

    }

    @Test
    public void checkLoginInDifferentWindow() throws InterruptedException {
        pageProvider.mainPage().loginToProfile(VALID_LOGIN_UI, VALID_PASSWORD);
        pageProvider.headerElement().isButtonSignOutVisible();
        pageProvider.homePage().openWindow();
        pageProvider.homePage().switchTab(1);
        pageProvider.homePage().openHomePage();
        pageProvider.headerElement().checkSignOutButtonIsVisible();
        pageProvider.homePage().switchTab(0);
        pageProvider.headerElement().checkSignOutButtonIsVisible();
        pageProvider.homePage().closeTab(1);
        pageProvider.homePage().switchTab(0);
        pageProvider.homePage().refreshPage();
        pageProvider.headerElement().checkSignOutButtonIsVisible();

    }

    @Test
    public void fillInputAndRefreshPage(){
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().fillLoginForm(TestData.VALID_LOGIN_UI,TestData.VALID_PASSWORD);
        pageProvider.mainPage().refreshPage();
        pageProvider.mainPage().clickOnButtonSignIn();
        pageProvider.headerElement().checkSignOutButtonIsNotVisible();

    }

    @Test
    public void loginWithTab() {
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().pressTabKey();
        pageProvider.mainPage().pressTabKey();
        pageProvider.mainPage().enterLoginFieldWithKeys(VALID_LOGIN_UI)
                .pressTabKey();
        pageProvider.mainPage().enterPasswordFieldWithKeys(VALID_PASSWORD)
        .pressTabKey();
        pageProvider.mainPage().pressTabKey();
        pageProvider.mainPage().pressSignInButton();
        pageProvider.headerElement().checkSignOutButtonIsVisible();





    }

}
