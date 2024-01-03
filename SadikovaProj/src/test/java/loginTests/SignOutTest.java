package loginTests;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void signOutTest() {
        pageProvider.loginPage()
                .loginToProfile(TestData.VALID_LOGIN_UI, TestData.VALID_PASSWORD);
        pageProvider.headerElement().assertUserLoginHasElements();
        pageProvider.headerElement().clickSignOutButton();
        pageProvider.headerElement().assertGuestLoginHasElements();






    }}
