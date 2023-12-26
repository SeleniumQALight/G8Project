package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePages extends ParentPage {
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSingOut;

    public HomePages(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSingOutVisible() {
       // WebElement buttonSingOut = webDriver.findElement(By.xpath("//button[text()='Sign Out']"));
        return isElementDisplayed(buttonSingOut);
    }
}