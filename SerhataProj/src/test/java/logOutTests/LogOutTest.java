package logOutTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LogOutTest extends BaseTest {
    @Test
    public void LogOutTest() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonSignOut()
                .checkIsRedirectToLoginPage()
                .getHeader().checkIsHomeHeaderInvisibleFolSignOutUser()
        ;
    }
}