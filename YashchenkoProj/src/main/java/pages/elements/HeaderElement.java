package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;

//This class is for elements that are present on all pages of the logged user
public class HeaderElement extends CommonActionsWithElements {
    //create post button
    @FindBy(xpath = ".//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//span[@class='text-white mr-2']")
    private WebElement profileName;

    @FindBy(xpath = ".//a[@href='/profile/qaauto']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//span[@data-original-title='Chat']")
    private WebElement buttonChat;

    @FindBy(xpath = ".//*[@data-original-title='Search']")
    private WebElement buttonSearch;


    public boolean IsButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public HeaderElement checkIsButtonSignOutVisible() {
        isElementDisplayed(buttonSignOut);
        return this;
    }

    public HeaderElement checkIsButtonSignOutNotVisible() {
        checkIsElementInvisible(buttonSignOut);
        return this;
    }

    public HeaderElement checkIsProfileNameVisible() {
        checkIsElementVisible(profileName);
        return this;
    }

    public HeaderElement checkIsProfileNameNotVisible() {
        checkIsElementInvisible(profileName);
        return this;
    }

    public HeaderElement checkIsButtonMyProfileIconVisible() {
        checkIsElementVisible(buttonMyProfile);
        return this;
    }

    public HeaderElement checkIsButtonMyProfileIconNotVisible() {
        checkIsElementInvisible(buttonMyProfile);
        return this;
    }

    public HeaderElement  checkIsCreatePostButtonVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public HeaderElement checkIsCreatePostButtonNotVisible() {
        checkIsElementInvisible(buttonCreatePost);
        return this;
    }

    public HeaderElement checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat);
        return this;
    }

    public HeaderElement checkIsButtonChatNotVisible() {
        checkIsElementInvisible(buttonChat);
        return this;
    }

    public HeaderElement checkIsButtonSearchVisible() {
        checkIsElementVisible(buttonSearch);
        return this;
    }

    public HeaderElement checkIsButtonSearchNotVisible() {
        checkIsElementInvisible(buttonSearch);
        return this;
    }

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
}
