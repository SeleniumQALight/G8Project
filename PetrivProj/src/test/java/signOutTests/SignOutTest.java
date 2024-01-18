package signOutTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void signOut() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage();
        Assert.assertTrue("Button Sign Out is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button Search is not visible", pageProvider.homePage().getHeader().isButtonSearchVisible());
        Assert.assertTrue("Button Chat is not visible", pageProvider.homePage().getHeader().isButtonChatVisible());
        Assert.assertTrue("Button MyProfile is not visible", pageProvider.homePage().getHeader().isButtonMyProfileVisible());
        Assert.assertTrue("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
        pageProvider.loginPage().isInputUserNameNotVisible();
        pageProvider.loginPage().isInputPasswordNotVisible();
        pageProvider.loginPage().isButtonSignInNotVisible();
        pageProvider.homePage().getHeader().clickOnButtonSignOut().checkIsRedirectToLoginPage();
        Assert.assertTrue("Button Sign In is not visible", pageProvider.loginPage().isButtonSignInVisible());
        pageProvider.homePage().getHeader().isButtonSearchNotVisible();
        pageProvider.homePage().getHeader().isButtonChatNotVisible();
        pageProvider.homePage().getHeader().isButtonMyProfileNotVisible();
        pageProvider.homePage().getHeader().isButtonCreatePostNotVisible();
        pageProvider.homePage().getHeader().checkIsButtonSignOutNotVisible();
        Assert.assertTrue("Input Username is not visible", pageProvider.loginPage().isInputUserNameVisible());
        Assert.assertTrue("Input Password is not visible", pageProvider.loginPage().isInputPasswordVisible());
    }

    @Test
    public void checkSignedOutUserSessionInAllTabs() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage();
        pageProvider.homePage().openNewTabInBrowser();
        pageProvider.homePage().switchToTabInBrowser(1);
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().getHeader().isButtonSignOutVisible();
        pageProvider.loginPage().switchToTabInBrowser(0);
        pageProvider.homePage().getHeader().clickOnButtonSignOut();
        pageProvider.loginPage().getHeader().checkIsButtonSignOutNotVisible();
        pageProvider.loginPage().switchToTabInBrowser(1);
        pageProvider.loginPage().refreshPage();
        pageProvider.loginPage().getHeader().checkIsButtonSignOutNotVisible();
    }
}
