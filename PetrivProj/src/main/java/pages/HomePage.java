package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        String buttonSignOutXpath = ".//button[text()='Sign Out']";
        return isElementDisplayed(buttonSignOutXpath);
    }

    public boolean isButtonSignInVisible() {
        String buttonSignInXpath = ".//button[text()='Sign In']";
        return isElementDisplayed(buttonSignInXpath);
    }

    public boolean isInvalidUsernameOrPasswordMessageVisible() {
        String invalidUsernameOrPasswordMessageXpath = ".//div[contains(text(),'Invalid username/password.')]";
        return isElementDisplayed(invalidUsernameOrPasswordMessageXpath);
    }
}
