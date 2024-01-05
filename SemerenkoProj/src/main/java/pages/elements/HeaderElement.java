package pages.elements;

import libs.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

// описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    //create new post
    @FindBy(xpath = ".// a [@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[@href='/profile/" + TestData.VALID_LOGIN_UI + "']")
    private WebElement buttonMyProfile;

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

    public void checkIsElementButtonCreateNewPostVisible() {
        checkIsElementVisible(buttonCreatePost, "buttonCreatePost");
    }

    public void checkIsElementButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut, "buttonSignOut");
    }

    public void checkIsElementLinkMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile, "buttonMyProfile");
    }

    public void checkIsElementSpanUserNameVisible() {
        checkIsElementVisible(spanUserName, "spanUserName");
    }

    public void checkIsElementInputUsernameUnvisible() {
        checkIsElementUnvisible(inputUsername, "inputUsername");
    }

    public void checkIsElementInputPasswordUnvisible() {
        checkIsElementUnvisible(inputPassword, "inputPassword");
    }


}
