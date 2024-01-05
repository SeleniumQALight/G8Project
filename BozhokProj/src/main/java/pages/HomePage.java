package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;

    @FindBy(xpath = ".//*[@class ='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;


    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement successProfileName;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkIsRedirectOnHomePage() {
//        TODO check url
        Assert.assertTrue("Invalid page - not Home Page ", getHeader().isButtonSignOutVisible());
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }


    // check is Profile Name visible
    public HomePage checkIsProfileNameVisible(String profileName) {
        Assert.assertEquals("Profile Name is not visible", profileName, successProfileName.getText());
        return this;
    }

//    public boolean isInputLoginVisible() {
//        return isElementDisplayed(successProfileName);
//    }
}
