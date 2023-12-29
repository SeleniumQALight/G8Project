package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

//describe header element for all pages logged in user
public class HeaderElement extends CommonActionsWithElements {
    //create post button
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    //click on create post button
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }
}
