package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;
public class LoginTestUsingTabAndEnter extends BaseTest {

    @Test
public void login() {
        pageProvider.loginPage().openLoginPage(); // 1. Open login page
        pageProvider.loginPage().pressTabKey(1); // 2. Press Tab key
        pageProvider.loginPage().pressTabKey(1); // 3. Press Tab key
        pageProvider.loginPage().enterTextIntoField(VALID_LOGIN_UI); // 4. Enter login into input Login (введення без елемента, використовуючи класс Actions)
        pageProvider.loginPage().pressTabKey(1); // 5. Press Tab key
        pageProvider.loginPage().enterTextIntoField(VALID_PASSWORD_UI); // 6. Enter password into input Password
        pageProvider.loginPage().pressEnterKey(); // 7. Press Enter key
        pageProvider.homePage().checkIsRedirectToHomePage(); // 8. Check that button SignOut is visible
    }
}