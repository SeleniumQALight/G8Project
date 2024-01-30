package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;

//описует элементы котоыре есть в Header у залогинено пользователя
public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = ".//img[@data-original-title='My Profile']/..")
    private WebElement buttonMyProfile;
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement linkCreatePost;
    @FindBy(xpath = ".//span[contains(@class,'text-white')and not(@data-original-title)]")
    private WebElement userName;
    @FindBy(xpath = ".//a[@data-original-title='Search']")
    private WebElement linkSearch;
    @FindBy(xpath = ".//span[@data-original-title='Chat']")
    private WebElement iconChat;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
    @Step
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
    public HeaderElement checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public HeaderElement checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
        return this;
    }

    public HeaderElement checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public HeaderElement checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
        return this;
    }

    public HeaderElement checkIsMyProfileButtonVisible() {
        checkIsElementVisible(buttonMyProfile);
        return this;
    }

    public HeaderElement checkIsMyProfileButtonNotVisible() {
        checkIsElementNotVisible(buttonMyProfile);
        return this;
    }

    public HeaderElement checkIsUserNameVisible() {
        checkIsElementVisible(userName);
        return this;
    }

    public void checkTextInUsername(String text) {
        checkTextInElement(userName, text);
    }

    public HeaderElement checkIsSearchLinkVisible() {
        checkIsElementVisible(linkSearch);
        return this;
    }

    public HeaderElement checkIsSearchLinkNotVisible() {
        checkIsElementNotVisible(linkSearch);
        return this;
    }

    public HeaderElement checkIsChatIconVisible() {
        checkIsElementVisible(iconChat);
        return this;
    }

    public HeaderElement checkIsChatIconNotVisible() {
        checkIsElementNotVisible(iconChat);
        return this;
    }

    public void checkIsHeaderForUserVisible() {
        checkIsSearchLinkVisible();
        checkIsChatIconVisible();
        checkIsMyProfileButtonVisible();
        checkIsButtonCreatePostVisible();
        checkIsButtonSignOutVisible();
        logger.info("Header for user is visible");
    }

    public void checkIsHeaderForGuestVisible() {
        checkIsSearchLinkNotVisible();
        checkIsChatIconNotVisible();
        checkIsMyProfileButtonNotVisible();
        checkIsButtonCreatePostNotVisible();
        checkIsButtonSignOutNotVisible();
    }

}
