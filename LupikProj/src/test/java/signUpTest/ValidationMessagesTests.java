package signUpTest;

import baseTest.BaseTest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

   @RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTests extends BaseTest {
    final String ERROR_USERNAME = "Username must be at least 3 characters.";

    final String ERROR_USERNAME_CYRILLIC = "Username can only contain letters and numbers.";

    final String ERROR_USERNAME_BIG = "Username cannot exceed 30 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";

    final String ERROR_PASSWORD_BIG = "Password cannot exceed 50 characters.";
    final String SEMICOLON = ";";

    @Test
       @Parameters(method = "parameterForValidationMessageTests")
       public void validationMessagesTest( String userName, String email,String password, String expectedMessage) {
              pageProvider.loginPage().openLoginPage();
              pageProvider.loginPage().enterTextIntoRegisterLogin(userName);
              pageProvider.loginPage().enterTextIntoRegisterEmail(email);
              pageProvider.loginPage().enterTextIntoRegisterPassword(password);
            pageProvider.loginPage().checkErrorMessages(expectedMessage);


    }

    public Object[][] parameterForValidationMessageTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr@tr.com", "tr", ERROR_PASSWORD},
                {"тарас","tr@tr.com" ,"tr",ERROR_USERNAME_CYRILLIC + SEMICOLON + ERROR_PASSWORD},
                {"t".repeat(31),"tr@tr.com","t".repeat(51),ERROR_USERNAME_BIG + SEMICOLON + ERROR_PASSWORD_BIG}

                };
    }
}
