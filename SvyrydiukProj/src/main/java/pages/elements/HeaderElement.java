package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

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


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    //click on create post button
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    //button Create Post is visible
    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed(buttonCreatePost);
    }

    //My profile image button is visible
    public boolean isProfileButtonVisible() {
        return isElementDisplayed(profileImage);
    }

    //username is visible
    public boolean isUsernameVisible() {
        return isElementDisplayed(username);
    }

    //check text in username
    public void checkTextInUsername(String text) {
        checkTextInElement(username, text);
    }

    public boolean isSearchButtonVisible() {
        return isElementDisplayed(searchButton);
    }


    public boolean isChatButtonVisible() {
        return isElementDisplayed(chatButton);
    }

    public void isChatButtonNotVisible() {
        checkIsElementNotVisible(chatButton);
    }

    public void clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
    }

    public void isProfileButtonNotVisible() {
        checkIsElementNotVisible(profileImage);
    }

    public void isButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
    }

    public void isButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
    }

    public void isSearchButtonNotVisible() {
        checkIsElementNotVisible(searchButton);
    }
}
