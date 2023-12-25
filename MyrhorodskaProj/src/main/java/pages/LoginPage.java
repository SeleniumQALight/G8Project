package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//опис методів для сторінки логін
public class LoginPage extends ParentPage{
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://aqa-complexapp.onrender.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }

    public void enterTextInToInputLogin(String login) {
        WebElement inputLogin = webDriver.findElement(By.xpath(".//*[@placeholder='Username']"));
        enterTextInToInput(inputLogin, login);

    }
    public void enterTextInToInputPassword(String password) {
        WebElement inputPassword = webDriver.findElement(By.xpath(".//*[@placeholder='Password']"));
        enterTextInToInput(inputPassword, password);
    }
    public void clickOnButtonSingIn() {
        WebElement buttonSingIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSingIn);
    }
<<<<<<< HEAD
=======

>>>>>>> 3a409fe01ea463fa368157df8d6ab2facd56281e
    public boolean isButtonSignInVisible() {
        try {
            WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
            return isElementDisplayed(buttonSignIn);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isInvalidUserNamePasswordMessageVisible() {
        WebElement invalidMassage = webDriver.findElement(By.xpath("//div[contains(text(),'Invalid username/password.')]"));
        return isElementDisplayed(invalidMassage);
    }
}

