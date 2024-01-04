package registrationFormTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationFormTest extends BaseTest {
    @Test
    public void checkIsValidationMessagesArePresent(){
        pageProvider.loginPage().openLoginPage();
        pageProvider.registrationPage().fillRegistrationFormWithInvalidCreds()
                .checkIsValidationMessageDisplayedForUsernameField()
                .checkIsValidationMessageDisplayedForEmailField()
                .checkIsValidationMessageDisplayedForPasswordField();
    }
}