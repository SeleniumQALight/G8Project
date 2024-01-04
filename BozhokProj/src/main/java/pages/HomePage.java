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

    @FindBy(xpath = ".//a[@class='mr-2']")
    private WebElement buttonProfile;

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement successProfileName;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;


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

    public HomePage checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public HomePage checkIsButtonProfileVisible() {
        checkIsElementVisible(buttonProfile);
        return this;
    }

    public HomePage checkIsProfileNameVisible(String text) {
        checkTextInElement(successProfileName, text);
        return this;
    }





}
