package pages.elements;

import libs.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;

// описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    //create new post
    @FindBy(xpath = ".// a [@href='/create-post']")
    private WebElement buttonCreatePost;

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

    public LoginPage clickOnElementButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public HeaderElement checkIsButtonCreateNewPostVisible() {
        checkIsElementVisible(buttonCreatePost, "buttonCreatePost");
        return this;
    }

    public HeaderElement checkIsButtonCreateNewPostUnvisible() {
        checkIsElementUnvisible(buttonCreatePost, "buttonCreatePost");
        return this;
    }

    public HeaderElement checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut, "buttonSignOut");
        return this;
    }

    public HeaderElement checkIsButtonSignOutUnvisible() {
        checkIsElementUnvisible(buttonSignOut, "buttonSignOut");
        return this;
    }

    public HeaderElement checkIsButtonMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile, "buttonMyProfile");
        return this;
    }

    public HeaderElement checkIsButtonMyProfileUnvisible() {
        checkIsElementUnvisible(buttonMyProfile, "buttonMyProfile");
        return this;
    }

    public HeaderElement checkIsSpanUserNameVisible() {
        checkIsElementVisible(spanUserName, "spanUserName");
        return this;
    }

    public HeaderElement checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat, "buttonChat");
        return this;
    }

    public HeaderElement checkIsButtonChatUnvisible() {
        checkIsElementUnvisible(buttonChat, "buttonChat");
        return this;
    }

    public HeaderElement checkIsButtonSearchVisible() {
        checkIsElementVisible(buttonSearch, "buttonSearch");
        return this;
    }

    public HeaderElement checkIsButtonSearchUnvisible() {
        checkIsElementUnvisible(buttonSearch, "buttonSearch");
        return this;
    }




}
