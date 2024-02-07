package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;

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


    @Step
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    @Step
    public HeaderElement checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonSignOutNotVisible() {
        checkIsElementInvisible(buttonSignOut);
        return this;
    }
    @Step
    public HeaderElement checkIsProfileNameVisible() {
        checkIsElementVisible(profileName);
        return this;
    }
    @Step
    public HeaderElement checkIsProfileNameNotVisible() {
        checkIsElementInvisible(profileName);
        return this;
    }
    @Step
    public HeaderElement checkIsButtonMyProfileIconVisible() {
        checkIsElementVisible(buttonMyProfile);
        return this;
    }
    @Step
    public HeaderElement checkIsButtonMyProfileIconNotVisible() {
        checkIsElementInvisible(buttonMyProfile);
        return this;
    }
    @Step
    public HeaderElement  checkIsCreatePostButtonVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }
    @Step
    public HeaderElement checkIsCreatePostButtonNotVisible() {
        checkIsElementInvisible(buttonCreatePost);
        return this;
    }
    @Step
    public HeaderElement checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat);
        return this;
    }
    @Step
    public HeaderElement checkIsButtonChatNotVisible() {
        checkIsElementInvisible(buttonChat);
        return this;
    }
    @Step
    public HeaderElement checkIsButtonSearchVisible() {
        checkIsElementVisible(buttonSearch);
        return this;
    }
    @Step
    public HeaderElement checkIsButtonSearchNotVisible() {
        checkIsElementInvisible(buttonSearch);
        return this;
    }
    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
    @Step
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
    @Step
    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
}
