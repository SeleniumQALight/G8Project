package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginTestByKeyboardTest extends BaseTest {

    @Test
    public void validLoginUsingKeyboard() {
        pageProvider.loginPage().openLoginPage();                                       // Open login page
        pageProvider.loginPage().pressTabKey(2);                                        // Press Tab 2 times
        pageProvider.loginPage().enterTextIntoInputActions(VALID_LOGIN_UI);             // Enter login into input Login (введення без елемента, використовуючи класс Actions)
        pageProvider.loginPage().pressTabKey(1);                                        // Press Tab key 1 time
        pageProvider.loginPage().enterTextIntoInputActions(VALID_PASSWORD_UI);          // Enter password into input Password
        pageProvider.loginPage().pressEnterKey();                                       // Press Enter key
        pageProvider.loginPage().getHeader().checkIsButtonSignOutVisible();             // Check that button SignOut is visible
    }
}