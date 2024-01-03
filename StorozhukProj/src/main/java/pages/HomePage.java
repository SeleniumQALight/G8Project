package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends ParentPage{

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        //WebElement buttonSignOut = webDriver.findElement(
        //        By.xpath("//button[text()='Sign Out']"));
        WebElement buttonSignOut = findElementByXpath("//button[text()='Sign Out']");
        return isElementDisplayed(buttonSignOut);
    }

    public boolean isButtonSignInVisible() {
        //WebElement buttonSignIn = webDriver.findElement(
        //        By.xpath("//button[text()='Sign In']"));
        WebElement buttonSignIn = findElementByXpath("//button[text()='Sign In']");
        return isElementDisplayed(buttonSignIn);
    }
}
