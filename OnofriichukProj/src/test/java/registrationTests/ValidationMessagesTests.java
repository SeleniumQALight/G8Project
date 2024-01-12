package registrationTests;

import baseTest.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

public class ValidationMessagesTests extends BaseTest {
    @Test
    @Ignore
    public void validationMessagesTest() {
        pageProvider.loginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField("taras");
        pageProvider.loginPage().enterTextIntoRegistrationEmailField("tr");
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField("tr");
        pageProvider.loginPage().checkErrorsMessages(
                "You must provide a valid email address.; Password must be at least 12 characters.");
} }
