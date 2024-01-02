package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

// описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    // create post button
    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    //check the presence of elements in the Header - search buttons, chat buttons, avatars,
    // CreatePost buttons, signOut buttons,
    // as well as the absence of a field for entering a login, password, and sign In button.

    @FindBy(xpath = "//a[@class='text-white mr-2 header-search-icon']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[@class='text-white mr-2 header-chat-icon']")
    private WebElement chatButton;

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement avatar;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement createPostButton;

    @FindBy(xpath = "//button[@class='btn btn-sm btn-secondary']")
    private WebElement signOutButton;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement signInButton;


    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    // click on create post button
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    //Checking the presence of elements in the header after login

    public boolean isSearchButtonVisible() {
        return isElementDisplayed(searchButton);
    }

    public boolean isChatButtonVisible() {
        return isElementDisplayed(chatButton);
    }

    public boolean isAvatarVisible() {
        return isElementDisplayed(avatar);
    }

    public boolean isCreatePostButtonVisible() {
        return isElementDisplayed(createPostButton);
    }


    public boolean isSignOutButtonVisible() {
        return isElementDisplayed(signOutButton);
    }

    public boolean isLoginInputVisible() {
        return isElementDisplayed(loginInput);
    }

    public boolean isPasswordInputVisible() {
        return isElementDisplayed(passwordInput);
    }

    public boolean isSignInButtonVisible() {
        return isElementDisplayed(signInButton);
    }



}
