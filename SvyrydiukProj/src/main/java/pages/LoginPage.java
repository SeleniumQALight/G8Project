package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
@FindBy(xpath = ".//button[text()='Sign In']") // this element will crreated in PageFactory in CommonActionsWithElements.java
    private WebElement buttonSignIn;

@FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

@FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;


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

    public void enterTextIntoInputLogin(String login) {
       // WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
        //WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextIntoInput(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        //WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
        clickOnElement(buttonSignIn);
    }

    public boolean isWarningMessageVisible() {
        try {
            WebElement warningMessage = webDriver.findElement(By.xpath(".//div[text()='Invalid username/password.']"));
            return isElementDisplayed(warningMessage);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isButtonSignInVisible() {
        //try {
            //WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
            return isElementDisplayed(buttonSignIn);
        //} catch (Exception e) {
        //    return false;
       // }
    }

    //is button Sign In visible


}
