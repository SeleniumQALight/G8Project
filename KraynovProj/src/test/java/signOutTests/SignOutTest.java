package signOutTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void logOutTest() {
        pageProvider.loginPage()
                .openLoginPageFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonSignOut()
                .checkIsRedirectToHomePage();
    }
}
