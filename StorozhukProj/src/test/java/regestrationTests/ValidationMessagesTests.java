package regestrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationMessagesTests extends BaseTest {
    @Test
    public void TC_001_checkValidationMessages() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField("taras");
        pageProvider.loginPage().enterTextIntoRegistrationEmailField("tr");
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField("tr");
        pageProvider.loginPage().checkErrorMessages(
                "You must provide a valid email address.;Password must be at least 12 characters.");
    }
}
