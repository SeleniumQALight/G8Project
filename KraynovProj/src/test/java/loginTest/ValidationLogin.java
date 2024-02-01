package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidationLogin extends BaseTest {

    final String ERROR_MESSAGE = "Invalid username/password.";

    @Test
    @Parameters(method = "parametersForValidationMessages")
    public void validationMessageTest(String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().fillLoginForm(email, password)
                .clickOnButtonSignIn();
        pageProvider.loginPage().checkErrorMessageInLoginForm(expectedMessages);

    }

    public Object[][] parametersForValidationMessages() {
        return new Object[][]{
                {"anton@gmail.com", "tr", ERROR_MESSAGE},
                {"anton", "", ERROR_MESSAGE},
                {"", "anton123", ERROR_MESSAGE},
                {"", "", ERROR_MESSAGE},
        };
    }
}