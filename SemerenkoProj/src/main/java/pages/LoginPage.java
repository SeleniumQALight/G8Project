package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage{
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://aqa-complexapp.onrender.com");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
        }
    }

    public void enterTextIntoInputLogin(String login) {
        WebElement inputLogin = webDriver.findElement(
                By.xpath(".//input[@placeholder='Username']"));
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPass(String pass) {
        WebElement inputPass = webDriver.findElement(
                By.xpath(".//input[@placeholder='Password']"));
        enterTextIntoInput(inputPass, pass);
    }

    public void clickOnButtonSignIn() {
        WebElement buttonSignIn = webDriver.findElement(
                By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        clickOnElement(buttonSignIn);
    }
}
