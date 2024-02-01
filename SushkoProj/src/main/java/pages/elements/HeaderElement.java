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
    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//span[@class = 'text-white mr-2']")
    private WebElement spanUserProfileName;

    @FindBy(xpath = ".//a[@data-original-title = 'Search']")
    private WebElement linkGetSearch;

    @FindBy(xpath = ".//span[@data-original-title = 'Chat']")
    private WebElement spanGetChat;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']//..") ////img[@alt='My profile']
    private WebElement buttonMyProfile;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public boolean isButtonCreatePostIsVisible() {
        return isElementDisplayed(buttonCreatePost);
    }

    public boolean isButtonMyProfileIsVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean isSpanUserProfileNameIsVisible() {
        return isElementDisplayed(spanUserProfileName);
    }

    public boolean isLinkGetSearchIsVisible() {
        return isElementDisplayed(linkGetSearch);
    }

    public boolean isSpanGetChatIsVisible() {
        return isElementDisplayed(spanGetChat);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    @Step
    public HeaderElement checkAllElementsInHeaderAreVisible() {
        isButtonSignOutVisible();
        isButtonCreatePostIsVisible();
        isButtonMyProfileIsVisible();
        isSpanUserProfileNameIsVisible();
        isLinkGetSearchIsVisible();
        isSpanGetChatIsVisible();
        return this;
    }

    public void checkIsButtonSignOutInvisible(){
        checkIsElementNotVisible(buttonSignOut);
    }

    public void checkIsButtonSignOutVisible(){
        checkIsElementVisible(buttonSignOut);
    }

    public void checkAllElementsFromHeaderAreInvisible() {
        checkIsElementNotVisible(buttonCreatePost);
        checkIsElementNotVisible(buttonSignOut);
        checkIsElementNotVisible(buttonMyProfile);
        checkIsElementNotVisible(spanUserProfileName);
        checkIsElementNotVisible(linkGetSearch);
        checkIsElementNotVisible(spanGetChat);
    }

    public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
}