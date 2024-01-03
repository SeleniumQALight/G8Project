package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;
    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;
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
}
     //   WebElement buttonSignOut = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]"));
        return isElementDisplayed(buttonSignOut);
    }

    public HomePage checkIsredirectToHomePage() {
        //TODO check url
        Assert.assertTrue("Home page is not opened", getHeader().isButtonSignOutVisible());
        return this; //перевірка чи ми на сторінці
    }
    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
}