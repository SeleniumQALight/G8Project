package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class LoginTestWithRefresh extends BaseTest {


    @Test
    public void login() {
        pageProvider.loginPage().openLoginPage(); // 1. Open login page
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI); // 2. Enter 'qaauto' login into input Login
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI); // 3. Enter '123456qwerty' password into input Password
        pageProvider.loginPage().refreshPage(); // 4. Refresh page
        pageProvider.loginPage().clickOnButtonSignIn(); // 5. Click on button SingIn
        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutVisble()); // 6. Check that button SignOut is not visible
    }
}