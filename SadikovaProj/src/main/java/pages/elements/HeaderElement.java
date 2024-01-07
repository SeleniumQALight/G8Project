package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

// описание элементов в хедере , часть элементов в классе MainPage

public class HeaderElement extends CommonActionsWithElements {

    /**
     * User
     */
    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createPostButton;
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement signOutButton;
    @FindBy(xpath = "//a[contains(@href,'/profile')]/..//span[contains(@class,'text-white') and not (contains(@class,'header-chat-icon'))]")
    private WebElement userName;
    @FindBy(xpath = "//a[contains(@href,'/profile/')]/img")
    private WebElement myProfileIcon;
    @FindBy(xpath = "//a[contains(@class,'header-search-icon')]")
    private WebElement searchLink;
    @FindBy(xpath = "//span[contains(@class,'header-chat-icon')]")
    private WebElement chatIcon;
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement myProfilePageButton;

    /**
     *VISIBLE
     */
    public HeaderElement checkSignOutButtonIsVisible() {
        checkIsElementVisible(signOutButton);
        return this;
    }

    public HeaderElement checkSearchLinkIsVisible() {
        checkIsElementVisible(searchLink);
        return this;
    }

    public HeaderElement checkChatIconIsVisible() {
        checkIsElementVisible(chatIcon);
        return this;
    }

    public HeaderElement checkCreatePostButtonIsVisible() {
        checkIsElementVisible(createPostButton);
        return this;
    }

    public HeaderElement checkMyProfileIconIsVisible() {
        checkIsElementVisible(myProfileIcon);
        return this;
    }

    public HeaderElement checkUserNameIsVisible() {
        checkIsElementVisible(userName);
        return this;
    }

    /**
     * NOT visible
     */
    public HeaderElement checkSignOutButtonIsNotVisible() {
        checkElementIsNotDisplayed(signOutButton);
        return this;
    }


    public HeaderElement checkSearchLinkIsNotVisible() {
        checkElementIsNotDisplayed(searchLink);
        return this;
    }

    public HeaderElement checkChatIconIsNotVisible() {
        checkElementIsNotDisplayed(chatIcon);
        return this;
    }

    public HeaderElement checkCreatePostButtonIsNotVisible() {
        checkElementIsNotDisplayed(createPostButton);
        return this;
    }

    public HeaderElement checkMyProfileIconIsNotVisible() {
        checkElementIsNotDisplayed(myProfileIcon);
        return this;
    }

    public HeaderElement checkUserNameIsNotVisible() {
        checkElementIsNotDisplayed(userName);
        return this;
    }

    /**
     * CLICKS
     */

    public CreatePostPage clickCreatePostButton() {
        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }

    public MyProfilePage clickOnMyProfilePageButton(){
        clickOnElement(myProfilePageButton);
        return new MyProfilePage(webDriver);
    }

    public HeaderElement clickSignOutButton() {
        clickOnElement(signOutButton);
        return new HeaderElement(webDriver);
    }

    public boolean isButtonSignOutVisible() {
//        WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        return isElementDisplayed(signOutButton);
    }

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
}

