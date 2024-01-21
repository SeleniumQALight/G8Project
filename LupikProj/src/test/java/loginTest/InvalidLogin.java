package loginTest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.Util;
import org.junit.Assert;
import org.junit.Test;
import baseTest.BaseTest;
import org.junit.runner.RunWith;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

@RunWith(JUnitParamsRunner.class)
public class InvalidLogin extends BaseTest {
    final String INVALID_LOGIN_UI = "Invalid" + VALID_LOGIN_UI + Util.getDateAndTimeFormatted();
    final String INVALID_PASSWORD_UI = "Invalid" + VALID_PASSWORD_UI + Util.getDateAndTimeFormatted();
    final String ERROR_MESSAGE = "Invalid username/password.";

    @Parameters(method = "parameterForValidationMessageTests")
    @Test
    public void validationMessageForInvalidLogin(String login, String password, String expectedMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(login);
        pageProvider.loginPage().enterTextInToInputPassword(password);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.loginPage().isButtonSignInVisible());

        pageProvider.loginPage().checkErrorsMessages(expectedMessage);


    }


    public Object[][] parameterForValidationMessageTests() {
        return new Object[][]{
                {VALID_LOGIN_UI, INVALID_PASSWORD_UI, ERROR_MESSAGE},
                {INVALID_LOGIN_UI, VALID_PASSWORD_UI, ERROR_MESSAGE},
                {INVALID_LOGIN_UI, INVALID_PASSWORD_UI, ERROR_MESSAGE}


        };
    }

}
