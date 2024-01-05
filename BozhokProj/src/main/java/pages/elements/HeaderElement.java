package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionWithElements;
import pages.CreatePostPage;

// опишемо елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionWithElements {

    // create post button
    @FindBy(xpath = "//*[@class ='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class ='mr-2']")
    private WebElement buttonProfile;

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement ProfileName;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement successProfileName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    // check is button Create Post visible
    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed(buttonCreatePost);
    }

    // check is button Profile visible
    public boolean isButtonProfileVisible() {
        return isElementDisplayed(buttonProfile);
    }

//    // check login input field
    public boolean isInputLoginVisible() {
        return isElementDisplayed(successProfileName);
    }
}