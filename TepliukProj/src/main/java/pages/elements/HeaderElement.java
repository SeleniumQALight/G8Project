package pages.elements;


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

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;


    //myProfile button
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy (xpath = "//span[@class='text-white mr-2']")
    private WebElement nameOfUser;



      @FindBy (xpath = "//a[@data-original-title= 'Search']")
        private WebElement buttonFind;

      @FindBy (xpath = "//span[@data-original-title= 'Chat']")
      private WebElement buttonChat;

      @FindBy (xpath = ".//*[@alt='My profile']")
      private WebElement avatar;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

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

    public boolean isButtonFindVisible() {
    return isElementDisplayed(buttonFind);
    }

    public boolean isButtonChatVisible() {
        return isElementDisplayed(buttonChat);
    }

    public boolean isAvatarVisible() {
        return isElementDisplayed(avatar);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }



}
