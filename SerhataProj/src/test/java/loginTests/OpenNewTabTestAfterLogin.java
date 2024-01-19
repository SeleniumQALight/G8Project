package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class OpenNewTabTestAfterLogin extends BaseTest {
    @Test
    public void openNewTabAfterLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
        pageProvider.loginPage().openNewTabInBrowser();
        pageProvider.loginPage().switchToTabInBrowser(1);
        pageProvider.loginPage().openLoginPage();
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
        pageProvider.loginPage().switchToTabInBrowser(0);
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
        pageProvider.loginPage().closeTabAndSwitchToMainPage();
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
    }

    @Test
    public void checkRefreshingDataInTheLoginPage() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().refreshPage();
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
    }

    @Test
    public void checkLogOutInTwoTabs() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
        pageProvider.loginPage().openNewTabInBrowser();
        pageProvider.loginPage().switchToTabInBrowser(1);
        pageProvider.loginPage().openLoginPage();
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
        pageProvider.loginPage().switchToTabInBrowser(0);
        pageProvider.homePage().getHeader().clickOnButtonSignOut();
        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
        pageProvider.loginPage().switchToTabInBrowser(1);
        pageProvider.loginPage().refreshPage();
        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
    }
}
