package registrationFormTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationFormTest extends BaseTest {
    @Test
    public void checkIsValidationMessagesArePresent(){
        pageProvider.loginPage()
                .openLoginPage()
                .getRegistrationForm()
                .fillRegistrationFormWithInvalidCreds()
                .checkIsValidationMessageDisplayedForUsernameField()
                .checkIsValidationMessageDisplayedForEmailField()
                .checkIsValidationMessageDisplayedForPasswordField();
    }
}