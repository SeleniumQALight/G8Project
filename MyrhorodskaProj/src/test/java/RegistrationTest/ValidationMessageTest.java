package RegistrationTest;

import org.junit.Ignore;
import org.junit.Test;
import BaseTest.BaseTest;

public class ValidationMessageTest extends BaseTest{
    @Test
    public void validationMessageTest() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().inputTextIntoRegistrationLogin("nataliia");
        pageProvider.loginPage().inputTextIntoRegistrationPassword("tr");
        pageProvider.loginPage().inputTextIntoRegistrationEmail("tr");
        pageProvider.loginPage().checkErrorMessages(
                "You must provide a valid email address.;Password must be at least 12 characters.");
    }
}
