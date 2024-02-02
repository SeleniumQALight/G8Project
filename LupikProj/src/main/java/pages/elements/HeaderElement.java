package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

// описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    // create post button


    @FindBy(xpath = ".//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    //my profile button
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//*[@alt='My profile']")
    private WebElement avatar;
    @FindBy(xpath = ".//span[@class='text-white mr-2']")
    private WebElement CurrentUserName;

    @FindBy(xpath = ".//span[@data-original-title='Chat']")
    private WebElement chatIcon;

    @FindBy(xpath = ".//a[@data-original-title='Search']")
    private WebElement searchIcon;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {

        return isElementDisplayed(buttonSignOut);

    }

    public boolean isButtonCreatePostVisible() {

        return isElementDisplayed(buttonCreatePost);
    }


    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);


    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonMyProfileVisible() {

        return isElementDisplayed(buttonMyProfile);

    }

    @Step
    public void checkUserNameIsPresent(String TestUserName) {
        WebElement element = CurrentUserName;
        checkTextInElement(element, TestUserName);
        logger.info("Username is visible and matches the test one");

    }

    @Step
    public boolean isChatIconVisible() {
        return isElementDisplayed(chatIcon);
    }

    @Step
    public void clickOnButtonSignOut() {
        // WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignOut);
    }

    @Step
    public boolean isSearchIconIsVisible() {
        return isElementDisplayed(searchIcon);
    }

    @Step
    public boolean isButtonAvatarVisible() {
        return isElementDisplayed(avatar);
    }


    //new methods:
    @Step
    public HeaderElement checkIsSearchIconIsNotVisible() {
        checkIsElementNotVisible(searchIcon);
        return this;
    }

    @Step
    public HeaderElement checkIsChatIconNotVisible() {
        checkIsElementNotVisible(chatIcon);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonAvatarNotVisible() {
        checkIsElementNotVisible(avatar);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonSihOutIsNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
        return this;
    }

    @Step
    public HeaderElement checkAllElementsInHeaderAreNotVisible() {
        checkIsSearchIconIsNotVisible();
        checkIsButtonAvatarNotVisible();
        checkIsChatIconNotVisible();
        checkIsButtonCreatePostNotVisible();
        checkIsButtonSihOutIsNotVisible();
        return this;
    }

}
