package LoginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .checkIsButtonSignOutVisible()
                .checkIsButtonCreateNewPostVisibe()
                .checkIsLinkMyProfileVisible()
                .checkIsSpanUserNameVisible()
                .checkIsInputUsernameUnvisible()
                .checkIsInputPasswordUnvisible();
//        pageProvider.loginPage().openLoginPage();
//        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
//        pageProvider.loginPage().enterTextIntoInputPass(VALID_PASSWORD_UI);
//        pageProvider.loginPage().clickOnButtonSignIn();
//
//        Assert.assertTrue("Button SignOut is not displayed",
//                pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }
    @Test
    public void invalidLogin(){
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(INVALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPass(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is displayed", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Invalid Login massage is absent", pageProvider.loginPage().isMessageFailLogin());
    }
}
