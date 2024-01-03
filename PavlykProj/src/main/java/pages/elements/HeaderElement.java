package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;

// описывает элементы, которые есть в хедере залогиненного пользователя
public class HeaderElement extends CommonActionsWithElements {


    // create post button
    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement linkCreatePost;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']/..")
    private WebElement linkMyProfile;

    @FindBy(xpath = ".//span[contains(@class,'text-white')and not(@data-original-title)]")
    private WebElement userName;

    @FindBy(xpath = ".//a[@data-original-title='Search']")
    private WebElement linkSearch;

    @FindBy(xpath = ".//span[@data-original-title='Chat']")
    private WebElement iconChat;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
//        WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        return isElementDisplayed(buttonSignOut);
    }

    public HeaderElement isButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public HeaderElement isButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
        return this;
    }

    public HeaderElement isButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public HeaderElement isButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
        return this;
    }

    public HeaderElement isMyProfileLinkVisible() {
        checkIsElementVisible(linkMyProfile);
        return this;
    }

    public HeaderElement isMyProfileLinkNotVisible() {
        checkIsElementNotVisible(linkMyProfile);
        return this;
    }

    public HeaderElement isUserNameVisible() {
        checkIsElementVisible(userName);
        return this;
    }

    public void checkTextInUsername(String text) {
        checkTextInElement(userName, text);
    }

    public HeaderElement isSearchLinkVisible() {
        checkIsElementVisible(linkSearch);
        return this;
    }

    public HeaderElement isSearchLinkNotVisible() {
        checkIsElementNotVisible(linkSearch);
        return this;
    }

    public HeaderElement isChatIconVisible() {
        checkIsElementVisible(iconChat);
        return this;
    }

    public HeaderElement isChatIconNotVisible() {
        checkIsElementNotVisible(iconChat);
        return this;
    }

    public void checkIsHeaderForUserVisible() {
        isSearchLinkVisible();
        isChatIconVisible();
        isMyProfileLinkVisible();
        isButtonCreatePostVisible();
        isButtonSignOutVisible();
        logger.info("Header for user is visible");
    }

    public void checkIsHeaderForGuestVisible() {
        isSearchLinkNotVisible();
        isChatIconNotVisible();
        isMyProfileLinkNotVisible();
        isButtonCreatePostNotVisible();
        isButtonSignOutNotVisible();
    }
}
