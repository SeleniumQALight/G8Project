package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;

// описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']//..")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//span[@class = 'text-white mr-2']")
    private WebElement spanUserProfileName;

    @FindBy(xpath = ".//a[@data-original-title = 'Search']")
    private WebElement linkGetSearch;

    @FindBy(xpath = ".//span[@data-original-title = 'Chat']")
    private WebElement spanGetChat;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public boolean isButtonCreatePostIsVisible() {
        return isElementDisplayed(buttonCreatePost);
    }

    public boolean isButtonMyProfileIsVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean isSpanUserProfileNameIsVisible() {
        return isElementDisplayed(spanUserProfileName);
    }

    public boolean isLinkGetSearchIsVisible() {
        return isElementDisplayed(linkGetSearch);
    }

    public boolean isSpanGetChatIsVisible() {
        return isElementDisplayed(spanGetChat);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public HeaderElement checkAllElementsInHeaderAreVisible() {
        isButtonSignOutVisible();
        isButtonCreatePostIsVisible();
        isButtonMyProfileIsVisible();
        isSpanUserProfileNameIsVisible();
        isLinkGetSearchIsVisible();
        isSpanGetChatIsVisible();
        return this;
    }

    public void checkAllElementsFromHeaderAreInvisible() {
        checkIsElementNotVisible(buttonCreatePost);
        checkIsElementNotVisible(buttonSignOut);
        checkIsElementNotVisible(buttonMyProfile);
        checkIsElementNotVisible(spanUserProfileName);
        checkIsElementNotVisible(linkGetSearch);
        checkIsElementNotVisible(spanGetChat);
    }
}