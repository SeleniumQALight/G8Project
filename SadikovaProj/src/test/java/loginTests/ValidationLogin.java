package loginTests;

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
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().fillLoginForm(email, password)
                .clickOnButtonSignIn();
        pageProvider.mainPage().checkErrorMessageInLoginForm(expectedMessages);

    }

    public Object[][] parametersForValidationMessages() {
        return new Object[][]{
                {"taras@gmail.com", "tr", ERROR_MESSAGE},
                {"taras", "", ERROR_MESSAGE},
                {"", "taras123", ERROR_MESSAGE},
                {"", "", ERROR_MESSAGE},

        };
    }
}
