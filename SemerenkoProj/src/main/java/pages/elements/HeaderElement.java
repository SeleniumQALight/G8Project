package pages.elements;

import data.TestData;
import io.qameta.allure.Step;
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

    @Step
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    @Step
    public MyProfilePage clickOnButtonProfile() {
        clickOnElement(getButtonMyProfile(TestData.VALID_LOGIN_UI));
        return new MyProfilePage(webDriver);
    }

    @Step
    public MyProfilePage clickOnButtonProfile(String login) {
        clickOnElement(getButtonMyProfile(login));
        return new MyProfilePage(webDriver);
    }

    @Step
    public LoginPage clickOnElementButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    @Step
    public HeaderElement checkIsButtonCreateNewPostVisible() {
        checkIsElementVisible(buttonCreatePost, "buttonCreatePost");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonCreateNewPostUnvisible() {
        checkIsElementUnvisible(buttonCreatePost, "buttonCreatePost");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut, "buttonSignOut");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonSignOutUnvisible() {
        checkIsElementUnvisible(buttonSignOut, "buttonSignOut");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonMyProfileVisible() {
        checkIsElementVisible(getButtonMyProfile(TestData.VALID_LOGIN_UI), "buttonMyProfile");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonMyProfileUnvisible() {
        checkIsElementUnvisible(getButtonMyProfile(TestData.VALID_LOGIN_UI), "buttonMyProfile");
        return this;
    }

    @Step
    public HeaderElement checkIsSpanUserNameVisible() {
        checkIsElementVisible(getSpanUserName(TestData.VALID_LOGIN_UI), "spanUserName");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat, "buttonChat");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonChatUnvisible() {
        checkIsElementUnvisible(buttonChat, "buttonChat");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonSearchVisible() {
        checkIsElementVisible(buttonSearch, "buttonSearch");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonSearchUnvisible() {
        checkIsElementUnvisible(buttonSearch, "buttonSearch");
        return this;
    }


}
