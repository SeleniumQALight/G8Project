package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        try {
            WebElement buttonSignOut
                    = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return isElementDisplayed(buttonSignOut);
        } catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }
}
