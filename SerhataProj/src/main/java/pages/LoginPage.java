package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage {


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
        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        enterTextInToInput(inputLogin, login);
    }

    public void enterTextInToInputPassword(String password) {
        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextInToInput(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInPresent() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).isDisplayed();
            logger.info(state + " is button Sign In visible");
            return state;
        } catch (Exception e){
            logger.info("Button Sign In is not displayed");
            return false;
        }
    }

    public boolean isErrorMessagePresent() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).isDisplayed();
            logger.info(state + " is error message visible");
            return state;
        } catch (Exception e){
            logger.info("Error message is not displayed");
            return false;
        }
    }
}
