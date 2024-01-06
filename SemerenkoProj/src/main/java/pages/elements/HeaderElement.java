package pages.elements;

import libs.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;

// описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    //create new post
    @FindBy(xpath = ".// a [@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    public WebElement buttonSignIn;

    @FindBy(xpath = ".//a[@href='/profile/qaauto']")
    private WebElement buttonProfile;

    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[@href='/profile/" + TestData.VALID_LOGIN_UI + "']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//span[@data-original-title='Chat']")
    private WebElement buttonChat;

    @FindBy(xpath = ".//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = ".//span[contains (text(), 'qaauto')]")
    private WebElement spanUserName;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUsername;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;


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
    public MyProfilePage clickOnButtonProfile() {
        clickOnElement(buttonProfile);
        return new MyProfilePage(webDriver);
    }

    public LoginPage clickOnElementButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public void checkIsElementButtonCreateNewPostVisible() {
        checkIsElementVisible(buttonCreatePost, "buttonCreatePost");
    }

    public void checkIsElementButtonCreateNewPostUnvisible() {
        checkIsElementUnvisible(buttonCreatePost, "buttonCreatePost");
    }

    public void checkIsElementButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut, "buttonSignOut");
    }

    public void checkIsElementButtonSignOutUnvisible() {
        checkIsElementUnvisible(buttonSignOut, "buttonSignOut");
    }

    public void checkIsElementLinkMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile, "buttonMyProfile");
    }

    public void checkIsElementLinkMyProfileUnvisible() {
        checkIsElementUnvisible(buttonMyProfile, "buttonMyProfile");
    }

    public void checkIsElementSpanUserNameVisible() {
        checkIsElementVisible(spanUserName, "spanUserName");
    }

    public void checkIsElementButtonChatVisible() {
        checkIsElementVisible(buttonChat, "buttonChat");
    }

    public void checkIsElementButtonChatUnvisible() {
        checkIsElementUnvisible(buttonChat, "buttonChat");
    }

    public void checkIsElementButtonSearchVisible() {
        checkIsElementVisible(buttonSearch, "buttonSearch");
    }

    public void checkIsElementButtonSearchUnvisible() {
        checkIsElementUnvisible(buttonSearch, "buttonSearch");
    }

    public void checkIsElementInputUsernameVisible() {
        checkIsElementVisible(inputUsername, "inputUsername");
    }

    public void checkIsElementInputUsernameUnvisible() {
        checkIsElementUnvisible(inputUsername, "inputUsername");
    }

    public void checkIsElementInputPasswordVisible() {
        checkIsElementVisible(inputPassword, "inputPassword");
    }

    public void checkIsElementInputPasswordUnvisible() {
        checkIsElementUnvisible(inputPassword, "inputPassword");
    }

    public void checkIsElementButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn, "buttonSignIn");
    }

    public void checkIsElementButtonSignInUnvisible() {
        checkIsElementUnvisible(buttonSignIn, "buttonSignIn");
    }


}
