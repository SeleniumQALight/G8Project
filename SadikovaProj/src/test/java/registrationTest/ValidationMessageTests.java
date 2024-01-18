package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.TestData;
import libs.Util;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessageTests extends BaseTest {
    final String ERROR_USERNAME = "Username must be at least 3 characters.",
            ERROR_EMAIL = "You must provide a valid email address.",
            ERROR_PASSWORD = "Password must be at least 12 characters.",
            SEMECOLON = ";",
            PASSWORD_CANNOT_EXCEED_50 = "Password cannot exceed 50 characters.",
            NAME_FIELD_CAN_ONLY_CONTAIN_LETTERS_AND_NUMBERS = "Username can only contain letters and numbers.";

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessageTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().fillRegistrationForm(userName, email, password);
        pageProvider.mainPage().checkErrorsMessage(expectedMessages);

    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_EMAIL + SEMECOLON + ERROR_PASSWORD},
                {"taras", "rtytrrrr@gmail.com", "1", ERROR_PASSWORD},
                {"mariia", "rtytrrrr@gmail.com", "1".repeat(60), PASSWORD_CANNOT_EXCEED_50},
                {"мАРИИЯ", "rtytrrrr@gmail.com", TestData.VALID_PASSWORD, NAME_FIELD_CAN_ONLY_CONTAIN_LETTERS_AND_NUMBERS}
        };
    }

}
