package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

//описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    //create post button
    @FindBy(xpath = "//a[contains(text(), 'Create Post')]")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;
    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }


    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
