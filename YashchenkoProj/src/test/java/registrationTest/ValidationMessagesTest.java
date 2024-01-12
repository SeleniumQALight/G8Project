package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationMessagesTest extends BaseTest {
    @Test
    public void validationMessagesTest() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationInputLogin("Tim");
        pageProvider.getLoginPage().enterTextIntoRegistrationInputEmail("123");
        pageProvider.getLoginPage().enterTextIntoRegistrationInputPassword("123");
        pageProvider.getLoginPage().checkErrorMessages("Password must be at least 12 characters.;You must provide a valid email address.");
    }
}
