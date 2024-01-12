package registerFormTest;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationMessagesTests extends BaseTest {
    @Test
    public void ValidationMessagesTests() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputUsernameRegister("taras");
        pageProvider.loginPage().enterTextIntoInputEmailRegister("tr");
        pageProvider.loginPage().enterTextIntoInputPasswordRegister("tr");
        pageProvider.loginPage().checkErrorMessages(
                "You must provide a valid email address.;Password must be at least 12 characters.");
    }
}
