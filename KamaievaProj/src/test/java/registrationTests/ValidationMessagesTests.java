package registrationTests;

import baseTest1.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

public class ValidationMessagesTests extends BaseTest {
    @Test
    @Ignore
    public void validationMessagesTests() {
        pageProvider.getLoginPage().openLoginPage();
//        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField("test");
//        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField("trtr");
//        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField("123456");
//        pageProvider.getLoginPage().checkErrorsMessages("You must provide a valid email address.;Password must be at least 12 characters.");
    }
}
