package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;

// описывает элементы, которые есть в хедере залогиненного пользователя
public class HeaderElement extends CommonActionsWithElements {


    // create post button
    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement linkCreatePost;

    @FindBy(xpath = ".//span[contains(@class,'text-white')and not(@data-original-title)]")
    private WebElement userName;

    @FindBy(xpath = ".//a[@data-original-title='Search']")
    private WebElement linkSearch;

    @FindBy(xpath = ".//span[@data-original-title='Chat']")
    private WebElement iconChat;

    //myProfile button
    @FindBy(xpath = ".//img[@data-original-title='My Profile']/..")
    private WebElement buttonMyProfile;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    @Step
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    @Step
    public boolean isButtonSignOutVisible() {
//        WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        return isElementDisplayed(buttonSignOut);
    }

    @Step
    public HeaderElement checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
        return this;
    }

    @Step
    public HeaderElement checkIsMyProfileButtonVisible() {
        checkIsElementVisible(buttonMyProfile);
        return this;
    }

    @Step
    public HeaderElement checkIsMyProfileButtonNotVisible() {
        checkIsElementNotVisible(buttonMyProfile);
        return this;
    }

    @Step
    public HeaderElement checkIsUserNameVisible() {
        checkIsElementVisible(userName);
        return this;
    }

    @Step
    public void checkTextInUsername(String text) {
        checkTextInElement(userName, text);
    }

    @Step
    public HeaderElement checkIsSearchLinkVisible() {
        checkIsElementVisible(linkSearch);
        return this;
    }

    @Step
    public HeaderElement checkIsSearchLinkNotVisible() {
        checkIsElementNotVisible(linkSearch);
        return this;
    }

    @Step
    public HeaderElement checkIsChatIconVisible() {
        checkIsElementVisible(iconChat);
        return this;
    }

    @Step
    public HeaderElement checkIsChatIconNotVisible() {
        checkIsElementNotVisible(iconChat);
        return this;
    }

    @Step
    public void checkIsHeaderForUserVisible() {
        checkIsSearchLinkVisible();
        checkIsChatIconVisible();
        checkIsMyProfileButtonVisible();
        checkIsButtonCreatePostVisible();
        checkIsButtonSignOutVisible();
        logger.info("Header for user is visible");
    }

    @Step
    public void checkIsHeaderForGuestVisible() {
        checkIsSearchLinkNotVisible();
        checkIsChatIconNotVisible();
        checkIsMyProfileButtonNotVisible();
        checkIsButtonCreatePostNotVisible();
        checkIsButtonSignOutNotVisible();
    }

    @Step
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
}
