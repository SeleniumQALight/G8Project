package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePages extends ParentPage {
    public HomePages(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSingOutVisible() {
        WebElement buttonSingOut = webDriver.findElement(By.xpath("//button[text()='Sign Out']"));
        return isElementDisplayed(buttonSingOut);
    }
}