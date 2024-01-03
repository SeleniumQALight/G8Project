package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    public HomePage checkIsButtonCreateNewPostVisibe() {
        Assert.assertTrue("CreatePostButton is absent", getHeader().isButtonCreatePostVisible());
        return this;
    }

    public HomePage checkIsButtonSignOutVisible(){
        Assert.assertTrue("SignOutButton is absent", getHeader().isButtonSignOutVisible());
        return this;
    }
    public HomePage checkIsLinkMyProfileVisible(){
        Assert.assertTrue("MyProfile is absent", getHeader().isLinkMyProfileVisible());
        return this;
    }

    public HomePage checkIsSpanUserNameVisible(){
        Assert.assertTrue("SpanUserName is absent", getHeader().isSpanUserNameVisible());
        return this;
    }

    public HomePage checkIsInputUsernameUnvisible(){
        Assert.assertFalse("Input Username is visible", getHeader().isInputUsernameVisible());
        return this;
    }

    public HomePage checkIsInputPasswordUnvisible(){
        Assert.assertFalse("Input Password is visible", getHeader().isInputPasswordVisible());
        return this;
    }


    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("Invalid page - not Home Page", getHeader().isButtonSignOutVisible());
        return this;
    }
}
