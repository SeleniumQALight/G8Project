package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationMessagesTest extends BaseTest {
    @Test
    public void validationMessagesTest() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationInputLogin("123");
        pageProvider.getLoginPage().enterTextIntoRegistrationInputEmail("123");
        pageProvider.getLoginPage().enterTextIntoRegistrationInputPassword("123");
    }
}
