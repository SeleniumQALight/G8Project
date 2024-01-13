package registerFormTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegisterFormMessagesCheckWhrnInvalidDataWithTabAndEnter extends BaseTest{

    @Test

    public void login() {
        pageProvider.loginPage().openLoginPage(); // 1. Open login page
        pageProvider.loginPage().pressTabKey(5); // 2.1 Tabom доклікати до поля User Name в Реєстраційній формі
        pageProvider.loginPage().enterTextIntoField("tr"); // 3. Ввести не валідне значення в поле User Name
        pageProvider.loginPage().pressTabKey(1); // 4. Tabom доклікати до поля Email в Реєстраційній формі
        pageProvider.loginPage().enterTextIntoField("tr"); //5. Ввести не валідне значення в поле Email
        pageProvider.loginPage().pressTabKey(1); // 6. Tabom доклікати до поля Password в Реєстраційній формі
        pageProvider.loginPage().enterTextIntoField("tr"); // 7. Ввести не валідне значення в поле Password
        pageProvider.loginPage().pressEnterKey(); // 8. Натиснути кнопку Enter
        pageProvider.loginPage().checkErrorMessages("Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."); // 9. Перевірити що відобразилися три еррор меседжа
    }
}