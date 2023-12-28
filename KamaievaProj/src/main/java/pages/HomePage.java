package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + " is button visible");
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }
}
