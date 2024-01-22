package pages.elements;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;

// описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    private String buttonMyProfileLocator = ".//a[@href='/profile/%s']";

    private String spanUserNameLocator = ".//span[text()=' %s']";

    @FindBy(xpath = ".// a [@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
    private WebElement buttonSignOut;


    @FindBy(xpath = ".//span[@data-original-title='Chat']")
    private WebElement buttonChat;

    @FindBy(xpath = ".//a[@data-original-title='Search']")
    private WebElement buttonSearch;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getButtonMyProfile(String username) {
        try {
            return webDriver.findElement(By.xpath(String.format(buttonMyProfileLocator, username)));
        } catch (Exception e) {
            return null;
        }
    }

    private WebElement getSpanUserName(String username) {
        try {
            return webDriver.findElement(By.xpath(String.format(spanUserNameLocator, username)));
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public MyProfilePage clickOnButtonProfile() {
        clickOnElement(getButtonMyProfile(TestData.VALID_LOGIN_UI));
        return new MyProfilePage(webDriver);
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
        checkIsElementVisible(getButtonMyProfile(TestData.VALID_LOGIN_UI), "buttonMyProfile");
        return this;
    }

    public HeaderElement checkIsButtonMyProfileUnvisible() {
        checkIsElementUnvisible(getButtonMyProfile(TestData.VALID_LOGIN_UI), "buttonMyProfile");
        return this;
    }

    public HeaderElement checkIsSpanUserNameVisible() {
        checkIsElementVisible(getSpanUserName(TestData.VALID_LOGIN_UI), "spanUserName");
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
