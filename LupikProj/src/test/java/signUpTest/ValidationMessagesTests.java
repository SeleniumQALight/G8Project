package signUpTest;

import baseTest.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

public class ValidationMessagesTests extends BaseTest {

    @Test
    @Ignore
    public void validationMessagesTest() {
              pageProvider.loginPage().openLoginPage();
              pageProvider.loginPage().enterTextIntoRegisterLogin("tr");
              pageProvider.loginPage().enterTextIntoRegisterEmail("tr");
              pageProvider.loginPage().enterTextIntoRegisterPassword("tr");
             pageProvider.loginPage().clickOnButtonSignUp();


    }
}
