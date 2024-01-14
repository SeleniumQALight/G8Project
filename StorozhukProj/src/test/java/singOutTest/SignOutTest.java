package singOutTest;

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
        Assert.assertTrue("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("Button My Profile is not visible", pageProvider.homePage().getHeader().isButtonMyProfileVisible());
        Assert.assertTrue("Button Comment is not visible", pageProvider.homePage().getHeader().isButtonCommentVisible());
        Assert.assertTrue("Button User Name is not visible", pageProvider.homePage().getHeader().isButtonUserNameVisible());
        pageProvider.loginPage().isInputUserNameNotVisible();
        pageProvider.loginPage().isInputPasswordNotVisible();
        pageProvider.loginPage().isButtonSignInNotVisible();
        pageProvider.homePage().getHeader().clickOnButtonSignOut().checkIsRedirectToLoginPage();
        Assert.assertTrue("Button Sign In is not visible", pageProvider.loginPage().isButtonSignInVisible());
        pageProvider.homePage().getHeader().isButtonSearchNotVisible();
        pageProvider.homePage().getHeader().isButtonCreatePostNotVisible();
        pageProvider.homePage().getHeader().isButtonMyProfileNotVisible();
        pageProvider.homePage().getHeader().isButtonCommentNotVisible();
        pageProvider.homePage().getHeader().isButtonUserNameNotVisible();
        pageProvider.homePage().getHeader().isButtonSignOutNotVisible();
        Assert.assertTrue("Input Username is not visible", pageProvider.loginPage().isInputUserNameVisible());
        Assert.assertTrue("Input Password is not visible", pageProvider.loginPage().isInputPasswordVisible());



    }

}
