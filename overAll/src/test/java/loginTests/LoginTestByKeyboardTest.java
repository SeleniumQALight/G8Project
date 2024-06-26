package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginTestByKeyboardTest extends BaseTest {
    @Test
    public void validLoginUsingKeyboard() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().pressTabKey();
        pageProvider.loginPage().pressTabKey();
        pageProvider.loginPage().enterTextIntoInputActions(VALID_LOGIN_UI);
        pageProvider.loginPage().pressTabKey();
        pageProvider.loginPage().enterTextIntoInputActions(VALID_PASSWORD_UI);
        pageProvider.loginPage().pressEnterKey();

        Assert.assertTrue("Button SignOut is not visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());

    }

    @Test
    public void validLoginCheckNewTab() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage();
        pageProvider.loginPage().openNewTabInBrowser();
        pageProvider.loginPage().switchToTabInBrowser(1);
        pageProvider.loginPage().openLoginPage();
        Assert.assertTrue("Button SignOut is not visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());

    }


}
