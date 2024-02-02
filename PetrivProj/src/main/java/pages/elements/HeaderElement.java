package pages.elements;

import io.qameta.allure.Step;
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
    @Step
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }
    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
    @Step
    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed(buttonCreatePost);
    }
    @Step
    public boolean isButtonMyProfileVisible() {
        return isElementDisplayed(buttonMyProfile);
    }
    @Step
    public boolean isUserNameVisible() {
        return isElementDisplayed(userName);
    }
    @Step
    public boolean isButtonChatVisible() {
        return isElementDisplayed(buttonChat);
    }
    @Step
    public boolean isButtonSearchVisible() {
        return isElementDisplayed(buttonSearch);
    }
    @Step
    public void checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
    }
    @Step
    public void checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
    }
    @Step
    public void isButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
    }
    @Step
    public void isButtonMyProfileNotVisible() {
        checkIsElementNotVisible(buttonMyProfile);
    }
    @Step
    public void isButtonChatNotVisible() {
        checkIsElementNotVisible(buttonChat);
    }
    @Step
    public void isButtonSearchNotVisible() {
        checkIsElementNotVisible(buttonSearch);
    }
    @Step
    public void checkTextInUserName(String text) {
        checkTextInElement(userName, text);
    }
    @Step
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
    @Step
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
}
