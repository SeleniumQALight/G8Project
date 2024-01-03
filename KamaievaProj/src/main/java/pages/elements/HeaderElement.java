package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;

//Discribe elements which present in header of page for user who was logined
public class HeaderElement extends CommonActionsWithElements{
    @FindBy(xpath = "//a[contains(text(), 'Create Post')]")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//img[@data-original-title= 'My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement userName;

    @FindBy(xpath = "//a[@data-original-title= 'Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-original-title= 'Chat']")
    private WebElement buttonChat;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public boolean isButtonCreatePostVisible(){
        return isElementDisplayed(buttonCreatePost);
    }

    public boolean isButtonMyProfileVisible(){
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean isButtonSearchVisible(){
        try {
            boolean state = webDriver.findElement(By.xpath("//a[@data-original-title= 'Search']")).isDisplayed();
            logger.info("Element 'Search' is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element 'Search' is not displayed");
            return false;
        }
    }

    public boolean isButtonChatVisible(){
        try{
            boolean state = webDriver.findElement(By.xpath("//span[@data-original-title= 'Chat']")).isDisplayed();
            logger.info("Element 'Chat' is displayed -> " + state);
            return state;
        } catch (Exception e){
            logger.info("Element 'Chat' is not displayed");
            return false;
        }
    }

    public boolean isUserNameVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//span[@class='text-white mr-2']")).isDisplayed();
            logger.info("Element 'User Name' is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.info("User Name is not displayed");
            return false;
        }
    }

    public Object getUserName(){
        return userName.getText();
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public void isButtonChatNotVisible() {
        checkIsElementNotVisible(buttonChat, "Button Chat");
    }

    public void isButtonSearchNotVisible() {
        checkIsElementNotVisible(buttonSearch, "Button Search");
    }

    public void isButtonMyProfileNotVisible() {
        checkIsElementNotVisible(buttonMyProfile, "Button My Profile");
    }

    public void isButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost, "Button Create Post");
    }

    public void isButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut, "Button Sign Out");
    }
}
