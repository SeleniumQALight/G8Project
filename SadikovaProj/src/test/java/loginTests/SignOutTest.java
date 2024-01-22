package loginTests;

import baseTest.BaseTest;
import data.TestData;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void signOutTest() {
        pageProvider.mainPage()
                .loginToProfile(TestData.VALID_LOGIN_UI, TestData.VALID_PASSWORD);
        pageProvider.headerElement().checkSearchLinkIsVisible()
                .checkChatIconIsVisible()
                .checkUserNameIsVisible()
                .checkMyProfileIconIsVisible()
                .checkCreatePostButtonIsVisible()
                .checkSignOutButtonIsVisible();
        pageProvider.mainPage().checkLoginFieldNotVisible()
                .checkPasswordFieldNotVisible()
                .checkSignInButtonIsNotVisible();
        pageProvider.headerElement().clickSignOutButton();
        pageProvider.headerElement().checkSearchLinkIsNotVisible()
                .checkChatIconIsNotVisible()
                .checkUserNameIsNotVisible()
                .checkMyProfileIconIsNotVisible()
                .checkCreatePostButtonIsNotVisible()
                .checkSignOutButtonIsNotVisible();
        pageProvider.mainPage().checkPasswordFieldIsVisible()
                .checkLoginFieldIsVisible()
                .checkSignInButtonIsVisible();


    }
}
