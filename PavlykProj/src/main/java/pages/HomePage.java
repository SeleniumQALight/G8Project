package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends ParentPage{

    WebElement buttonSignOut = webDriver.findElement(By.xpath("//button[text()='Sign Out']"));

        public HomePage(WebDriver webDriver) {
            super(webDriver);
        }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }
}
