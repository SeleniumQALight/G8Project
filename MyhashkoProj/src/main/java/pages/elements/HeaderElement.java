package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import pages.CommonActionsWhithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

// описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWhithElements {
    // create post button
    @FindBy(xpath = "//a[contains(text(), 'Create Post')]")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = ".//img[@alt='My profile']")
    private WebElement buttonMyProfile;
    @FindBy(xpath = ".//span[@class='text-white mr-2']")
    private WebElement buttonLogin;
    @FindBy(xpath = ".//*[@class='text-white mr-2 header-search-icon']")
    private WebElement buttonSearch;
    @FindBy(xpath = ".//*[@class='text-white mr-2 header-chat-icon']")
    private WebElement buttonChatCall;
    @FindBy(xpath = ".//*[@alt='My profile']")
    private WebElement avatarka;
    //my profile button
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile1;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisble() {

        return isElementDisplayed(buttonSignOut);

    }
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
    public boolean isButtonCreatePostVisble() {
        return isElementDisplayed(buttonCreatePost);
    }

    public boolean isButtonMyProfileVisble() {
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean isLoginVisible() {
        return isElementDisplayed(buttonLogin);
    }

    public boolean isButtonSearchVisble() {
        return isElementDisplayed(buttonSearch);
    }

    public boolean isButtonChatVisble() {
        return isElementDisplayed(buttonChatCall);
    }

    public boolean isAvatarVisible() {
        return isElementDisplayed(avatarka);
    }

    public void clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
    }

    public MyProfilePage clickOnButtonMyProfile() { clickOnElement(buttonMyProfile1);
        return new MyProfilePage(webDriver);
    }
}

