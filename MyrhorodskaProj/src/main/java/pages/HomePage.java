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
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return isElementDisplayed(buttonSignOut);
        } catch (Exception e) {
            return false;
        }
    }
<<<<<<< HEAD
}

=======
}
>>>>>>> 3a409fe01ea463fa368157df8d6ab2facd56281e
