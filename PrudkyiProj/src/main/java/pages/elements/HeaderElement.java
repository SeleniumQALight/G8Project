package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

//описує усі елементи які є  в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//*[@class='btn btn-sm btn-success mr-2']")
    public WebElement buttonCreatePost;
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSingOut;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public boolean isButtonSingOutVisible() {
        return isElementDisplayed(buttonSingOut);
    }
}