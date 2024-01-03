package signOutTest;

import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class SignOutTest extends baseTest.BaseTest {


    @Test
    public void signOut() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Search is visible", pageProvider.homePage().getHeader().isButtonSearchVisble());
        Assert.assertTrue("Button Chat is visible", pageProvider.homePage().getHeader().isButtonChatVisble());
        Assert.assertTrue("Avatar is visible", pageProvider.homePage().getHeader().isAvatarVisible());
        Assert.assertTrue("Button Create Post is visible", pageProvider.homePage().getHeader().isButtonCreatePostVisble());
        Assert.assertTrue("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutVisble());
        Assert.assertFalse("Button SignIn is not visible", pageProvider.loginPage().isButtonSignInVisble());
        Assert.assertFalse("Field for input login is not visible", pageProvider.loginPage().isInputLoginVisible());
        Assert.assertFalse("Field for input password is not visible", pageProvider.loginPage().isInputPasswordVisible());

        pageProvider.homePage().getHeader().clickOnButtonSignOut();

        Assert.assertFalse("Button Search is not visible", pageProvider.homePage().getHeader().isButtonSearchVisble());
        Assert.assertFalse("Button Chat is not visible", pageProvider.homePage().getHeader().isButtonChatVisble());
        Assert.assertFalse("Avatar is not visible", pageProvider.homePage().getHeader().isAvatarVisible());
        Assert.assertFalse("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisble());
        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisble());
        Assert.assertTrue("Field for input login is visible", pageProvider.loginPage().isInputLoginVisible());
        Assert.assertTrue("Field for input password is visible", pageProvider.loginPage().isInputPasswordVisible());
        Assert.assertTrue("Button SignIn is visible", pageProvider.loginPage().isButtonSignInVisble());

    }
}
