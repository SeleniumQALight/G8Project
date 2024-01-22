package hw6;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class JavaScriptNewTab extends BaseTest {

    @Test
    public void loginTestInNewTab() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());

        pageProvider.loginPage().openLinkInNewTab();
        pageProvider.loginPage().switchToTab(1);
        pageProvider.loginPage().openLoginPage();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());

        pageProvider.loginPage().switchToTab(0);

        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());

        pageProvider.loginPage().closeTab(1);
        pageProvider.loginPage().switchToTab(0);

        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());

    }
}
