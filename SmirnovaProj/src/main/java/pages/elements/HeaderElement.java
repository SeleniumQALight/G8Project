package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;

//описує елементи які є в Header залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = "//*[@class='text-white mr-2 header-search-icon']")
    private WebElement searchIcon;
    @FindBy(xpath = "//*[@class='text-white mr-2 header-chat-icon']")
    private WebElement chatIcon;
    @FindBy(xpath = "//*[@class='mr-2']")
    private WebElement userAvatar;
    @FindBy(xpath = "//*[@class='text-white mr-2']")
    private WebElement linkUserName;
    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = "//button[text()='Sign Out']")
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

    public boolean isSearchIconDisplayed() {
        return isElementDisplayed(searchIcon);
    }

    public boolean isChatIconDisplayed() {
        return isElementDisplayed(chatIcon);
    }

    public boolean isAvatarDisplayed() {
        return isElementDisplayed(userAvatar);
    }

    public boolean isUserNameDisplayed() {
        return isElementDisplayed(linkUserName);
    }

    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed(buttonCreatePost);
    }

    public boolean isHeaderOfTheLoggedInUserDisplayed() {
        isSearchIconDisplayed();
        isChatIconDisplayed();
        isUserNameDisplayed();
        isAvatarDisplayed();
        isButtonCreatePostVisible();
        isButtonSignOutVisible();
        return true;
    }
    public boolean isHeaderOfTheLoggedInUserNotDisplayed() {
        isSearchIconNotDisplayed();
        isChatIconNotDisplayed();
        isUserNameNotDisplayed();
        isAvatarNotDisplayed();
        isButtonCreatePostNotVisible();
        isButtonSignOutNotVisible();
        return true;
    }

    private void isButtonSignOutNotVisible() {
        isElementNotDisplayed(buttonSignOut);
    }

    private void isButtonCreatePostNotVisible() {
        isElementNotDisplayed(buttonCreatePost);
    }

    private void isAvatarNotDisplayed() {
        isElementNotDisplayed(userAvatar);
    }

    private void isUserNameNotDisplayed() {
        isElementNotDisplayed(linkUserName);
    }

    private void isChatIconNotDisplayed() {
        isElementNotDisplayed(chatIcon);
    }

    private void isSearchIconNotDisplayed() {
        isElementNotDisplayed(searchIcon);
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
}
