package pages.elements;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;


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

    @FindBy(xpath = "//a[@class=\"mr-2\"]")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement userName;

    @Step
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    // click on create post button
    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    //Checking the presence of elements in the header after login
    @Step
    public HeaderElement checkSearchButtonIsVisible() {
     Assert.assertTrue("Search button is not displayed", isElementDisplayed(searchButton));
        return this;
    }
    @Step
    public HeaderElement checkChatButtonIsVisible() {
        Assert.assertTrue("Chat button is not displayed", isElementDisplayed(chatButton));
        return this;
    }
    @Step
    public HeaderElement checkAvatarIsVisible() {
        Assert.assertTrue("Avatar is not displayed", isElementDisplayed(avatar));
        return this;
    }
    @Step
    public HeaderElement checkCreatePostButtonIsVisible() {
        Assert.assertTrue("Create Post button is not displayed", isElementDisplayed(createPostButton));
        return this;
    }

    @Step
    public HeaderElement checkSignOutButtonIsVisible() {
        Assert.assertTrue("Sign Out button is not displayed", isElementDisplayed(signOutButton));
        return this;
    }


    // Sing out button
    @Step
    public void clickOnButtonSignOut() {
          clickOnElement(buttonSignOut);

    }
    @Step
    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed(buttonCreatePost);

    }

    public boolean isButtonMyProfileVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean isUserNameVisible() {
        return isElementDisplayed(userName);
    }




    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
}
