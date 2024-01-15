package registrationTests;

import baseTest1.BaseTest;
import org.junit.Test;

public class ValidationMessagesTests extends BaseTest {
    @Test
    public void validationMessagesTests() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputUsernameRegister("taras");
        pageProvider.getLoginPage().enterTextIntoInputEmailRegister("tr");
        pageProvider.getLoginPage().enterTextIntoInputPasswordRegister("tr");
        pageProvider.getLoginPage().checkErrorsMessages(
                "You must provide a valid email address.;Password must be at least 12 characters.");
    }
}
