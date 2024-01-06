package signUpTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SignUpTest extends BaseTest {

    @Test

    public  void invalidSignUpTest(){

        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegisterLogin("tr");
        pageProvider.loginPage().enterTextIntoRegisterEmail("tr");
        pageProvider.loginPage().enterTextIntoRegisterPassword("tr");
        pageProvider.loginPage().clickOnButtonSignUp();

        Assert.assertTrue("Button Sign In is not visible", pageProvider.loginPage().isButtonSignInVisible());


        Assert.assertTrue("Error message in register Username field is not visible", pageProvider.loginPage().isTextInErrorMessageInRegisterUsernameIsVisible());
        Assert.assertTrue("Error message in register Email field is not visible",pageProvider.loginPage().isTextInErrorMessageInRegisterEmailIsVisible());
        Assert.assertTrue("Error message in register Password field is not visible",pageProvider.loginPage().isTextInErrorMessageInRegisterPasswordIsVisible());

        Assert.assertTrue("Button Sign Out is visible", pageProvider.homePage().getHeader().isButtonSihOutIsNotVisible());
        Assert.assertTrue("Button Create Post is visible", pageProvider.homePage().getHeader().isButtonCreatePostNotVisible());





    }
}
