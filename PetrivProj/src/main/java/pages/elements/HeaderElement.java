package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;

// описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    // create post button
    @FindBy(xpath = ".//a[contains(text(), 'Create Post')]")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//span[@class='text-white mr-2']")
    private WebElement userName;

    @FindBy(xpath = ".//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = ".//span[@data-original-title='Chat']")
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

    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed(buttonCreatePost);
    }

    public boolean isButtonMyProfileVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean isUserNameVisible() {
        return isElementDisplayed(userName);
    }

    public boolean isButtonChatVisible() {
        return isElementDisplayed(buttonChat);
    }

    public boolean isButtonSearchVisible() {
        return isElementDisplayed(buttonSearch);
    }

    public void checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
    }

    public void checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
    }

    public void isButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
    }

    public void isButtonMyProfileNotVisible() {
        checkIsElementNotVisible(buttonMyProfile);
    }

    public void isButtonChatNotVisible() {
        checkIsElementNotVisible(buttonChat);
    }

    public void isButtonSearchNotVisible() {
        checkIsElementNotVisible(buttonSearch);
    }

    public void checkTextInUserName(String text) {
        checkTextInElement(userName, text);
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
}
