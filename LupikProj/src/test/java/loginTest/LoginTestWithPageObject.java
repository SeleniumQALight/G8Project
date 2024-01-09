package loginTest;

import baseTest.BaseTest;
import com.beust.ah.A;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("My profile icon is not visible", pageProvider.homePage().getHeader().isButtonMyProfileVisible());
        pageProvider.homePage().getHeader().checkUserNameIsPresent(VALID_LOGIN_UI);

        Assert.assertFalse("Field Username is visible", pageProvider.loginPage().isInputLoginFieldVisible());
        Assert.assertFalse("Field Password is visible", pageProvider.loginPage().isInputPasswordFieldVisible());


    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qaauto1");
        pageProvider.loginPage().enterTextInToInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Text 'Invalid username/password' is not visible", pageProvider.loginPage().isTextInvalidLoginOrPasswordDisplayed());
        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());

    }
}