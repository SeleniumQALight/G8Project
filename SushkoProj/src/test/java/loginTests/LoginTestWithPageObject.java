package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSingIn();

        pageProvider.homePage().getHeader().checkAllElementsInHeaderAreVisible();
        pageProvider.loginPage().checkAllElementsFromLoginFormAreInvisible();

//I did it first via assert, then replaced with new created methods, but they do more checks than specified in the task.
// it looks better through the methods, but I commented the first option just in case
//
//        Assert.assertTrue("Button SignOut is not visible",
//                pageProvider.homePage().getHeader().isButtonSignOutVisible());
//        Assert.assertTrue("Button Create Post is not visible",
//                pageProvider.homePage().getHeader().isButtonCreatePostIsVisible());
//        Assert.assertTrue("Button My Profile is not visible",
//                pageProvider.homePage().getHeader().isButtonMyProfileIsVisible());
//        Assert.assertTrue("User profile name is not visible",
//                pageProvider.homePage().getHeader().isSpanUserProfileNameIsVisible());

//        Assert.assertFalse("Field for login is visible",
//                pageProvider.loginPage().isInputLoginIsVisible());
//        Assert.assertFalse("Field for password is visible",
//                pageProvider.loginPage().isInputPasswordIsVisible());

    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("qaauto_1");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertTrue("Button SignIn is not visible",
                pageProvider.loginPage().isButtonSignInIsVisible());
        Assert.assertFalse("Button SignOut is visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Validation message is not displayed",
                pageProvider.loginPage().isValidationMessageIsDisplayed());
    }

    @Test
    public void loginAndOpenLoginPageInNewTab(){
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .getHeader()
                .isButtonSignOutVisible()
        ;

        pageProvider.loginPage()
                .openLoginPageInNewTabWithJS(1)
        ;

        pageProvider.homePage().getHeader().isButtonSignOutVisible();
        pageProvider.loginPage().switchToMainTab();

        pageProvider.homePage().getHeader().isButtonSignOutVisible();

        pageProvider.loginPage().closeTabAndSwitchToMainTab(1);
        pageProvider.homePage().getHeader().isButtonSignOutVisible();
    }

    @Test
    public void inputLoginDataAndRefreshPage(){
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);

        pageProvider.loginPage().refreshPage();
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertFalse("Button SignOut is visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }
}


//Додаткова домашка (не обовʼязково до виконання)
//1. Тест на валідний логін за допомогою кнопок (ТАБ і Ентер) використувати Actions клас
//    Steps
//    1. Open login page
//    2. Press Tab key
//    3. Press Tab key
//    4. Enter login into input Login (введення без елемента, використовуючи класс Actions)
//    5. Press Tab key
//    6. Enter password into input Password
//    7. Press Enter key
//    8. Check that button SignOut is visible
//
//2. Тест на перевірку еррор меседжів при регістраціі за допомогою кнопок (ТАБ і Ентер)
//    Steps
//     1. Open login page
//     2. Tabom доклікати до поля User Name в Реєстраційній формі
//     3. Ввести не валідне значення в поле User Name
//     4. Tabom доклікати до поля Email в Реєстраційній формі
//     5. Ввести не валідне значення в поле Email
//     6. Tabom доклікати до поля Password в Реєстраційній формі
//     7. Ввести не валідне значення в поле Password
//     8. Натиснути кнопку Enter
//     9. Перевірити що відобразилися три еррор меседжа
//
// 3. Тест на перевірку разлогінування в двох табах
//    Steps
//     1. Open login page
//     2. Login with valid credentials
//     3. Check that button SignOut is visible
//     4. Open new tab in browser using JavaScript
//     5. Switch to new tab
//     6. Open login page
//     7. Check that button SignOut is visible
//     8. Switch to main tab
//     9. Click on button SignOut
//     10. Check that button SignOut is not visible
//     11. Switch to new tab
//     12. Refresh page
//     12. Check that button SignOut is not visible
//
//
//NOTE!
//Спробуйте знайти методи самостійно по роботі з вкладками в браузері, по роботі з клавіатурою (використовуючи Actions клас).
//Використовуйте пошук по сайту stackoverflow.com, selenium.dev, google.com. А також звичайно Copilot 🙂
//Якщо ж треба перевірити методи чи зрозуміти як їх використати в тестах, та і деякі приклади тестів - це можна підглянути ось в цьому комміті
//https://github.com/SeleniumQALight/G8Project/pull/224/commits/cd9643656c8f95dfff98750cd0fea94716a14b5a
