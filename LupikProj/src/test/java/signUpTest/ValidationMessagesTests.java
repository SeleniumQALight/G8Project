package signUpTest;

import baseTest.BaseTest;

import org.junit.Test;

public class ValidationMessagesTests extends BaseTest {

    @Test

    public void validationMessagesTest() {
              pageProvider.loginPage().openLoginPage();
              pageProvider.loginPage().enterTextIntoRegisterLogin("taras");
              pageProvider.loginPage().enterTextIntoRegisterEmail("tr");
              pageProvider.loginPage().enterTextIntoRegisterPassword("tr");
            pageProvider.loginPage().checkErrorMessages("You must provide a valid email address.;Password must be at least 12 characters.");


    }
}
