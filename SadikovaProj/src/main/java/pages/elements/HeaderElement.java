package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

// описание элементов в хедере
public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createPostButton;
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement signOutButton;

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(signOutButton);

    }

    public CreatePostPage clickCreatePostButton(){
        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
}
