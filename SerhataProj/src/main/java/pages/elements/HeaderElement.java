package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;

// описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = "//a[contains(text(), 'Create Post')]")
    public static WebElement buttonCreatePost;

    @FindBy(xpath = "//a[@href='/profile/qaauto']")
    public static WebElement titleMyProfile;

    @FindBy(xpath = "//a[@data-original-title='Search']")
    public static WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    public static WebElement buttonChat;

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement userName;

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public boolean isButtonSignOutPresent() {
        return isElementDisplayed(buttonSignOut);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(titleMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonCreatePostPresent() {return isElementDisplayed(buttonCreatePost);}

    public boolean isTitleMyProfilePresent() {return isElementDisplayed(titleMyProfile);}

    public boolean isUserNamePresent() {return isElementDisplayed(userName);}

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public void checkIsHeaderForUserVisible() {
        checkIsElementVisible(buttonCreatePost);
        checkIsElementVisible(titleMyProfile);
        checkIsElementVisible(buttonSearch);
        checkIsElementVisible(buttonChat);
        checkIsElementVisible(userName);
    }

    public void checkIsHomeHeaderInvisibleFolSignOutUser() {
        checkIsElementNotVisible(buttonCreatePost);
        checkIsElementNotVisible(titleMyProfile);
        checkIsElementNotVisible(buttonSearch);
        checkIsElementNotVisible(buttonChat);
        checkIsElementNotVisible(userName);
    }
}
