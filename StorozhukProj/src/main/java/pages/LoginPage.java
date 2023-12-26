package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage  extends ParentPage {
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]") //цей елемент буде створенний PageFactory в CommonActionsWithElements
    private WebElement buttonSingIn;
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

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

    public void enterTextIntoInputLogin(String login) {
        enterTextIntoInputLogin(inputLogin, login); //   WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
    }

    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInputLogin(inputPassword, password); // WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
    }

    public void clickOnButtonSignIn() {
    //    WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSingIn);
    }

    // is button Sign In visible
    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSingIn);
    }

}
