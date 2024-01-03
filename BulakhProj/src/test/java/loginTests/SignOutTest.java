package loginTests;

import baseTast.BaseTest;
import org.junit.Test;
import pages.elements.HeaderElement;

public class SignOutTest extends BaseTest {

    @Test
    public void signOut() {

        HeaderElement header = pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader()
                .checkSearchButtonIsVisible()
                .checkChatButtonIsVisible()
                .checkAvatarIsVisible()
                .checkCreatePostButtonIsVisible()
                .checkSignOutButtonIsVisible()
                .checkLoginInputIsNotVisible()
                .checkPasswordInputIsNotVisible()
                .checkSignInButtonIsNotVisible();


    }


}
