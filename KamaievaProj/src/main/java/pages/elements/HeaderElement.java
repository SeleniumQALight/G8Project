package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;
import pages.LoginPage;

//Discribe elements which present in header of page for user who was logined
public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = "//a[contains(text(), 'Create Post')]")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//img[@data-original-title= 'My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement userName;

    @FindBy(xpath = "//a[@data-original-title= 'Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-original-title= 'Chat']")
    private WebElement buttonChat;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed(buttonCreatePost);
    }

    public boolean isButtonMyProfileVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean isButtonSearchVisible(String elementName) {
        return isElementDisplayed(buttonSearch, elementName);
    }

    public boolean isButtonChatVisible(String elementName) {
        return isElementDisplayed(buttonChat, elementName);
    }

    public boolean isUserNameVisible() {
        return isElementDisplayed(userName);
    }

    public Object getUserName() {
        return userName.getText();
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public void checkIsButtonChatNotVisible() {
        checkIsElementNotVisible(buttonChat, "Button Chat");
    }

    public void checkIsButtonSearchNotVisible() {
        checkIsElementNotVisible(buttonSearch, "Button Search");
    }

    public void checkIsButtonMyProfileNotVisible() {
        checkIsElementNotVisible(buttonMyProfile, "Button My Profile");
    }

    public void checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost, "Button Create Post");
    }

    public void checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut, "Button Sign Out");
    }

    public void checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
    }
}
