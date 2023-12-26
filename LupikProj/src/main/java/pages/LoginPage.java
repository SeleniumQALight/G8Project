package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParrentPage{


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://aqa-complexapp.onrender.com");
            logger.info("Login page was opened");
        } catch (Exception e) {
          logger.error("Can not open Login Page");
          Assert.fail("Can not open Login Page");
        }
    }

    public void enterTextInToInputLogin(String login) {
        WebElement inputLogin = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        enterTextInToInput(inputLogin, login);
    }

    public void enterTextInToInputPassword(String password) {
        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextInToInput(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }
}