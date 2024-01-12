package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationMessagesTests extends BaseTest {
    @Test
    public void validationMessagesTests() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextRegistrationUserNameField("taras");
        pageProvider.loginPage().enterTextRegistrationEmailField("tr");
        pageProvider.loginPage().enterTextRegistrationPasswordField("tr");
        pageProvider.loginPage().checkErrorsMessages(
                "You must provide a valid email address.;Password must be at least 12 characters.");
    }
}

