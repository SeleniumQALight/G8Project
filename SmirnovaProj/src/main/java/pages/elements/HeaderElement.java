package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;
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
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public HeaderElement checkIsSearchIconDisplayed() {
        checkIsElementVisible(searchIcon);
        return this;
    }

    public HeaderElement checkIsChatIconDisplayed() {
        checkIsElementVisible(chatIcon);
        return this;
    }

    public HeaderElement checkIsAvatarDisplayed() {
        checkIsElementVisible(userAvatar);
        return this;
    }

    public HeaderElement checkIsUserNameDisplayed() {
        checkIsElementVisible(linkUserName);
        return this;
    }

    public HeaderElement checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public HeaderElement checkIsHeaderOfTheLoggedInUserDisplayed() {
        checkIsSearchIconDisplayed();
        checkIsChatIconDisplayed();
        checkIsUserNameDisplayed();
        checkIsAvatarDisplayed();
        checkIsButtonCreatePostVisible();
        checkIsButtonSignOutVisible();
        return this;
    }
    public HeaderElement checkIsHeaderOfTheLoggedInUserNotDisplayed() {
        checkIsSearchIconNotDisplayed();
        checkIsChatIconNotDisplayed();
        checkIsUserNameNotDisplayed();
        checkIsAvatarNotDisplayed();
        checkIsButtonCreatePostNotVisible();
        checkIsButtonSignOutNotVisible();
        return this;
    }

    private HeaderElement checkIsButtonSignOutNotVisible() {
        checkElementIsNotVisible(buttonSignOut);
        return this;
    }

    private HeaderElement checkIsButtonCreatePostNotVisible() {
        checkElementIsNotVisible(buttonCreatePost);
        return this;
    }

    private HeaderElement checkIsAvatarNotDisplayed() {
        checkElementIsNotVisible(userAvatar);
        return this;
    }

    private HeaderElement checkIsUserNameNotDisplayed() {
        checkElementIsNotVisible(linkUserName);
        return this;
    }

    private HeaderElement checkIsChatIconNotDisplayed() {
        checkElementIsNotVisible(chatIcon);
        return this;
    }

    private HeaderElement checkIsSearchIconNotDisplayed() {
        checkElementIsNotVisible(searchIcon);
        return this;
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
}
