package registratinTests;

import baseTest.BaseTest;
import org.junit.Test;

import static data.TestData.SMALL_INVALID_TEXT;

public class ValidationMessagesTestByKeyboardTest extends BaseTest {

    @Test
    public void validationMessagesTests() {
        pageProvider.loginPage().openLoginPage();                                       // Open login page
        pageProvider.loginPage().pressTabKey(5);                                        // Tabom доклікати до поля User Name в Реєстраційній формі
        pageProvider.loginPage().enterTextIntoInputActions(SMALL_INVALID_TEXT);         // Ввести не валідне значення в поле User Name
        pageProvider.loginPage().pressTabKey(1);                                        // Tabom доклікати до поля Email в Реєстраційній формі
        pageProvider.loginPage().enterTextIntoInputActions(SMALL_INVALID_TEXT);         // Ввести не валідне значення в поле Email
        pageProvider.loginPage().pressTabKey(1);                                        // Tabom доклікати до поля Password в Реєстраційній формі
        pageProvider.loginPage().enterTextIntoInputActions(SMALL_INVALID_TEXT);         // Ввести не валідне значення в поле Email
        pageProvider.loginPage().pressEnterKey();                                       // Натиснути кнопку Enter
        pageProvider.loginPage().checkErrorsMessages("You must provide a valid email address.;Password must be at least 12 characters.;Password must be at least 12 characters.");             // Перевірити що відобразилися три еррор меседжа
    }
}