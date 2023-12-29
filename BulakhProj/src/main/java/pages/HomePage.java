package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        // TODO check url
        Assert.assertTrue("Invalid page - not Home page"
                , getHeader().isButtonSignOutVisible());
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
    public boolean isButtonSingOutVisible(){
        try {
            WebElement buttonSignOut = webDriver.findElement(By.xpath("//button[text()='Sign Out']"));
            return isElementDisplayed(buttonSignOut);
        } catch (Exception e) {
            return false;
        }
    }
    }


