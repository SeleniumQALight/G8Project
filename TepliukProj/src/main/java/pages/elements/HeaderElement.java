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
    // create post button
    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;


    //myProfile button
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement nameOfUser;


    @FindBy(xpath = "//a[@data-original-title= 'Search']")
    private WebElement buttonFind;

    @FindBy(xpath = "//span[@data-original-title= 'Chat']")
    private WebElement buttonChat;

    @FindBy(xpath = ".//*[@alt='My profile']")
    private WebElement avatar;



    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
    @Step
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed(buttonCreatePost);
    }

    public boolean isButtonMyProfileVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean isNameOfUserVisible() {
        return isElementDisplayed(nameOfUser);
    }



    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }


    public HeaderElement checkIsButtonFindVisible() {
        checkIsElementVisible(buttonFind);
        return this;
    }

    public HeaderElement checkIsButtonFindNotVisible() {
        checkIsElementNotVisible(buttonFind);
        return this;
    }

    public HeaderElement checkIsButtonChatVisible() {
        checkIsElementVisible(buttonChat);
        return this;
    }

    public HeaderElement checkIsButtonChatNotVisible() {
        checkIsElementNotVisible(buttonChat);
        return this;
    }

    public HeaderElement checkIsAvatarVisible() {
        checkIsElementVisible(avatar);
        return this;
    }

    public HeaderElement checkIsAvatarNotVisible() {
        checkIsElementNotVisible(avatar);
        return this;
    }

    public HeaderElement checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public HeaderElement checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
        return this;
    }

    public HeaderElement checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }
    public HeaderElement checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
        return this;
    }

    public HeaderElement checkIsButtonMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile);
        return this;
    }




    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);

    }


}