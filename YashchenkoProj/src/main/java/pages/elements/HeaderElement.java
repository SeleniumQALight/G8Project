package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

//This class is for elements that are present on all pages of the logged user
public class HeaderElement extends CommonActionsWithElements {
    //create post button
    @FindBy(xpath = ".//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//span[@class='text-white mr-2']")
    private WebElement profileName;

    @FindBy(xpath = ".//a[@href='/profile/qaauto']")
    private WebElement buttonMyProfile;

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public void checkIsProfileNameVisible() {
        checkIsElementVisible(profileName);
    }

    public void checkIsButtonMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile);
    }

    public void  checkIsCreatePostButtonVisible() {
        checkIsElementVisible(buttonCreatePost);
    }

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
