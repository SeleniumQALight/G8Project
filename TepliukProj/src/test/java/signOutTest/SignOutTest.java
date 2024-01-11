package signOutTest;

import baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void signOutTest() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().isButtonFindVisible();


//        pageProvider.homePage().getHeader().isButtonChatVisible();
//        pageProvider.homePage().getHeader().isAvatarVisible();
//        pageProvider.homePage().getHeader().isButtonCreatePostVisible();
//        pageProvider.homePage().getHeader().isButtonSignOutVisible();



        ;
    }
}
