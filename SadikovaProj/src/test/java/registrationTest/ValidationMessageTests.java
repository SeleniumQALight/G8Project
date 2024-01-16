package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessageTests extends BaseTest {
    final String ERROR_USERNAME = "Username must be at least 3 characters.",
            ERROR_EMAIL = "You must provide a valid email address.",
            ERROR_PASSWORD = "Password must be at least 12 characters.",
            SEMECOLON = ";";

    @Test
    @Parameters(method ="parametersForValidationMessagesTests")
    public void validationMessageTest(String userName,String email,String password, String expectedMessages) {
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().fillRegistrationForm(userName, email,password);
        pageProvider.mainPage().checkErrorsMessage(expectedMessages);

    }

    public Object[][] parametersForValidationMessagesTests(){
        return new Object[][]{
                {"taras","tr","tr", ERROR_EMAIL + SEMECOLON + ERROR_PASSWORD},
                {"taras", "rtytrrrr@gmail.com","1", ERROR_PASSWORD}
        };
    }

}
