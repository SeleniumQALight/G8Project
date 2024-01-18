package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestInvalidParams extends BaseTest {
    final String ERROR_MESSAGE = "Invalid username/password.";


    @Test
    @Parameters(method = "parametersForInvalidationLogin")
    public void invalidLogin(String login, String password, String errorMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(login);
        pageProvider.loginPage().enterTextIntoInputPassword(password);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.loginPage().checkisErrorMessageVisibleForInvalidLogin(errorMessage);
    }

    private Object[][] parametersForInvalidationLogin() {
        return new Object[][]{
                {"1234", "1234", ERROR_MESSAGE},
                {"asdasdas", "asdasdas", ERROR_MESSAGE},
                {"123gfdgdfsgds4", "12fgfdgfsdgfd34", ERROR_MESSAGE},
                {"123523sdfssafd234", "123dsfgasdfasdfas4", ERROR_MESSAGE},
                };
    }

}


