package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import pages.CommonActionsWhithElements;
import pages.CreatePostPage;

// описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWhithElements {
    // create post button
    @FindBy(xpath = "//a[contains(text(), 'Create Post')]")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisble() {

        return isElementDisplayed(buttonSignOut);

    }
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
