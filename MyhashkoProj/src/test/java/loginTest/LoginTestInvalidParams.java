package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestInvalidParams extends BaseTest {
    final String expectedErrorMessage = "Invalid username/password.";
    final String SEMICOLON = ";";


    @Test
    @Parameters(method = "parametersForInvalidationLogin")
    public void invalidLogin(String login, String password) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(login);
        pageProvider.loginPage().enterTextIntoInputPassword(password);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.loginPage().isErrorMessageVisible();
    }

    private Object[][] parametersForInvalidationLogin(){
        return new Object[][]{
                {"1234", "1234"},
                {"asdasdas", "asdasdas"},
                {"123gfdgdfsgds4", "12fgfdgfsdgfd34"},
                {"123523sdfssafd234", "123dsfgasdfasdfas4"},

        };
    }
}

