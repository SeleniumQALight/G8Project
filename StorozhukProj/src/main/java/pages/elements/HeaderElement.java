package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;

//this page describes elements of header for logged in user
public class HeaderElement extends CommonActionsWithElements {
    //create post button
    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    //my profile button
    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;
    @FindBy(xpath = ".//span[@class='text-white mr-2']")
    private WebElement buttonUserName;
    @FindBy(xpath = ".//a[@data-original-title='Search']")
    private WebElement buttonSearch;
    @FindBy(xpath = ".//*[contains(name(), 'svg')][@data-icon='comment']")
    private WebElement buttonComment;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        WebElement buttonSignOut = webDriver.findElement(
                By.xpath("//button[text()='Sign Out']"));
        return isElementDisplayed(buttonSignOut);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed(buttonCreatePost);
    }

    public boolean isButtonUserNameVisible() {
        return isElementDisplayed(buttonUserName);
    }

    public boolean isButtonMyProfileVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean isButtonCommentVisible() {
        return isElementDisplayed(buttonComment);
    }

    public boolean isButtonSearchVisible() {
        return isElementDisplayed(buttonSearch);
    }

    public boolean isButtonProfileVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    public LoginPage clickOnButtonSignOut() { //this method returns LoginPage
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public void isButtonSearchNotVisible() {
        checkElementIsNotVisible(buttonSearch);
    }

    private void checkElementIsNotVisible(WebElement buttonSearch) {
    }

    public void isButtonCreatePostNotVisible() {
        checkElementIsNotVisible(buttonCreatePost);
    }

    public void isButtonMyProfileNotVisible() {
        checkElementIsNotVisible(buttonMyProfile);
    }

    public void isButtonCommentNotVisible() {
        checkElementIsNotVisible(buttonComment);
    }

    public void isButtonUserNameNotVisible() {
        checkElementIsNotVisible(buttonUserName);
    }

    public void isButtonSignOutNotVisible() {
        checkElementIsNotVisible(buttonSignOut);
    }

    public boolean isUserNameVisible() {
        return isElementDisplayed(buttonUserName);
    }

    public void checkTextInUserName(String textUserName) {
        checkTextInElement(buttonUserName, textUserName);
    }

    public void checkIsButtonSignOutVisible(){
         checkIsElementVisible(buttonSignOut);
    }
}




