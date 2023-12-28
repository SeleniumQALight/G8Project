package loginTests;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;
import pages.ParentPage;

public class CreatePostTest extends BaseTest {

    @Test
    public void createPost(){
       pageProvider.loginPage().loginToProfile(TestData.VALID_LOGIN_UI, TestData.VALID_PASSWORD);
       pageProvider.homePage().checkIsRedirectToHomePage().isButtonSignOutVisible();

    }
}
