package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTextWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Button Sing Out is not visible", pageProvider.homePage().isButtonSingOutVisible());
    }

}
