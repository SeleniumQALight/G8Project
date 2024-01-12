package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationMessegesTests extends BaseTest{
    @Test
    public void ValidationMessegesTests() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUsernameField("Tr");
        pageProvider.loginPage().enterTextIntoRegistrationEmailField("tr");
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField("tr");
        pageProvider.loginPage().checkErrorMessage(
                "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.");
    }
}
