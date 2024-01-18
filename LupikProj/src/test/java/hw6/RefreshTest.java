package hw6;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class RefreshTest extends BaseTest {

    @Test
    public void refreshTest() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.homePage().refreshPage();
        pageProvider.loginPage().clickOnButtonSignIn();

        pageProvider.homePage().getHeader().checkIsButtonSihOutIsNotVisible();
    }



}
