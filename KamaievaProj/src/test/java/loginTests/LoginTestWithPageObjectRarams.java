package loginTests;

import baseTest1.BaseTest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObjectRarams extends BaseTest {

    @Test
    @Parameters(method = "parametersForInvalidLoginTest")
    public void invalidLoginTestWithParams(String login, String pass) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
        pageProvider.getLoginPage().enterTextIntoInputPassword(pass);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().checkIsAlertInvalidUsernamePasswordVisible();
    }

    public Object[][] parametersForInvalidLoginTest() {
        return new Object[][]{
                {"qaautoInvalid", VALID_PASSWORD_UI},
                {VALID_LOGIN_UI, "123456"},
                {VALID_LOGIN_UI, "123456QWERTY"},
                {" ", " "},
                {"", ""},
                {"#@%login", "#@%"},
        };
    }
}
