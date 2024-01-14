package SignUpTests;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationMessagesTests extends BaseTest {
    @Test
    public void validationMessagesTest() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField("trsjd");
        pageProvider.loginPage().enterTextIntoRegistrationEmailField("tr");
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField("tr");
        pageProvider.loginPage().checkErrorMessages("You must provide a valid email address.;Password must be at least 12 characters.");
    }

    @Test
    public void validationMessagesTestUsingKeyboard() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().pressTabKey(5);
        pageProvider.loginPage().enterTextIntoInputActions("tr");
        pageProvider.loginPage().pressTabKey(1);
        pageProvider.loginPage().enterTextIntoInputActions("tr");
        pageProvider.loginPage().pressTabKey(1);
        pageProvider.loginPage().enterTextIntoInputActions("tr");
        pageProvider.loginPage().pressEnterKey();
        pageProvider.loginPage().checkErrorMessages("Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.");
    }
}
