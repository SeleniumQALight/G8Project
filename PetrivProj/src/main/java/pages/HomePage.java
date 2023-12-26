package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//button[contains(text(),'Sign In')]") // цей елемент створиться PageFactory в CommonActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[contains(text(),'Invalid username/password.')]")
    private WebElement invalidUsernameOrPasswordMessage;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isInvalidUsernameOrPasswordMessageVisible() {
        return isElementDisplayed(invalidUsernameOrPasswordMessage);
    }

    public HomePage checkIsRedirectToHomePage() {
        // TODO check url
        Assert.assertTrue("Invalid page - not Home Page", isButtonSignOutVisible());
        return this;
    }
}
