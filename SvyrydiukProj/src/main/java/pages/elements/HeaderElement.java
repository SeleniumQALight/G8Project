package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

//describe header element for all pages logged in user
public class HeaderElement extends CommonActionsWithElements {
    //create post button
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = ".//span[@class='text-white mr-2']")
    private WebElement username;
    @FindBy(xpath = ".//img[@alt='My profile']")
    private WebElement profileImage;

    @FindBy(xpath = ".//a[@class='text-white mr-2 header-search-icon']")
    private WebElement searchButton;
    @FindBy(xpath = ".//span[@class='text-white mr-2 header-chat-icon']")
    private WebElement chatButton;

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
    @Step
    //click on create post button
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
    @Step
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    //button Create Post is visible
    @Step
    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed(buttonCreatePost);
    }

    //My profile image button is visible
    @Step
    public boolean isProfileButtonVisible() {
        return isElementDisplayed(profileImage);
    }

    //username is visible
    @Step
    public boolean isUsernameVisible() {
        return isElementDisplayed(username);
    }

    //check text in username
    @Step
    public void checkTextInUsername(String text) {
        checkTextInElement(username, text);
    }
    @Step
    public boolean isSearchButtonVisible() {
        return isElementDisplayed(searchButton);
    }

    @Step
    public boolean isChatButtonVisible() {
        return isElementDisplayed(chatButton);
    }
    @Step
    public void isChatButtonNotVisible() {
        checkIsElementNotVisible(chatButton);
    }
    @Step
    public void clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
    }
    @Step
    public void isProfileButtonNotVisible() {
        checkIsElementNotVisible(profileImage);
    }
    @Step
    public void isButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
    }
    @Step
    public void isButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
    }
    @Step
    public void isSearchButtonNotVisible() {
        checkIsElementNotVisible(searchButton);
    }
    @Step
    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
}
