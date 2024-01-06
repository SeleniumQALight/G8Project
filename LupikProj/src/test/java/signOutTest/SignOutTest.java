package signOutTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class SignOutTest extends BaseTest {

    @Test
    public void signOutTest() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Search icon is not visible", pageProvider.homePage().getHeader().isSearchIconIsVisible());
        Assert.assertTrue("Chat is not visible", pageProvider.homePage().getHeader().isChatIconVisible());
        Assert.assertTrue("My profile icon is not visible", pageProvider.homePage().getHeader().isButtonAvatarVisible());
        Assert.assertTrue("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());


        Assert.assertTrue("Field Username is visible", pageProvider.loginPage().isInputLoginFieldNotVisible());
        Assert.assertTrue("Field Password is visible", pageProvider.loginPage().isInputPasswordFieldNotVisible());
        Assert.assertTrue("Button Sign It is visible", pageProvider.loginPage().isButtonSignInIsNotVisible());

        pageProvider.homePage().getHeader().clickOnButtonSignOut();

        Assert.assertTrue("Button SignIn is not visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Field Username is not visible", pageProvider.loginPage().isInputLoginFieldVisible());
        Assert.assertTrue("Field Password is not visible", pageProvider.loginPage().isInputPasswordFieldVisible());

        Assert.assertTrue("Search icon  is visible", pageProvider.homePage().getHeader().isSearchIconIsNotVisible());
        Assert.assertTrue("Search icon  is visible", pageProvider.homePage().getHeader().isChatIconNotVisible());
        Assert.assertTrue("Avatar is visible", pageProvider.homePage().getHeader().isButtonAvatarNotVisible());
        Assert.assertTrue("Button Create Post is visible", pageProvider.homePage().getHeader().isButtonCreatePostNotVisible());
        Assert.assertTrue("Button Sign out is visible", pageProvider.homePage().getHeader().isButtonSihOutIsNotVisible());


    }
}
