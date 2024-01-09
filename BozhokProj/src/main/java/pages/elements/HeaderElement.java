package pages.elements;

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

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement successProfileName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement buttonChat;

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

    // check is button Create Post visible
    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed(buttonCreatePost);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    // check is button Profile visible
    public boolean isButtonProfileVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public boolean isButtonSearchVisible() {
        return isElementDisplayed(buttonSearch);
    }

    public boolean isButtonChatVisible() {
        return isElementDisplayed(buttonChat);
    }

    public boolean isButtonAvatarVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean isButtonSignInVisible() {
        return isElementNotDisplayed(successProfileName);
    }

    public boolean isInputPasswordVisible() {
        return isElementNotDisplayed(inputPassword);
    }

    public boolean isInputLoginVisible() {
        return isElementNotDisplayed(successProfileName);
    }

}