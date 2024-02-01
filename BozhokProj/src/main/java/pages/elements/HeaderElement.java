package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionWithElements;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;

// опишемо елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionWithElements {

    // create post button
    @FindBy(xpath = "//*[@class ='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement profileName;

    @FindBy(xpath = "//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement buttonChat;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

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
        checkIsElementNotVisible(buttonSignOut);
        return this;
    }

    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    // check is button Create Post visible
    @Step
    public HeaderElement checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    @Step
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    // check is button Profile visible
    public boolean isButtonProfileVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    @Step
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    @Step
    public HeaderElement checkIsButtonSearchVisible() {
       checkIsElementVisible(buttonSearch);
       return this;
    }

    @Step
    public HeaderElement checkIsButtonSearchNotVisible() {
        checkIsElementNotVisible(buttonSearch);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonChatNotVisible() {
        checkIsElementNotVisible(buttonChat);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonMyProfileNotVisible() {
        checkIsElementNotVisible(buttonMyProfile);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
        return this;
    }

    @Step
    public HeaderElement checkIsProfileNameVisible(String login) {
        checkTextInElement(profileName, login);
        return this;
    }
}