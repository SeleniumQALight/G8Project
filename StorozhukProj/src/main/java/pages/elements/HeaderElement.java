package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

//this page describes elements of header for logged in user
public class HeaderElement extends CommonActionsWithElements {
//create post button
    @FindBy (xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
    public boolean isButtonSignOutVisible() {WebElement buttonSignOut = webDriver.findElement(
                By.xpath("//button[text()='Sign Out']"));
        return isElementDisplayed(buttonSignOut);
    }
    public CreatePostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
